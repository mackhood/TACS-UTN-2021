import React, {useContext, useEffect, useState} from "react";
import Grid from "@material-ui/core/Grid";
import {useHistory, useParams, useRouteMatch} from "react-router-dom";
import Box from '@material-ui/core/Box';
import Container from '@material-ui/core/Container';
import Button from "@material-ui/core/Button";
import Dialog from '@material-ui/core/Dialog';
import DialogTitle from '@material-ui/core/DialogTitle';
import {AppContext} from "../../Common/AppContext";
import {useAuth} from "../../Auth/useAuth";
import {PlayDuelButton} from "../../Api/Effects/PlayDuelButton";
import CommonService from "../../Api/CommonService";
import * as _ from "lodash";
import {TurnAttributes} from "./GameComponents/TurnAttributes";
import {AttributeLabel} from "./GameComponents/AttributeLabel";
import {TurnCards} from "./GameComponents/TurnCards";
import {CardsWonLabel} from "./GameComponents/CardsWonLabel";
import {SeeDuels} from "./GameComponents/SeeDuels";
import {PlayersTurnLabel} from "./GameComponents/PlayerTurnLabel";
import {ShuffleCardsButton} from "./GameComponents/ShuffleCards";

export default function Game() {
    let history = useHistory();
    const {user} = useAuth();
    const [game, setGame] = useState(null);
    const [sessionUser, setSessionUser] = useState({});
    const [enableGame, setEnableGame] = useState(false);
    const [currentDuel, setCurrenDuel] = useState({
        "creatorCard": {
            "id": 4,
            "name": "Abomination",
            "powerstats": {
                "strength": 80,
                "intelligence": 63,
                "speed": 53,
                "durability": 90,
                "power": 62,
                "combat": 95
            }
        },
        "challengedCard": {
            "id": 1,
            "name": "A-Bomb",
            "powerstats": {
                "strength": 100,
                "intelligence": 38,
                "speed": 17,
                "durability": 80,
                "power": 24,
                "combat": 64
            }

        },
        "attribute": null,
        "result": null
    });
    const [showCards, setShowCards] = useState(false);
    const [atributoEnJuego, setAtributoEnJuego] = useState("Elegir atributo");
    const [openResult, setOpenResult] = useState(false);
    const [jugadorTurno, setJugadorTurno] = useState(null);

    const [loading, setLoading] = useState(true);

    const {state} = useContext(AppContext);

    let {id} = useParams();
    let {url} = useRouteMatch();

    const getNextPlayerUsername= (game) => {
        //TODO pedir que la api devuelva un booleano o un number
        if (game.creator.isMyTurn === "true") return game.creator.username;
        if (game.challenged.isMyTurn === "true") return game.challenged.username;
        return "Partido finalizado";
    }
    useEffect(() => {
        async function fetchData(){
            const responseDuels = await CommonService.getGameDuels({id: id});
            const responseGame = await CommonService.getSingleGame({id: id});
            let newDuels = game !== null ? game.duels : [];
            if (responseDuels.data.data !== []) newDuels = _.union(newDuels, [responseDuels.data.data]);
            setGame({
                ...game,
                game: responseGame.data,
                duels: newDuels
            });
            if (user.username === responseGame.data.creator.username){
                setSessionUser({...sessionUser, username: responseGame.data.creator.username});
            }else{
                setSessionUser({...sessionUser, username: responseGame.data.challenged.username});
            }
            setJugadorTurno(getNextPlayerUsername(responseGame.data));
        }
        fetchData();
    }, []);
    //Cuando es el turno del usuario logeado habilito el juego
    useEffect(() => {
        //TODO validaciones con estado de la partida
        if (game !== null && game.game.state !== "INPROGRESS") {
            setEnableGame(false);
            return true;
        }
        if (jugadorTurno !== null && sessionUser !== null) {
            let cardsAvailable = game.game.actualNumberCards > 0;
            setEnableGame((jugadorTurno === sessionUser.username) && cardsAvailable);
        }
    }, [jugadorTurno, sessionUser, enableGame]);


    //TODO get from API
    let attributes = ["intelligence", "strength", "speed", "durability", "power", "combat"];

    function handleClose (){
        setOpenResult(false);
    }

    function setAttribute(attr) {
        setAtributoEnJuego(attr);
    }
    const navigateToDuels = () => {
        history.push(url + '/duels');
    }

    function handleRepartirCartas() {
        async function fetchNextCard() {
            return await CommonService.getNextUserCard({id: game.game.id});
        }
        if (game.game.actualNumberCards > 0){
            setLoading(true);
            fetchNextCard().then((response)=>{
                setCurrenDuel(
                {
                        ...currentDuel,
                    creatorCard:{
                        card: {
                            name: response.data.name
                        },
                        powerstats: {
                            "strength": response.data.strength,
                            "intelligence": response.data.intelligence,
                            "speed": response.data.speed,
                            "durability": response.data.durability,
                            "power": response.data.power,
                            "combat": response.data.combat
                        }
                    },
                    challengedCard: {
                        card: {
                            name: "??"
                        },
                        powerstats: {
                            "strength": "??",
                            "intelligence": "??",
                            "speed": "??",
                            "durability": "??",
                            "power": "??",
                            "combat": "??"
                        }
                    }
                    }
                );
            }).catch((err) => console.log(err)).then(() =>setLoading(false));

            setShowCards(true);
        }
    }

    useEffect(() => {
        setLoading(game === null || sessionUser === null);
    }, [game, sessionUser]);

    return (
        loading ? <h3>Loading...</h3> :
        (
            <div>
                <Dialog disableBackdropClick disableEscapeKeyDown open={openResult} onClose={handleClose}>
                    <DialogTitle>GANADOR</DialogTitle>
                </Dialog>
                <Grid container alignContent={"center"}>
                    <Grid item xs={12} sm={4}>
                        <Container>

                            <PlayersTurnLabel
                                jugadorTurno={jugadorTurno}
                                game={game}
                            />
                            <br></br>
                            <TurnAttributes strings={attributes} callbackfn={(attr, index) => {

                                return (
                                    <Grid item xs={12} key={index}>
                                        <Button
                                            variant="contained"
                                            onClick={() => setAttribute(attr)}
                                            color="primary"
                                            size="large"
                                            fullWidth
                                        >
                                            {attr}
                                        </Button>
                                        <br></br>
                                        <br></br>
                                    </Grid>
                                )
                            }}/>
                            <br></br>
                            <AttributeLabel
                                enableGame={enableGame}
                                atributoEnJuego={atributoEnJuego}
                            />
                            <br></br>
                            <br></br>
                        </Container>
                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <Container>
                            <ShuffleCardsButton
                                enableGame={enableGame}
                                onClick={handleRepartirCartas}
                            />
                            <br></br>
                            <br></br>
                            <TurnCards
                                showCards={showCards}
                                currentDuel={currentDuel}
                                game={game}
                            />

                            <br></br>
                            <br></br>
                            <Box component="span" display="block" bgcolor="blue">
                                <PlayDuelButton
                                    game={{game: game.game}}
                                    attribute={atributoEnJuego}
                                    disabled={!enableGame || atributoEnJuego === "Elegir atributo" || !showCards}
                                    setGame={setGame}
                                />
                            </Box>
                        </Container>
                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <Container>
                            <SeeDuels onClick={navigateToDuels}/>
                            <br></br>
                            <br></br>
                            <CardsWonLabel sessionUser={sessionUser}/>
                            <br/>
                            <br/>
                        </Container>
                    </Grid>
                </Grid>
            </div>
        )
    );

}


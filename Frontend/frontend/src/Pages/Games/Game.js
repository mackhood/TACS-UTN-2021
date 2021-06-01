import React, {useContext, useEffect, useState} from "react";
import Grid from "@material-ui/core/Grid";
import {useHistory} from "react-router-dom";
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import Container from '@material-ui/core/Container';
import Button from "@material-ui/core/Button";
import HeroeCard from "../../Components/HeroeCard";
import Dialog from '@material-ui/core/Dialog';
import DialogTitle from '@material-ui/core/DialogTitle';
import {AppContext} from "../../Common/AppContext";
import * as _ from "lodash";
import {useRouteMatch} from "react-router";
import {useAuth} from "../../Auth/useAuth";
import {DropGameButton} from "../../Api/Effects/DropGameButton";
import {PlayDuelButton} from "../../Api/Effects/PlayDuelButton";

export default function Game() {
    let history = useHistory();
    const {user} = useAuth();
    const [game, setGame] = useState(null);
    //User seeing the game
    const [sessionUser, setSessionUser] = useState({});

    const [enableGame, setEnableGame] = useState(false);

    // {
    //     "id": -1,
    //     "cardsLeft": null,
    //     "creatorCard": {
    //     "id": 10,
    //         "name": "Agent Bob",
    //         "strength": 10,
    //         "intelligence": 10,
    //         "speed": 10,
    //         "durability": 10,
    //         "power": 10,
    //         "combat": 10
    // },
    //     "challengedCard": {
    //     "id": 2,
    //         "name": "Abe Sapien",
    //         "strength": 20,
    //         "intelligence": 20,
    //         "speed": 20,
    //         "durability": 20,
    //         "power": 20,
    //         "combat": 20
    // },
    //     "attribute": "strength",
    //     "result": {
    //     "winner": "player3",
    //         "result": "VICTORY"
    // }}
    const [currentDuel, setCurrenDuel] = useState({
        "id": -1,
        "cardsLeft": null,
        "creatorCard": null,
        "challengedCard": null,
        "attribute": "",
        "result": null
    });

    const [showCards, setShowCards] = useState(false);
    const [showAttributes, setShowAttributes] = useState(false);
    const [atributoEnJuego, setAtributoEnJuego] = useState("Elegir atributo");
    const [openResult, setOpenResult] = useState(false);
    const {state} = useContext(AppContext);
    let {id} = useRouteMatch();

    useEffect(() => {

        let someGame = _.find(state.games, function(elem) {
            return elem.id === parseInt(id);
        });
        if (someGame === undefined) {
            history.push('/games');
        }else{

            setGame(someGame);
            setEnableGame(someGame.nextPlayerUsername === user.username);
            let currentDuel = someGame.duels[someGame.duels.length -1];
            if (currentDuel.result !== null){
                setCurrenDuel(currentDuel);
            }
            if (someGame.creator.username === user.username){
                setSessionUser(someGame.creator);
            }else{
                setSessionUser(someGame.challenged);
            }
        }
    }, []);

    const jugadorTurno = game.nextPlayerUsername;

    //TODO get from API
    let attributes = ["intelligence", "strength", "speed", "durability", "power", "combat"];

    function handleClose (){
        setOpenResult(false);
    }

    function setAttribute(attr) {
        if (showAttributes) {
            setShowAttributes(false);
            setCurrenDuel({...currentDuel, attribute: attr});
        } else {
            setShowAttributes(true);
            setCurrenDuel({...currentDuel, attribute: "Elegir atributo"});
        }
    }

    function createGameResultData(duel, winnerName) {
        return { duel, winnerName };
    }

    function createGameResults(game) {
        let data = [];
        game.duels.forEach((x) => {
            data.push(
                createGameResultData(
                    x.id,
                    state.users.filter(y => y.id === x.winnerId)[0].name
                ));
        })
        return data;
    }

    const showDuels = () => {
        showTable("/duels", "PARTIDA " + game.id, ["Duelo", "Ganador"], createGameResults(game))
    }

    const showTable = async (url, title, tableHeaders, tableRows) => {
        const location = {
            pathname: url,
            state: {
                title: title,
                tableHeaders: tableHeaders,
                tableRows: tableRows
            }
        }

        history.push(location);
    }

    function handleRepartirCartas() {
        let creatorCard = game.creator.cards[0];
        let challengedCard = game.challenged.cards[0];
        setShowCards(true);
        setCurrenDuel({...currentDuel, creatorCard: creatorCard, challengedCard: challengedCard});
    }

    return (
        game === null ? <h3>Loading...</h3> :
        (
            <div>
                <Dialog disableBackdropClick disableEscapeKeyDown open={openResult} onClose={handleClose}>
                    <DialogTitle>GANADOR</DialogTitle>
                </Dialog>
                <Grid container alignItems={"top"} alignContent={"center"}>
                    <Grid item xs={12} sm={4}>
                        <Container>

                            <Box component="span" display="block" bgcolor="green">
                                <Typography gutterBottom variant="h4" component="h2">
                                    TURNO DE JUGADOR
                                    </Typography>
                                <Typography gutterBottom variant="h5" component="h2">
                                    {jugadorTurno}
                                </Typography>
                                <DropGameButton game={game}/>
                                <br></br>
                                <br></br>
                            </Box>
                            <br></br>
                            <Box component="span" display="block" bgcolor="orange">
                                <Typography gutterBottom variant="h4" component="h2">
                                    ATRIBUTOS
                                    </Typography>
                                <Grid container alignItems={"center"} alignContent={"center"} justify="center" >
                                    <Box component="span" display="block" bgcolor="orange" width="75%">
                                        {attributes.map((attr, index) => {

                                            return (
                                                <Grid item xs={12}>
                                                    <Button
                                                        variant="contained"
                                                        onClick={() => setAttribute(attr)}
                                                        disabled={!showAttributes || !enableGame}
                                                        color="primary"
                                                        size="large"
                                                        fullWidth="true"
                                                    >
                                                        {attr}
                                                    </Button>
                                                    <br></br>
                                                    <br></br>
                                                </Grid>
                                            )
                                        })}
                                    </Box>
                                </Grid>
                            </Box>
                            <br></br>
                            <Box component="span" display="block" bgcolor="green">
                                <Typography gutterBottom variant="h4" component="h2">
                                    Atributo:
                                    </Typography>
                                <Typography gutterBottom variant="h5" component="h2">
                                    {!enableGame ? "Aguarda tu turno" :  atributoEnJuego}
                                </Typography>
                            </Box>
                            <br></br>
                            <br></br>
                        </Container>
                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <Container>
                            <Box component="span" display="block" bgcolor="green" height="500">
                                <Typography gutterBottom variant="h4" component="h2">
                                    MAZO
                                </Typography>
                                <Grid container alignItems={"center"} alignContent={"center"} justify="center" >
                                    <Box component="span" display="block" bgcolor="orange" width="75%">
                                        <Grid item xs={12}>
                                            <Button
                                                variant="contained"
                                                disabled={!enableGame || !showCards}
                                                onClick={handleRepartirCartas}
                                                color="primary"
                                                size="large"
                                                fullWidth="true"
                                            >
                                                Repartir cartas
                                            </Button>
                                        </Grid>
                                    </Box>
                                    <br></br>
                                    <br></br>
                                    <br></br>
                                </Grid>
                            </Box>
                            <br></br>
                            <br></br>
                            {showCards ?
                                (
                                    <Box component="span" display="block" bgcolor="orange">
                                        <Typography gutterBottom variant="h4" component="h2">
                                            CARTAS
                                        </Typography>
                                        <Grid container alignItems={"center"} alignContent={"center"} justify="center" spacing={4}>
                                            <Grid item xs={12} sm={5}>
                                                <Box component="span" display="block" bgcolor="orange">
                                                    <Typography gutterBottom variant="h4" component="h2">
                                                        Tu carta
                                                    </Typography>
                                                    <HeroeCard
                                                        name={sessionUser.cards[0].name}
                                                        powerstats={sessionUser.cards[0].powerstats}
                                                        image={sessionUser.cards[0].image}
                                                    />
                                                </Box>
                                            </Grid>
                                            <Grid item xs={12} sm={5}>
                                                <Box component="span" display="block" bgcolor="orange">
                                                    <Typography gutterBottom variant="h4" component="h2">
                                                        Contrincante
                                                    </Typography>
                                                    <HeroeCard
                                                        name={ currentDuel.result ? game.challengedCard.name : "??" }
                                                        powerstats={currentDuel.result ? game.challengedCard.powerstats : "??"}
                                                        image={currentDuel.result ? game.challengedCard.image : "??"}
                                                    />
                                                </Box>
                                            </Grid>
                                        </Grid>
                                    </Box>
                                ) :
                                (
                                    <div>
                                        <Typography gutterBottom variant="h4" component="h2">
                                            Por favor, repartir las cartas
                                        </Typography>
                                    </div>
                                )
                            }

                            <br></br>
                            <br></br>
                            <Box component="span" display="block" bgcolor="blue">
                                <PlayDuelButton
                                    attribute={currentDuel.attribute}
                                    disabled={!enableGame || currentDuel.attribute === ""}
                                />
                            </Box>
                        </Container>
                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <Container>
                            <Box component="span" display="block" bgcolor="green" height="500">
                                <Typography gutterBottom variant="h4" component="h2">
                                    DUELOS
                                    </Typography>
                                <Grid container alignItems={"center"} alignContent={"center"} justify="center" >
                                    <Box component="span" display="block" bgcolor="orange" width="75%">
                                        <Grid item xs={12}>

                                            <Button variant="contained" onClick={showDuels} color="primary" size="large" fullWidth="true">
                                                Ver duelos
                                                    </Button>

                                        </Grid>
                                    </Box>
                                    <br></br>
                                    <br></br>
                                    <br></br>
                                </Grid>
                            </Box>
                            <br></br>
                            <br></br>
                            <Box component="span" display="block" bgcolor="orange">
                                <Typography gutterBottom variant="h4" component="h2">
                                    CARTAS GANADAS
                                    </Typography>
                                <Grid container alignItems={"center"} alignContent={"center"} justify="center" >
                                    <Box component="span" display="block" bgcolor="orange" width="75%">
                                        {sessionUser.gainedCards.map((card, index) => {

                                            return (
                                                <Grid item xs={12} key={index}>
                                                    <Button variant="contained" color="primary" size="large" fullWidth="true">
                                                        {card.name}
                                                    </Button>
                                                    <br></br>
                                                    <br></br>
                                                </Grid>
                                            )
                                        })}
                                    </Box>
                                </Grid>
                            </Box>
                        </Container>
                    </Grid>
                </Grid>
            </div>
        )
    );

}


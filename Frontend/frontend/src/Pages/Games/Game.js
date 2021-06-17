import React, {useContext, useEffect, useState} from "react";
import Grid from "@material-ui/core/Grid";
import {useHistory, useParams, useRouteMatch} from "react-router-dom";
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import Container from '@material-ui/core/Container';
import Button from "@material-ui/core/Button";
import HeroeCard from "../../Components/HeroeCard";
import Dialog from '@material-ui/core/Dialog';
import DialogTitle from '@material-ui/core/DialogTitle';
import {AppContext} from "../../Common/AppContext";
import {useAuth} from "../../Auth/useAuth";
import {DropGameButton} from "../../Api/Effects/DropGameButton";
import {PlayDuelButton} from "../../Api/Effects/PlayDuelButton";
import CommonService from "../../Api/CommonService";
import {customAlphabet} from 'nanoid'

const nanoid = customAlphabet('1234567890', 2);

export default function Game() {
    let history = useHistory();
    const {user} = useAuth();
    const [game, setGame] = useState(null);
    const [sessionUser, setSessionUser] = useState({});
    const [enableGame, setEnableGame] = useState(false);
    const [currentDuel, setCurrenDuel] = useState({
        "cardsLeft": 3,
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
    const [showAttributes, setShowAttributes] = useState(false);
    const [atributoEnJuego, setAtributoEnJuego] = useState("Elegir atributo");
    const [openResult, setOpenResult] = useState(false);
    const [jugadorTurno, setJugadorTurno] = useState(null);
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
            if (responseDuels.data.data !== null) newDuels.push({...responseDuels.data.data});

            setGame({
                ...game,
                game: responseGame.data,
                duels: newDuels
            });
            if (user.username === responseGame.data.creator.username){
                setSessionUser(responseGame.data.creator);
            }else{
                setSessionUser(responseGame.data.challenged.username);
            }
            setJugadorTurno(getNextPlayerUsername(responseGame.data));
        }
        fetchData();
    }, []);
    //Cuando es el turno del usuario logeado habilito el juego
    useEffect(() => {
        //TODO validaciones con estado de la partida
        if (jugadorTurno !== null && sessionUser !== null) {
            let cardsAvailable = game.game.actualNumberCards > 0;
            setEnableGame(jugadorTurno === sessionUser.username && cardsAvailable);
        }
    }, [jugadorTurno, sessionUser])


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

    const navigateToDuels = () => {
        history.push(url + '/duels');
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
        //Si todavía quedan cartas por jugar
        if (game.game.actualNumberCards > 0){
            console.log(game.duels);
            // let creatorCard = game.duels[game.duels.length-1].creatorCard;
            // let creatorCard = {};
            // let challengedCard = game.duels[game.duels.length-1].challengedCard;
            // let challengedCard = {};
            setShowCards(true);
            // setCurrenDuel({...currentDuel, creatorCard: creatorCard, challengedCard: challengedCard});
        }
    }

    return (
        game === null || sessionUser === null ? <h3>Loading...</h3> :
        (
            <div>
                <Dialog disableBackdropClick disableEscapeKeyDown open={openResult} onClose={handleClose}>
                    <DialogTitle>GANADOR</DialogTitle>
                </Dialog>
                <Grid container alignContent={"center"}>
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
                                                disabled={!enableGame}
                                                onClick={handleRepartirCartas}
                                                color="primary"
                                                size="large"
                                                fullWidth
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
                                                        name={currentDuel.creatorCard.name}
                                                        powerstats={currentDuel.creatorCard.powerstats}
                                                        image={currentDuel.creatorCard.image}
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
                                    attribute={currentDuel ? currentDuel.attribute : ""}
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
                                            <Button variant="contained" onClick={navigateToDuels} color="primary" size="large" fullWidth>
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
                                    CARTAS GANADAS:
                                    </Typography>
                                <Grid container alignItems={"center"} alignContent={"center"} justify="center" >
                                    <Box component="span" display="block" bgcolor="orange" width="75%">
                                        {sessionUser.gainedCards}
                                    </Box>
                                </Grid>
                            </Box>
                            <br/>
                            <br/>
                            <pre>{JSON.stringify(game, null, 2)}</pre>
                            <pre>{JSON.stringify(currentDuel, null, 2)}</pre>
                            <pre>{JSON.stringify(sessionUser, null, 2)}</pre>
                        </Container>
                    </Grid>
                </Grid>
            </div>
        )
    );

}


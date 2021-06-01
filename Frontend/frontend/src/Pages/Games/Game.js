import React, {useState} from "react";
import Grid from "@material-ui/core/Grid";
import getGames from "../Resources/getGames";
import {makeStyles} from "@material-ui/core/styles";
import getDecks from "../Resources/getDecks";
import getUsers from "../Resources/getUsers";
import getCards from "../Resources/getCards";
import {useHistory, useLocation} from "react-router-dom";
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import Container from '@material-ui/core/Container';
import Button from "@material-ui/core/Button";
import HeroeCard from "../Components/HeroeCard";

import Dialog from '@material-ui/core/Dialog';
import DialogTitle from '@material-ui/core/DialogTitle';

const { customAlphabet } = require('nanoid')

const useStyles = makeStyles((theme) => ({
    layout: {
        width: 'auto',
        marginLeft: theme.spacing(2),
        marginRight: theme.spacing(2),
        [theme.breakpoints.up(800 + theme.spacing(2) * 2)]: {
            width: 800,
            marginLeft: 'auto',
            marginRight: 'auto',
        },
        margin: 10
    },
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    formControl: {
        margin: theme.spacing(1),
        minWidth: 120,
    },
    turnPaper: {
        width: 400,
        height: 300,
        overflow: 'auto',
    },
    attsPaper: {
        width: 400,
        height: 600,
        overflow: 'auto',
    },
    deckPaper: {
        width: 800,
        height: 400,
        overflow: 'auto',
    },
    cardsPaper: {
        width: 800,
        height: 400,
        overflow: 'auto',
    },
    playButtonPaper: {
        width: 800,
        height: 100,
        overflow: 'auto',
    },
    duelsPaper: {
        width: 400,
        height: 600,
        overflow: 'auto',
    },
    wonCartsPaper: {
        width: 400,
        height: 300,
        overflow: 'auto',
    },
}));



export default function Game() {


    let history = useHistory();
    const [games] = useState(getGames().data);
    const [users] = useState(getUsers());
    const [cards] = useState(getCards());
    const [heroes] = useState(getCards());
    const [showCards, setShowCards] = useState(false);
    const [showAttributes, setShowAttributes] = useState(false);
    const [atributoEnJuego, setAtributoEnJuego] = useState("Elegir atributo");
    const [cardsHandOut, setCardsHandOut] = useState(true);
    const [cardsHandedOut, setCardsHandedOut] = useState(false);
    const [executeGame, setExecuteGame] = useState(false);
    const [openResult, setOpenResult] = useState(false);

    let jugadorTurno = users[0].name;
    let attributes = [];
    let currentGame = games[0];


    for (var propertyName in cards[0].powerstats) {
        attributes.push(propertyName)
    }

    let wonCards = [];
    cards.map((card) => {
        wonCards.push({ "name": card.name });
    })

    function handleClose (){

    }


    const executeDuel = async () => {
        if (showCards) {
            setShowCards(false);
            setExecuteGame(true);
            
            
        } else {
            setShowCards(true);
            setExecuteGame(false);
            setTimeout(() => { 
                setOpenResult(true);
                setTimeout(() => { 
                    setOpenResult(false);
                    setCardsHandOut(true);
                    setCardsHandedOut(false);
                },2000);
            }, 1000);
        }

    }

    function getCard() {
        setCardsHandOut(false);
        setCardsHandedOut(true);
        setShowAttributes(true);
    }

    function setAttribute(attr) {
        if (showAttributes) {
            setShowAttributes(false);
            setExecuteGame(true);
            setAtributoEnJuego(attr);
        } else {
            setExecuteGame(false);
            setShowAttributes(true);
            setAtributoEnJuego("Elegir atributo");
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
                    users.filter(y => y.id == x.winnerId)[0].name
                ));
        })
        return data;
    }

    const showDuels = () => {
        showTable("/duels", "PARTIDA " + currentGame.id, ["Duelo", "Ganador"], createGameResults(currentGame))
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

    return (
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
                            <Button variant="contained" onClick={getCard} color="primary" size="medium">
                                Abandonar Partida
                                                </Button>
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
                                                <Button variant="contained" onClick={() => setAttribute(attr)} disabled={!showAttributes} color="primary" size="large" fullWidth="true">
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
                                {atributoEnJuego}
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
                                        <Button variant="contained" disabled={!cardsHandOut} onClick={getCard} color="primary" size="large" fullWidth="true">
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
                        {cardsHandedOut ? (
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
                                                name={heroes[0].name}
                                                powerstats={heroes[0].powerstats}
                                                image={heroes[0].image} />
                                        </Box>
                                    </Grid>
                                    <Grid item xs={12} sm={5}>
                                        <Box component="span" display="block" bgcolor="orange">
                                            <Typography gutterBottom variant="h4" component="h2">
                                                Contrincante
                                            </Typography>
                                            {showCards ? (
                                                <HeroeCard
                                                    name={heroes[1].name}
                                                    powerstats={heroes[1].powerstats}
                                                    image={heroes[1].image} />
                                            ) :
                                                (
                                                    <HeroeCard
                                                        name="Carta"
                                                        powerstats={{ "intelligence": "???", "strength": "???", "speed": "???", "durability": "???", "power": "???", "combat": "???" }}
                                                        image={{ "url": "./unknown.png" }} />
                                                )
                                            }
                                        </Box>
                                    </Grid>
                                </Grid>

                            </Box>
                        ) :
                            (<div>
                                <Typography gutterBottom variant="h4" component="h2">
                                    Por favor, repartir las cartas
                            </Typography>
                            </div>)}

                        <br></br>
                        <br></br>
                        <Box component="span" display="block" bgcolor="blue">

                            <Button variant="contained" onClick={executeDuel} disabled={!executeGame} color="primary" size="large" fullWidth="true" style={{ height: "100%" }}>
                                <Typography gutterBottom variant="h4" component="h2">
                                    ¡JUGAR!
                                </Typography>
                            </Button>
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
                                    {wonCards.map((wonCard, index) => {

                                        return (
                                            <Grid item xs={12}>
                                                <Button variant="contained" onClick={setAttribute} color="primary" size="large" fullWidth="true">
                                                    {wonCard.name}
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
        </div >
    );

}


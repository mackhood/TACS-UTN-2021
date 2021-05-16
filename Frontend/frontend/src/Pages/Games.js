import React, { useState } from "react";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import getGames from "../Resources/getGames";
import { makeStyles } from "@material-ui/core/styles";
import getDecks from "../Resources/getDecks";
import getUsers from "../Resources/getUsers";
import * as _ from 'lodash';
import GameWithButtons from "../Components/GameWithButtons";
import TablePage from "./TablePage";
import { useHistory} from "react-router-dom";
import { useLocation } from "react-router-dom"
import { useRadioGroup } from "@material-ui/core";


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
}));

export default function Games() {


    let history = useHistory();
    let location = useLocation();
    const [games] = useState(getGames().data);
    const [decks] = useState(getDecks()[0].data);
    const [users] = useState(getUsers());

    const classes = useStyles();

    const userId = 1;

    function createStatisticsData(id, deckName, creatorName , challengedName) {
        return { id, deckName, creatorName, challengedName};
    }

    function createStatistics() {
        let gamesCreated = games.filter(x => x.creatorId==userId); 
        let participatedGames = games.filter(x => x.challengedId==userId); 
        let data = [];
        gamesCreated.forEach((x) => {
            data.push(
                createStatisticsData(
                    x.id, 
                    decks.filter(y => y.id == x.deckId)[0].name, 
                    users.filter(y => y.id == x.creatorId)[0].name,
                    users.filter(y => y.id == x.challengedId)[0].name
                ));
        })
        return data;
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

    const createGame = async () => {

    }

    const continueGame = async (game) => {

    }

    const dropGame = async (game) => {

    }

    const showStats = () => {
        showTable("ESTADISTICAS", ["Partida", "Mazo", "Creador", "Desafiado"], createStatistics());
    }

    const showGame = (game) => {
        showTable("PARTIDA " + game.id, ["Duelo", "Ganador"], createGameResults(game))
    }

    const showTable = async (title, tableHeaders, tableRows ) => {
        const location = {
            pathname: '/tablePage',
            state: { 
                title: title,
                tableHeaders: tableHeaders,
                tableRows: tableRows
            }
          }

        history.push(location);
    }

    return (
        <div className={classes.layout}>

            <Grid container alignItems={"center"} alignContent={"center"}>
                <div style={{ width: "100%" }}>
                    <Grid container spacing={4} alignItems={"center"} alignContent={"center"}>
                        <Grid item xs={12} sm={4}>
                            <h1>PARTIDAS</h1>
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <Grid container spacing={4} alignItems={"center"} alignContent={"center"}>
                                <Grid item xs={12} sm={4}>
                                    <Button variant="contained" color="primary" onClick={() => {
                                        createGame();
                                    }}>
                                        Crear Partida
                            </Button>
                                </Grid>
                                <Grid item xs={12} sm={4}>
                                    <Button variant="contained" color="primary" onClick={() => {
                                        showStats();
                                    }}>
                                        Ver Estadisticas
                                    </Button>                
                                </Grid>
                            </Grid>
                        </Grid>
                    </Grid>
                </div>
                {games.map((game, index) => (
                    <Grid item xs={12} sm={4} key={index}>
                        <GameWithButtons
                            game={game}
                            users={users}
                            decks={decks}
                            dropGame={dropGame}
                            showGame={showGame}
                            continueGame={continueGame}
                        />
                    </Grid>
                ))}
            </Grid>
        </div>
    );

}


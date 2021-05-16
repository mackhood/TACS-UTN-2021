import React, { useState } from "react";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import getGames from "../Resources/getGames";
import { makeStyles } from "@material-ui/core/styles";
import getDecks from "../Resources/getDecks";
import getUsers from "../Resources/getUsers";
import * as _ from 'lodash';
import GameWithButtons from "../Components/GameWithButtons";
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

    const [games] = useState(getGames().data);
    const [decks] = useState(getDecks()[0].data);
    const [users] = useState(getUsers());

    const classes = useStyles();

    const createGame = async () => {
    }

    const continueGame = async (game) => {
    }

    const dropGame = async (game) => {
    }

    const showGame = async (game) => {
    }

    const showStats = async (game) => {
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
                                        createGame();
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


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
import { useHistory } from "react-router-dom";
import { useLocation } from "react-router-dom"
import { useRadioGroup } from "@material-ui/core";
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import InputLabel from '@material-ui/core/InputLabel';
import Input from '@material-ui/core/Input';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';

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
}));

export default function Games() {


    let history = useHistory();
    let location = useLocation();
    const [games] = useState(getGames().data);
    const [decks] = useState(getDecks()[0].data);
    const [users] = useState(getUsers());

    const classes = useStyles();

    const userId = 1;

    const [open, setOpen] = React.useState(false);

    function createStatisticsData(id, deckName, creatorName, challengedName) {
        return { id, deckName, creatorName, challengedName };
    }

    function createStatistics() {
        let gamesCreated = games.filter(x => x.creatorId == userId);
        let participatedGames = games.filter(x => x.challengedId == userId);
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
        const location = {
            pathname: '/game'
        }
        history.push(location);
    }

    const continueGame = async (game) => {
        const location = {
            pathname: '/game'
        }
        history.push(location);
    
    }

    const dropGame = async (game) => {

    }

    const showStats = () => {
        showTable("/stats", "ESTADISTICAS", ["Partida", "Mazo", "Creador", "Desafiado"], createStatistics());
    }

    const showGame = (game) => {
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

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <div className={classes.layout}>
            <Dialog disableBackdropClick disableEscapeKeyDown open={open} onClose={handleClose}>
                <DialogTitle>CREAR PARTIDA</DialogTitle>
                <DialogContent>
                    <form className={classes.container}>
                        <FormControl className={classes.formControl}>
                            <InputLabel htmlFor="demo-dialog-native">Mazo</InputLabel>
                            <Select
                                labelId="demo-dialog-select-label"
                                id="demo-dialog-select"
                                input={<Input />}
                            >
                                {decks.map((deck, index) => (
                                    <MenuItem value={10}>{deck.name}</MenuItem>
                                ))}
                            </Select>
                        </FormControl>
                        <FormControl className={classes.formControl}>
                            <InputLabel id="demo-dialog-select-label">Contrincante</InputLabel>
                            <Select
                                labelId="demo-dialog-select-label"
                                id="demo-dialog-select"
                                input={<Input />}
                            >
                                {users.map((user, index) => (
                                    <MenuItem value={10}>{user.name}</MenuItem>
                                ))}
                            </Select>
                        </FormControl>
                    </form>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} color="primary">
                        Cancel
          </Button>
                    <Button onClick={createGame} color="primary">
                        Ok
          </Button>
                </DialogActions>
            </Dialog>
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

                                        handleClickOpen();
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
                <Grid container alignItems={"center"} alignContent={"center"}>
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
            </Grid>
        </div>
    );

}


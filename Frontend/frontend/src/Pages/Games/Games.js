import React, {useContext, useState} from "react";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import getGames from "../../Resources/getGames";
import getDecks from "../../Resources/getDecks";
import getUsers from "../../Resources/getUsers";
import GameWithButtons from "../../Components/GameWithButtons";
import {useHistory} from "react-router-dom";
import {CreateGameDialog} from "./CreateGameDialog";
import {commonStyles} from "../../Resources/Styles";
import {AppContext} from "../../Common/AppContext";


export default function Games(props) {


    const {setNotify} = props;
    let history = useHistory();
    const [games] = useState(getGames().data);
    const [decks] = useState(getDecks()[0].data);
    const [users] = useState(getUsers());

    const {state} = useContext(AppContext);
    console.log(state.games, 'games');

    const classes = commonStyles();

    const userId = 1;

    const [open, setOpen] = React.useState(false);

    function createStatisticsData(id, deckName, creatorName, challengedName) {
        return { id, deckName, creatorName, challengedName };
    }

    function createStatistics() {
        let gamesCreated = games.filter(x => x.creatorId === userId);
        let data = [];
        gamesCreated.forEach((x) => {
            data.push(
                createStatisticsData(
                    x.id,
                    decks.filter(y => y.id === x.deckId)[0].name,
                    users.filter(y => y.id === x.creatorId)[0].name,
                    users.filter(y => y.id === x.challengedId)[0].name
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

    const continueGame = async (game) => {
        const location = {
            pathname: '/games/' + game.id
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
            <CreateGameDialog
                handleClose={handleClose}
                open={open}
                setOpen={setOpen}
                decks={state.decks}
                users={state.users}
                setNotify={setNotify}
            />
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
                    {state.games.map((game, index) => (
                        <Grid item xs={12} sm={4} key={index}>
                            <GameWithButtons
                                game={game}
                                users={state.users}
                                decks={state.decks}
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


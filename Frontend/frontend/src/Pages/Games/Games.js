import React, {useContext, useEffect, useState} from "react";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import GameWithButtons from "../../Components/GameWithButtons";
import {useHistory} from "react-router-dom";
import {CreateGameDialog} from "./CreateGameDialog";
import {commonStyles} from "../../Resources/Styles";
import {AppContext} from "../../Common/AppContext";
import {useAuth} from "../../Auth/useAuth";
import * as _ from 'lodash';


export default function Games() {
    let history = useHistory();
    const {state} = useContext(AppContext);
    const {user} = useAuth();
    const classes = commonStyles();
    const [open, setOpen] = React.useState(false);
    const [userList, setUserList] = useState([]);

    useEffect(() => {
        let users = _.filter(state.users, (elem)=> {
            return elem.username !== user.username
        });
        setUserList(users);
    }, [state.users]);

    const continueGame = async (gameId) => {
        const location = {
            pathname: '/games/' + gameId
        }
        history.push(location);
    }

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    function navigateToDuels(id) {
        history.push('/games/' + id);
    }

    function navigateToStats() {
        history.push('/games/stats');
    }

    return (
        <div className={classes.layout}>
            <CreateGameDialog
                handleClose={handleClose}
                open={open}
                setOpen={setOpen}
                decks={state.decks}
                users={userList}
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
                                    <Button
                                        variant="contained"
                                        color="primary"
                                        onClick={navigateToStats}
                                    >
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
                                showGame={() => navigateToDuels(game.id)}
                                continueGame={continueGame}
                            />
                        </Grid>
                    ))}
                </Grid>
            </Grid>
        </div>
    );

}


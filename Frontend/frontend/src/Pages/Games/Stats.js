import React, { useContext } from "react";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import GameWithButtons from "../../Components/GameWithButtons";
import { useHistory } from "react-router-dom";
import { CreateGameDialog } from "./CreateGameDialog";
import { commonStyles } from "../../Resources/Styles";
import { AppContext } from "../../Common/AppContext";


export default function Games(props) {
    const { setNotify } = props;
    let history = useHistory();
    const { state } = useContext(AppContext);
    const classes = commonStyles();
    const [open, setOpen] = React.useState(false);

    /*
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
        history.push('/games/' + id + '/duels');
    }

    function navigateToStats() {
        history.push('/games/stats');
    }
    */

    return (
        <div className={classes.layout}>
            <Grid container alignItems={"center"} alignContent={"center"}>
                <div style={{ width: "100%" }}>
                    <Grid container spacing={4} alignItems={"center"} alignContent={"center"}>
                        <Grid item xs={12} sm={4}>
                            <Button variant="contained" color="primary" onClick={() => {
                                ;
                            }}>
                                Estadísticas de partidas
                            </Button>
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <Button variant="contained" color="primary" onClick={() => {
                                ;
                            }}>
                                Estadísticas de usuarios
                            </Button>
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <Button variant="contained" color="primary" onClick={() => {
                                ;
                            }}>
                                Scoreboard
                            </Button>
                        </Grid>
                    </Grid>
                    
                </div>
            </Grid>
        </div >
    );

}


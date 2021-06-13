import React, { useContext } from "react";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import GameWithButtons from "../../Components/GameWithButtons";
import { useHistory } from "react-router-dom";
import { CreateGameDialog } from "./CreateGameDialog";
import { commonStyles } from "../../Resources/Styles";
import { AppContext } from "../../Common/AppContext";
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import BarChart from '@material-ui/icons/BarChart';


export default function Games(props) {
    const { setNotify } = props;
    let history = useHistory();
    const { state } = useContext(AppContext);
    const classes = commonStyles();
    const [open, setOpen] = React.useState(false);

    return (
        <div className={classes.layout}>
            <h1>ESTADÍSTICAS</h1>
            <Grid container alignItems={"center"} alignContent={"center"}>
                <Grid item xs={12} sm={4}>
                    <ListItem button>
                        <ListItemIcon><BarChart /></ListItemIcon>
                        <ListItemText primary="Partidas" />

                    </ListItem>
                    <br></br>
                    <Grid container alignItems={"center"} alignContent={"center"}>
                        <Grid item xs={12} sm={1}></Grid>
                        <Grid item xs={12} sm={8}>
                            <div>
                                <p>
                                    Visualizar cantidad de partidas creadas, en curso, terminadas y canceladas permitiendo seleccionar el rango de fechas.
                                        </p>
                            </div>
                        </Grid>
                    </Grid>
                    <br></br>
                    <br></br>
                    <Grid item xs={12} sm={4}>
                        <Button variant="contained" color="primary" onClick={() => {
                            ;
                        }}>
                            Ver
                            </Button>
                    </Grid>
                </Grid>
                <br></br>
                <Grid item xs={12} sm={4}>
                    <ListItem button>
                        <ListItemIcon><BarChart /></ListItemIcon>
                        <ListItemText primary="Scoreboard" />

                    </ListItem>
                    <Grid container alignItems={"center"} alignContent={"center"}>
                        <Grid item xs={12} sm={1}></Grid>
                        <Grid item xs={12} sm={8}>
                            <div>
                                <p>
                                Visualizar ranking y puntaje total de jugadores
                                        </p>
                            </div>
                        </Grid>
                    </Grid>
                    <br></br>
                    <br></br>
                    <br></br>
                    <br></br>
                    <br></br>
                    <br></br>
                    <Grid item xs={12} sm={4}>
                        <Button variant="contained" color="primary" onClick={() => {
                            ;
                        }}>
                            Ver
                            </Button>
                    </Grid>
                </Grid>
                <br></br>
                <Grid item xs={12} sm={4} border>
                    <ListItem button>
                        <ListItemIcon><BarChart /></ListItemIcon>
                        <ListItemText primary="Jugadores" />
                    </ListItem>
                    <Grid container alignItems={"center"} alignContent={"center"}>
                        <Grid item xs={12} sm={1}></Grid>
                        <Grid item xs={12} sm={8}>
                            <div>
                                <p>
                                Visualizar un usuario específico y ver sus estadísticas individuales
                                        </p>
                            </div>
                        </Grid>
                    </Grid>
                    <br></br>
                    <br></br>
                    <br></br>
                    <br></br>
                    <br></br>
                    <Grid item xs={12} sm={4}>
                        <Button variant="contained" color="primary" onClick={() => {
                            ;
                        }}>
                            Ver
                            </Button>
                    </Grid>
                </Grid>



            </Grid>
        </div >
    );

}


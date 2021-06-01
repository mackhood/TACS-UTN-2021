import React, {useState} from "react";
import Notification from "../../Components/Notification";
import Grid from "@material-ui/core/Grid";
import {Switch} from "react-router-dom";
import {PrivateRoute} from "../../Auth/PrivateRoute";
import {commonStyles} from "../../Resources/Styles";
import Games from "./Games";
import TablePage from "./TablePage";
import Game from "./Game";
import {useRouteMatch} from "react-router";

export const UserGames = ()=>{

    const classes = commonStyles();
    let { path } = useRouteMatch();
    const [notify, setNotify] = useState({isOpen: false, message: '', type: ''});

    return (
        <div className={classes.layout}>
            <Notification notify={notify} setNotify={setNotify}/>
            <Grid container alignItems={"center"} alignContent={"center"}>
                <Switch>
                    <PrivateRoute exact path={`${path}`}>
                        <Games setNotify={setNotify}/>
                    </PrivateRoute>
                    <PrivateRoute exact path={`${path}/:id`}>
                        <Game/>
                    </PrivateRoute>
                    <PrivateRoute exact path={`${path}/:id/duels`}>
                        <TablePage/>
                    </PrivateRoute>
                    <PrivateRoute exact path={`${path}/:id/stats`}>
                        <TablePage/>
                    </PrivateRoute>
                </Switch>
            </Grid>
        </div>
    );


}
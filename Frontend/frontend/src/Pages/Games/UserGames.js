import React from "react";
import Grid from "@material-ui/core/Grid";
import {Switch} from "react-router-dom";
import {PrivateRoute} from "../../Auth/PrivateRoute";
import {commonStyles} from "../../Resources/Styles";
import Games from "./Games";
import Game from "./Game";
import {useRouteMatch} from "react-router";

export const UserGames = ()=>{

    const classes = commonStyles();
    let { path } = useRouteMatch();

    return (
        <div className={classes.layout}>
            <Grid container alignItems={"center"} alignContent={"center"}>
                <Switch>
                    <PrivateRoute exact path={`${path}`}>
                        <Games />
                    </PrivateRoute>
                    <PrivateRoute exact path={`${path}/:id`}>
                        <Game/>
                    </PrivateRoute>
                </Switch>
            </Grid>
        </div>
    );


}
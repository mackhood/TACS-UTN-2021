import React, { useState } from "react";
import Grid from "@material-ui/core/Grid";
import { Switch } from "react-router-dom";
import { PrivateRoute } from "../../Auth/PrivateRoute";
import { commonStyles } from "../../Resources/Styles";
import { useRouteMatch } from "react-router";
import StatsMenu from "./StatsMenu";
import TablePage from "./TablePage";
import GameStats from "./gameStats";
import Scoreboard from "./scoreboard";
import UserStats from "./userStats";

export const Stats = () => {

    const classes = commonStyles();
    let { path } = useRouteMatch();

    return (
        <div className={classes.layout}>
            <Grid container alignItems={"center"} alignContent={"center"}>
                <Switch>
                    <PrivateRoute exact path={`${path}`}>
                        <StatsMenu />
                    </PrivateRoute>
                    <PrivateRoute exact path={`${path}/games`}>
                        <GameStats />
                    </PrivateRoute>
                    <PrivateRoute exact path={`${path}/scoreboard`}>
                        <Scoreboard />
                    </PrivateRoute>
                    <PrivateRoute exact path={`${path}/users`}>
                        <UserStats />
                    </PrivateRoute>
                </Switch>
            </Grid>
        </div>
    );


}
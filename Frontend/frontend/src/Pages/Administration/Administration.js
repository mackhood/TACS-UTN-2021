import React from "react";
import Grid from "@material-ui/core/Grid";
import {Switch} from "react-router-dom";
import {useRouteMatch} from "react-router";
import {CreateDeck} from "./CreateDeck";
import {DisplayDecks} from "./DisplayDecks";
import {UpdateDeck} from "./UpdateDeck";
import {DisplayDeck} from "./DisplayDeck";
import {PrivateRoute} from "../../Auth/PrivateRoute";
import {commonStyles} from "../../Resources/Styles";


export default function Administration() {

    const classes = commonStyles();
    let { path } = useRouteMatch();

    return (
        <div className={classes.layout}>
            <Grid container alignItems={"center"} alignContent={"center"}>
                <Switch>
                    <PrivateRoute exact path={`${path}`}>
                        <DisplayDecks />
                    </PrivateRoute>
                    <PrivateRoute exact path={`${path}/create`}>
                        <CreateDeck />
                    </PrivateRoute>
                    <PrivateRoute exact path={`${path}/:id/update`}>
                        <UpdateDeck />
                    </PrivateRoute>
                    <PrivateRoute exact path={`${path}/:id/display`}>
                        <DisplayDeck />
                    </PrivateRoute>
                </Switch>
            </Grid>
        </div>
    );
}
import React, { useState } from "react";
import Grid from "@material-ui/core/Grid";
import { Switch } from "react-router-dom";
import { useRouteMatch } from "react-router";
import { CreateDeck } from "./CreateDeck";
import { DisplayDecks } from "./DisplayDecks";
import Notification from "../../Components/Notification";
import { UpdateDeck } from "./UpdateDeck";
import { DisplayDeck } from "./DisplayDeck";
import { PrivateRoute } from "../../Auth/PrivateRoute";
import { commonStyles } from "../../Resources/Styles";



export default function Administration() {

    const classes = commonStyles();
    const [notify, setNotify] = useState({ isOpen: false, message: '', type: '' });
    let { path } = useRouteMatch();

    return (
        <div className={classes.layout}>
            <Notification notify={notify} setNotify={setNotify} />
            <Grid container alignItems={"center"} alignContent={"center"}>
                <Switch>
                    <PrivateRoute exact path={`${path}`}>
                        <DisplayDecks
                            setNotify={setNotify}
                        />
                    </PrivateRoute>
                    <PrivateRoute exact path={`${path}/create`}>
                        <CreateDeck
                            setNotify={setNotify}
                        />
                    </PrivateRoute>
                    <PrivateRoute exact path={`${path}/:id/update`}>
                        <UpdateDeck
                            setNotify={setNotify}
                        />
                    </PrivateRoute>
                    <PrivateRoute exact path={`${path}/:id/display`}>
                        <DisplayDeck />
                    </PrivateRoute>


                </Switch>
            </Grid>
        </div>
    );
}
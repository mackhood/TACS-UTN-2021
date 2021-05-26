import React, {useContext, useState} from "react";
import Grid from "@material-ui/core/Grid";
import {makeStyles} from "@material-ui/core/styles";
import {Route, Switch} from "react-router-dom";
import {useRouteMatch} from "react-router";
import {AppContext, AppContextProvider} from "../../Common/AppContext";
import {CreateDeck} from "./CreateDeck";
import {DisplayDecks} from "./DisplayDecks";
import Notification from "../../Components/Notification";
import {UpdateDeck} from "./UpdateDeck";

const useStyles = makeStyles((theme) => ({
    layout:{
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
}));

export default function Administration() {

    const classes = useStyles();
    const [notify, setNotify] = useState({isOpen: false, message: '', type: ''});
    let { path } = useRouteMatch();
    
    return (
        <AppContextProvider>
            <div className={classes.layout}>
                <Notification notify={notify} setNotify={setNotify}/>
                <Grid container alignItems={"center"} alignContent={"center"}>
                    <Switch>
                        <Route exact path={`${path}`}>
                            <DisplayDecks
                                setNotify={setNotify}
                            />
                        </Route>
                        <Route exact path={`${path}/create`}>
                            <CreateDeck
                                setNotify={setNotify}
                            />
                        </Route>
                        <Route exact path={`${path}/:id/update`}>
                            <UpdateDeck
                                setNotify={setNotify}
                            />
                        </Route>
                    </Switch>
                </Grid>
            </div>
        </AppContextProvider>
    );
}
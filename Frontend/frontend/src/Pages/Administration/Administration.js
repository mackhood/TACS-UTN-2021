import React, {useState} from "react";
import Grid from "@material-ui/core/Grid";
import {makeStyles} from "@material-ui/core/styles";
import {Route, Switch} from "react-router-dom";
import {useRouteMatch} from "react-router";
import {AppContextProvider} from "../../Common/AppContext";
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
                            <DisplayDecks/>
                        </Route>
                        <Route exact path={`${path}/create`}>
                            <CreateDeck
                                setNotify={setNotify}
                            />
                        </Route>
                        <Route exact path={`${path}/update`}>

                        </Route>
                        <Route exact path={`${path}/:id`}>
                            <UpdateDeck
                                setNotify={setNotify}
                            />
                        </Route>
                    </Switch>

                </Grid>
                {/*<Grid container spacing={4} alignItems={"center"} alignContent={"center"}>*/}
                    {/*<Grid item xs={12}>*/}
                    {/*    <Button variant="contained" color="primary" disabled={right.length === 0 || deckName.length === 0} onClick={() => {*/}
                    {/*        creating ? createDeck(right) : updateDeck(right);*/}
                    {/*        setCreating(true);*/}
                    {/*    }}>*/}
                    {/*        {creating ? 'Crear Mazo' : 'Actualizar Mazo'}*/}
                    {/*    </Button>*/}
                    {/*</Grid>*/}

                {/*</Grid>*/}

            </div>
        </AppContextProvider>
    );
}
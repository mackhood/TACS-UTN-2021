import React, { useState } from "react";
import Grid from "@material-ui/core/Grid";
import getGames from "../Resources/getGames";
import { makeStyles } from "@material-ui/core/styles";
import getDecks from "../Resources/getDecks";
import getUsers from "../Resources/getUsers";
import * as _ from 'lodash';
import { useHistory } from "react-router-dom";
import { useLocation } from "react-router-dom"
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Divider from "@material-ui/core/Divider";
import { AppBar } from "@material-ui/core";
import { Drawer } from '@material-ui/core';
import Box from '@material-ui/core/Box';

const { customAlphabet } = require('nanoid')

const useStyles = makeStyles((theme) => ({
    layout: {
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
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    formControl: {
        margin: theme.spacing(1),
        minWidth: 120,
    },
    turnPaper: {
        width: 400,
        height: 300,
        overflow: 'auto',
    },
    attsPaper: {
        width: 400,
        height: 600,
        overflow: 'auto',
    },
    deckPaper: {
        width: 800,
        height: 400,
        overflow: 'auto',
    },
    cardsPaper: {
        width: 800,
        height: 400,
        overflow: 'auto',
    },
    playButtonPaper: {
        width: 800,
        height: 100,
        overflow: 'auto',
    },
    duelsPaper: {
        width: 400,
        height: 600,
        overflow: 'auto',
    },
    wonCartsPaper: {
        width: 400,
        height: 300,
        overflow: 'auto',
    },
}));

export default function Game() {


    let history = useHistory();
    let location = useLocation();
    const [games] = useState(getGames().data);
    const [decks] = useState(getDecks()[0].data);
    const [users] = useState(getUsers());

    const classes = useStyles();

    const userId = 1;

    return (
        <div  style={{ width: '100%' }}>
            <Box component="div" display="inline" p={1} m={1}  bgcolor="background.paper">
                <Box component="span" display="block">
                    <Typography gutterBottom variant="h5" component="h2">
                        TURNO
                                </Typography>
                </Box>
                <br></br>
                <br></br>
                <Box component="span" display="block">
                    <Typography gutterBottom variant="h5" component="h2">
                        ATRIBUTOS
                                </Typography>
                </Box>
            </Box>

            <Box component="div" display="inline" p={1} m={1}  bgcolor="background.paper">
                <Box component="span" display="block">
                    <Typography gutterBottom variant="h5" component="h2">
                        MAZO
                                </Typography>
                </Box>
                <br></br>
                <br></br>
                <Box component="span" display="block">
                    <Typography gutterBottom variant="h5" component="h2">
                        CARTAS
                                </Typography>
                </Box>
                <br></br>
                <br></br>
                <Box component="span" display="block">
                    <Typography gutterBottom variant="h5" component="h2">
                        JUGAR!
                                </Typography>
                </Box>
            </Box>

            <Box component="div" display="inline" p={1} m={1}  bgcolor="background.paper"   >
                <Box component="span" display="block">
                    <Typography gutterBottom variant="h5" component="h2">
                        DUELOS
                                </Typography>
                </Box>
                <br></br>
                <br></br>
                <Box component="span" display="block">
                    <Typography gutterBottom variant="h5" component="h2">
                        CARTAS GANADAS
                                </Typography>
                </Box>
            </Box>

        </div>
    );

}


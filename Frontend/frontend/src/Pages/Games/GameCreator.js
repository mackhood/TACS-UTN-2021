import React, {useState} from "react";
import Grid from "@material-ui/core/Grid";
import getGames from "../../Resources/getGames";
import {makeStyles} from "@material-ui/core/styles";
import getDecks from "../../Resources/getDecks";
import getUsers from "../../Resources/getUsers";
import {useHistory, useLocation} from "react-router-dom";
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import InputLabel from '@material-ui/core/InputLabel';

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
}));

const customStyles = {
    option: (provided, state) => ({
        ...provided,
        borderBottom: '1px dotted pink',
        color: state.isSelected ? 'red' : 'blue',
        padding: 20,
    }),
    control: () => ({
        // none of react-select's styles are passed to <Control />
        width: 200,
    }),
    singleValue: (provided, state) => {
        const opacity = state.isDisabled ? 0.5 : 1;
        const transition = 'opacity 300ms';

        return { ...provided, opacity, transition };
    }
}

export default function GameCreator() {


    let history = useHistory();
    let location = useLocation();
    const [games] = useState(getGames().data);
    const [decks] = useState(getDecks()[0].data);
    const [users] = useState(getUsers());

    const classes = useStyles();

    const userId = 1;

    const options = [
        { value: "Spring", label: "Spring", color: "#498205" },
        { value: "Summer", label: "Summer", color: "#c19c00" },
        { value: "Autumn", label: "Autumn", color: "#da3b01" },
        { value: "Winter", label: "Winter", color: "#004e8c" }
    ];

    const styles = {
        option: (provided, state) => ({
            ...provided,
            fontWeight: state.isSelected ? "bold" : "normal",
            color: "white",
            backgroundColor: state.data.color,
            fontSize: state.selectProps.myFontSize
        }),
        singleValue: (provided, state) => ({
            ...provided,
            color: state.data.color,
            fontSize: state.selectProps.myFontSize
        })
    };


    return (
        <div className={classes.layout}>
            <Grid container alignItems={"center"} alignContent={"center"}>
                <div style={{ width: "100%" }}>
                    <Grid container spacing={4} alignItems={"center"} alignContent={"center"}>
                        <Grid item xs={12} sm={4}>
                            <h1>PARTIDAS</h1>
                        </Grid>
                    </Grid>
                </div>
                <div>
                    <Grid container spacing={4} alignItems={"center"} alignContent={"center"}>
                        <Grid item xs={12} sm={4}>
                            <Grid item xs={12} sm={4}>
                                <h1>CONTRINCANTE</h1>
                            </Grid>
                            <Select
                                labelId="demo-simple-select-label"
                                id="demo-simple-select"
                            >
                                
                                    {users.map((user, index) => (
                                        <MenuItem value={10}>{user.username}</MenuItem>
                                    ))}
                                
                            </Select>
                        </Grid>
                    </Grid>
                    <Grid container spacing={4} alignItems={"center"} alignContent={"center"}>
                        <Grid item xs={12} sm={4}>
                            <Grid item xs={12} sm={4}>
                                <h1>MAZO</h1>
                            </Grid>
                            <InputLabel id="demo-mutiple-chip-label">Chip</InputLabel>
                            <Select
                                labelId="demo-simple-select-label"
                                id="demo-simple-select"
                                value="Elegir Mazo"
                            >
                                {decks.map((deck, index) => (
                                    <MenuItem value={10}>{deck.name}</MenuItem>
                                ))} 
                            </Select>
                        </Grid>
                    </Grid>
                </div>

            </Grid>
        </div>
    );

}



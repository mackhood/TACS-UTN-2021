import React from "react";
import Button from "@material-ui/core/Button";
import {useAuth} from "./useAuth";
import {useHistory} from "react-router-dom";
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
}));

export const LoginUserButton = (props) => {
    const {user} = props;
    let auth = useAuth();
    let history = useHistory();

    const callback = () => {
        history.push('/admin/decks');
    };
    let login = (e) => {
        e.preventDefault();
        auth.login({user, callback});
    };
    const classes = useStyles();
    return (
        <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
            onClick={login}
            disabled={user.username === "" || user.password === ""}
        >
            Login
        </Button>
    )
}
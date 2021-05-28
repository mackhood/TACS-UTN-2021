import React from "react";
import {useAuth} from "./useAuth";
import {useHistory} from "react-router-dom";
import Button from "@material-ui/core/Button";
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
}));

export const RegisterUserButton = (props) => {
    const {user} = props;
    let auth = useAuth();
    let history = useHistory();

    const callback = () => {
        history.push('/login');
    };

    let register = (e) => {
        e.preventDefault();
        auth.register({user, callback})
            .catch(err => console.log(err, 'err'));
    };
    const classes = useStyles();

    return (
        <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
            onClick={register}
            disabled={user.username === "" || user.password === "" || user.email === ""}
        >
            Registrarme
        </Button>
    )
}
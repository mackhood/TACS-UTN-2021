import React, {useContext} from "react";
import {useAuth} from "./useAuth";
import {useHistory} from "react-router-dom";
import Button from "@material-ui/core/Button";
import {makeStyles} from "@material-ui/core/styles";
import {NotifyContext} from "../Common/NotifyContextProvider";

const useStyles = makeStyles((theme) => ({
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
}));

export const RegisterUserButton = (props) => {
    const {user} = props;
    let auth = useAuth();
    const {setNotify} = useContext(NotifyContext);
    let history = useHistory();

    const callback = () => {
        history.push('/login');
    };

    let register = (e) => {
        e.preventDefault();
        auth.register(user)
            .then(() => {
                setNotify({ isOpen: true, message: 'Registro exitoso', type: 'success', duration: 3000 });
                history.push('/login');
            })
            .catch(err => {
                console.log(err, 'err');
                setNotify({ isOpen: true, message: 'No se pudo realizar el registro', type: 'error', duration: 3000 });
            });
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
import React, {useState} from 'react';
import Avatar from '@material-ui/core/Avatar';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import SportsEsportsIcon from '@material-ui/icons/SportsEsports';
import Typography from '@material-ui/core/Typography';
import {makeStyles} from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import {useHistory} from 'react-router-dom';
import {LoginUserButton} from "../Auth/LoginUserButton";

function Copyright() {
    return (
        <Typography variant="body2" color="textSecondary" align="center">
            {'Copyright © '}
            <Link color="inherit" href="#">
                TACS-GRUPO 1
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

const useStyles = makeStyles((theme) => ({
    paper: {
        marginTop: theme.spacing(8),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.primary.contrastText,
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(1),
    }
}));

export default function Login() {
    const classes = useStyles();

    const [user, setUser] = useState({username: "", password:""});
    let history = useHistory();

    const handleInputChange = (e) => {
        const name = e.target.name;
        const value = e.target.value;

        setUser({
            ...user,
            [name]:value
        });
    }

    function navigateToRegister() {
        history.push('/register');
    }

    return (
        <Container component="main" maxWidth="xs">
            <CssBaseline />
            <div className={classes.paper}>
                <Avatar className={classes.avatar}>
                    <SportsEsportsIcon color={"primary"} />
                </Avatar>
                <Typography component="h1" variant="h5">
                    Crommy
                </Typography>
                <form className={classes.form} noValidate>
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        label="Username"
                        name="username"
                        onChange={handleInputChange}
                    />
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label="Contraseña"
                        type="password"
                        autoComplete="password"
                        onChange={handleInputChange}
                    />
                    <LoginUserButton
                        user={user}
                    />
                    <Grid container>
                        <Grid item xs>
                            <Link href="#" variant="body2">
                                Olvidaste tu contraseña?
                            </Link>
                        </Grid>
                        <Grid item>
                            <Link variant="body2" onClick={navigateToRegister}>
                                {"No tenes cuenta todavía? Registrate"}
                            </Link>
                        </Grid>
                    </Grid>
                </form>
            </div>
            <Box mt={8}>
                <Copyright />
            </Box>
        </Container>
    );
}
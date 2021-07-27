import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles({
    root: {
        maxWidth: 345,
        borderStyle: 'solid',
        borderColor: 'black',
        borderWidth: 1
    },
    media: {
        height: 300,
    },
});

export default function GameWithButtons(props) {
    const classes = useStyles();

    const {game, showGame, continueGame} = props;

    return (
        <Card className={classes.root}>
            <CardActionArea>
                    <CardMedia
                        component="img"
                        className={classes.media}
                        image="./finished.png"
                        title={game.name ? game.name : "Nombre" }
                    />
                <CardContent>                    
                    <div>
                    <Typography gutterBottom variant="h5" component="h2">
                            { game.status }
                        </Typography>
                        <br/>
                        <Typography gutterBottom>
                            Partida: { game.id }          
                        </Typography>
                        <Typography gutterBottom>
                            Deck:  { game.deckName }
                        </Typography>
                        <Typography gutterBottom>
                            Creador: {game.creator.username}
                        </Typography>
                        <Typography gutterBottom>
                            Desafiado: {game.challenged.username}
                        </Typography>
                    </div>
                </CardContent>
            </CardActionArea>
            <CardActions>
                {
                    (game.state === "INPROGRESS" || game.state === "CREATED") &&
                        (
                            <>
                                <Button size="small" color="primary" onClick={() => {continueGame(game.id)}}>
                                    Continuar
                                </Button>
                                <Button size="small" color="primary" onClick={() => {}}>
                                    Abandonar
                                </Button>
                            </>
                    )
                }
                {
                    (game.state === "FINISHED") && (
                        <Button size="small" color="primary" onClick={() => {showGame(game)}}>
                            Ver
                        </Button>
                    )
                }
            </CardActions>
        </Card>
    );

}

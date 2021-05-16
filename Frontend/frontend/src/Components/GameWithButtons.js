import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import Grid from "@material-ui/core/Grid";

const useStyles = makeStyles({
    root: {
        maxWidth: 345,
    },
    media: {
        height: 200,
    },
});

export default function GameWithButtons(props) {
    const classes = useStyles();

    const {game, users, decks, dropGame, showGame, continueGame} = props;
    
    return (
        <Card className={classes.root}>
            <CardActionArea>
                    <CardMedia
                        component="img"
                        className={classes.media}
                        image={"./" + game.gameStatus + ".png" }
                        title={game.name ? game.name : "Nombre" }
                        objectFit= "cover"
                    />
                <CardContent>                    
                    <div>
                    <Typography gutterBottom>
                            { game.gameStatus }
                        </Typography>
                        <br></br>
                        <Typography gutterBottom>
                            Partida: { game.id }          
                        </Typography>
                        <Typography gutterBottom>
                            Deck:  { decks.filter(x => x.id == game.deckId)[0].name }
                        </Typography>
                        <Typography gutterBottom>
                            Jugador creador: { users.filter(x => x.id == game.creatorId)[0].name }
                        </Typography>
                        <Typography gutterBottom>
                            Jugador desafiado: { users.filter(x => x.id == game.challengedId)[0].name }
                        </Typography>
                    </div>
                </CardContent>
            </CardActionArea>
            {(game.gameStatus == "New") &&   
            <CardActions>
                <Button size="small" color="primary" onClick={() => {continueGame(game)}}>
                    Continuar
                </Button>
            </CardActions>}
            {(game.gameStatus == "InProgress") &&   
            <CardActions>
                <Button size="small" color="primary" onClick={() => {showGame(game)}}>
                    Ver
                </Button>
                <Button size="small" color="primary" onClick={() => {continueGame(game)}}>
                    Continuar
                </Button>
                <Button size="small" color="primary" onClick={() => {dropGame(game)}}>
                    Abandonar
                </Button>            
            </CardActions>}
            {(game.gameStatus == "Finished") &&   
            <CardActions>
                <Button size="small" color="primary" onClick={() => {showGame(game)}}>
                    Ver
                </Button>            
            </CardActions>}
        </Card>
    );

}

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

    const {game, users, decks, dropGame, showGame, continueGame} = props;
    
    return (
        <Card className={classes.root}>
            <CardActionArea>
                    <CardMedia
                        component="img"
                        className={classes.media}
                        image="./finished.png"
                        title={game.name ? game.name : "Nombre" }
                        objectFit= "cover"
                    />
                <CardContent>                    
                    <div>
                    <Typography gutterBottom variant="h5" component="h2">
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
                            Creador: {game.creator.username}
                        </Typography>
                        <Typography gutterBottom>
                            Desafiado: {game.challenged.username}
                        </Typography>
                    </div>
                </CardContent>
            </CardActionArea>
            {(game.gameStatus === "New") &&
            <CardActions>
                <Button size="small" color="primary" onClick={() => {continueGame(game.id)}}>
                    Continuar
                </Button>
            </CardActions>}
            {(game.gameStatus === "InProgress") &&
            <CardActions>
                <Button size="small" color="primary" onClick={() => {showGame(game)}}>
                    Ver
                </Button>
                <Button size="small" color="primary" onClick={() => {continueGame(game.id)}}>
                    Continuar
                </Button>
                <Button size="small" color="primary" onClick={() => {dropGame(game.id)}}>
                    Abandonar
                </Button>            
            </CardActions>}
            {(game.gameStatus === "Finished") &&
            <CardActions>
                <Button size="small" color="primary" onClick={() => {showGame(game)}}>
                    Ver
                </Button>            
            </CardActions>}
        </Card>
    );

}

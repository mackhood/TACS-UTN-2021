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
    },
    media: {
        height: 200,
    },
});

export default function DeckCardWithButtons(props) {
    const classes = useStyles();

    const {deck, navigateToUpdate} = props;

    return (
        <Card className={classes.root}>
            <CardActionArea>
                <CardMedia
                    className={classes.media}
                    image="/cartascromy.webp"
                    title={deck.name ? deck.name : "Nombre" }
                />
                <CardContent>
                    <Typography gutterBottom variant="h5" component="h2">
                        {deck.name ? deck.name : "Nombre" }
                    </Typography>
                </CardContent>
            </CardActionArea>
            <CardActions>
                <Button size="small" color="primary" onClick={() => {}}>
                    Ver
                </Button>
                <Button size="small" color="primary" onClick={() => navigateToUpdate(deck.id)}>
                    Modificar
                </Button>
                <Button size="small" color="primary" onClick={() => {}}>
                    Eliminar
                </Button>
            </CardActions>
        </Card>
    );
}

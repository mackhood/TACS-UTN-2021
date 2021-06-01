import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles({
    root: {
        // maxWidth: 345,
    },
    media: {
        height: 150,
    },
});
export default function HeroeCard(props) {
    const classes = useStyles();
    const {name, powerstats, image} = props;

    return (
        <Card className={classes.root}>
            <CardActionArea>
                <CardMedia
                    className={classes.media}
                    image={"https://www.superherodb.com/pictures2/portraits/10/100/10060.jpg"}
                    title={name}
                />
                <CardContent>
                    <Typography gutterBottom variant="h5" component="h2">
                        {name}
                    </Typography>
                    {Object.keys(powerstats).map((keyName, index) => (
                        <Typography key={index} variant="body2" color="textPrimary" component="p">
                            <strong>{keyName.toString()}</strong>{": " + powerstats[keyName].toString()}
                        </Typography>
                    ))}
                </CardContent>
            </CardActionArea>
        </Card>
    );
}

import * as PropTypes from "prop-types";
import Box from "@material-ui/core/Box";
import React from "react";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";


export function CardsWonLabel(props) {
    return <Box component="span" display="block" bgcolor="orange">
        <Typography gutterBottom variant="h4" component="h2">
            CARTAS GANADAS:
        </Typography>
        <Grid container alignItems={"center"} alignContent={"center"} justify="center">
            <Box component="span" display="block" bgcolor="orange" width="75%">
                {props.sessionUser.gainedCards}
            </Box>
        </Grid>
    </Box>;
}

CardsWonLabel.propTypes = {sessionUser: PropTypes.shape({})};
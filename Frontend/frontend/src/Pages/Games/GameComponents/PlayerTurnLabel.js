import * as PropTypes from "prop-types";
import Box from "@material-ui/core/Box";
import React from "react";
import {DropGameButton} from "../../../Api/Effects/DropGameButton";
import Typography from "@material-ui/core/Typography";

export function PlayersTurnLabel(props) {
    return <Box component="span" display="block" bgcolor="green">
        <Typography gutterBottom variant="h4" component="h2">
            TURNO DE JUGADOR
        </Typography>
        <Typography gutterBottom variant="h5" component="h2">
            {props.jugadorTurno}
        </Typography>
        <DropGameButton game={props.game}/>
        <br/>
        <br/>
    </Box>;
}

PlayersTurnLabel.propTypes = {
    jugadorTurno: PropTypes.any,
    game: PropTypes.any
};
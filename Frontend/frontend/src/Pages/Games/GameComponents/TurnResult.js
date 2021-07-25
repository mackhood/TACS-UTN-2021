import Box from "@material-ui/core/Box";
import Typography from "@material-ui/core/Typography";
import * as PropTypes from "prop-types";
import React from "react";

export const TurnResult= (props) => {
    const {result} = props;
    return (result !== null && <Box component="span" display="block" bgcolor="green">
        <Typography gutterBottom variant="h4" component="h2">
            Resultado del turno
        </Typography>
        <Typography gutterBottom variant="h5" component="h2">
            {(result && result.result !== "DRAW") ? 'Ganador: ' + result.winner : "Empate"}
        </Typography>
        <br></br>
        <br></br>
    </Box>);
}

TurnResult.propTypes = {
    currentDuel: PropTypes.shape({
        result: PropTypes.any,
        challengedCard: PropTypes.shape({
            name: PropTypes.string,
            powerstats: PropTypes.shape({
                strength: PropTypes.number,
                durability: PropTypes.number,
                combat: PropTypes.number,
                power: PropTypes.number,
                speed: PropTypes.number,
                intelligence: PropTypes.number
            }),
            id: PropTypes.number
        }),
        creatorCard: PropTypes.shape({
            name: PropTypes.string,
            powerstats: PropTypes.shape({
                strength: PropTypes.number,
                durability: PropTypes.number,
                combat: PropTypes.number,
                power: PropTypes.number,
                speed: PropTypes.number,
                intelligence: PropTypes.number
            }),
            id: PropTypes.number
        }),
        attribute: PropTypes.any
    }),
    props: PropTypes.any
};
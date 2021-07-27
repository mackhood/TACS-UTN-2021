import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import React from "react";

export const NextTurnButton = (props) => {
    const {disabled, getNextTurn, currentDuel} = props;
    return (
        <Button
            variant="contained"
            disabled={!disabled}
            color="primary"
            size="large"
            fullWidth
            onClick={()=>getNextTurn(currentDuel)}
        >
            <Typography gutterBottom variant="h4" component="h2">
                Siguiente turno
            </Typography>
        </Button>
    )
}
export const PrevTurnButton = (props) => {
    const {disabled, getPrevTurn, currentDuel} = props;
    return (
        <Button
            variant="contained"
            disabled={!disabled}
            color="primary"
            size="large"
            fullWidth
            onClick={()=>getPrevTurn(currentDuel)}
        >
            <Typography gutterBottom variant="h4" component="h2">
                Turno Anterior
            </Typography>
        </Button>
    )
}
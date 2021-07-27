import * as PropTypes from "prop-types";
import Box from "@material-ui/core/Box";
import React from "react";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";

export function ShuffleCardsButton(props) {
    return <Box component="span" display="block" bgcolor="green" height="500">
        <Typography gutterBottom variant="h4" component="h2">
            MAZO
        </Typography>
        <Grid container alignItems={"center"} alignContent={"center"} justify="center">
            <Box component="span" display="block" bgcolor="orange" width="75%">
                <Grid item xs={12}>
                    <Button
                        variant="contained"
                        disabled={!props.enableGame}
                        onClick={props.onClick}
                        color="primary"
                        size="large"
                        fullWidth
                    >
                        Repartir cartas
                    </Button>
                </Grid>
            </Box>
            <br/>
            <br/>
            <br/>
        </Grid>
    </Box>;
}

ShuffleCardsButton.propTypes = {
    enableGame: PropTypes.bool,
    onClick: PropTypes.func
};
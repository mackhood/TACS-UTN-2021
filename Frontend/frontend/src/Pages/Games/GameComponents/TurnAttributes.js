import * as PropTypes from "prop-types";
import Box from "@material-ui/core/Box";
import React from "react";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";

export function TurnAttributes(props) {
    return <Box component="span" display="block" bgcolor="orange">
        <Typography gutterBottom variant="h4" component="h2">
            ATRIBUTOS
        </Typography>
        <Grid container alignItems={"center"} alignContent={"center"} justify="center">
            <Box component="span" display="block" bgcolor="orange" width="75%">
                {props.strings.map(props.callbackfn)}
            </Box>
        </Grid>
    </Box>;
}

TurnAttributes.propTypes = {
    strings: PropTypes.arrayOf(PropTypes.string),
    callbackfn: PropTypes.func
};
import * as PropTypes from "prop-types";
import Box from "@material-ui/core/Box";
import React from "react";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";

export function SeeDuels(props) {
    return <Box component="span" display="block" bgcolor="green" height="500">
        <Typography gutterBottom variant="h4" component="h2">
            DUELOS
        </Typography>
        <Grid container alignItems={"center"} alignContent={"center"} justify="center">
            <Box component="span" display="block" bgcolor="orange" width="75%">
                <Grid item xs={12}>
                    <Button variant="contained" onClick={props.onClick} color="primary"
                            size="large" fullWidth>
                        Ver duelos
                    </Button>
                </Grid>
            </Box>
            <br></br>
            <br></br>
            <br></br>
        </Grid>
    </Box>;
}

SeeDuels.propTypes = {onClick: PropTypes.func};
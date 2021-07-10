import Box from "@material-ui/core/Box";
import Typography from "@material-ui/core/Typography";
import * as PropTypes from "prop-types";
import React from "react";

export function AttributeLabel(props) {
    return <Box component="span" display="block" bgcolor="green">
        <Typography gutterBottom variant="h4" component="h2">
            Atributo:
        </Typography>
        <Typography gutterBottom variant="h5" component="h2">
            {!props.enableGame ? "Aguarda tu turno" : props.atributoEnJuego}
        </Typography>
    </Box>;
}

AttributeLabel.propTypes = {
    enableGame: PropTypes.bool,
    atributoEnJuego: PropTypes.string
};
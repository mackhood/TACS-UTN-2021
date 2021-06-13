import React, {useContext} from "react";
import Button from "@material-ui/core/Button";
import CommonService from "../CommonService";
import {useAuth} from "../../Auth/useAuth";
import {AppContext} from "../../Common/AppContext";
import Typography from "@material-ui/core/Typography";

export const PlayDuelButton = (props) => {
    const {game, disabled, attribute} = props;
    const {user} = useAuth();
    const {dispatch} = useContext(AppContext);
    const handlePlayDuel = (data) => {
        CommonService.postDuel({
            id: data.id,
            attribute: data.attribute
        }, user.token)
            .then(res => {
                dispatch({type:"ADD_DUEL", payload: res.data});
            })
            .catch(err => console.log(err, 'err'));
    }
    return (
        <Button
            variant="contained"
            onClick={() => handlePlayDuel({id: game.id, attribute: attribute})}
            disabled={disabled}
            color="primary"
            size="large"
            fullWidth
            style={{ height: "100%" }}
        >
            <Typography gutterBottom variant="h4" component="h2">
                ¡JUGAR!
            </Typography>
        </Button>
    )
}
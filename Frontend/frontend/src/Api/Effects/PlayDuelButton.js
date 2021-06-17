import React, {useContext} from "react";
import Button from "@material-ui/core/Button";
import CommonService from "../CommonService";
import {useAuth} from "../../Auth/useAuth";
import {AppContext} from "../../Common/AppContext";
import Typography from "@material-ui/core/Typography";
import {NotifyContext} from "../../Common/NotifyContextProvider";

export const PlayDuelButton = (props) => {
    const {game, disabled, attribute} = props;
    const {user} = useAuth();
    const {dispatch} = useContext(AppContext);
    const {setNotify} = useContext(NotifyContext);
    const handlePlayDuel = (data) => {
        CommonService.postDuel({
            id: data.id,
            attribute: data.attribute
        }, user.token)
            .then(res => {
                dispatch({type:"ADD_DUEL", payload: res.data});
                setNotify({isOpen:true, message:'Nueva partida creada', type:'success', duration: 3000})
            })
            .catch(err => {
                console.log(err, 'err');
                setNotify({isOpen:true, message:'No se pudo crear la partida', type:'error', duration: 3000})
            });
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
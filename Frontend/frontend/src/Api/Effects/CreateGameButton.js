import React, {useContext} from "react";
import {AppContext} from "../../Common/AppContext";
import {useAuth} from "../../Auth/useAuth";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import CommonService from "../CommonService";
import {NotifyContext} from "../../Common/NotifyContextProvider";

export const CreateGameButton = (props) => {
    const {dispatch} = useContext(AppContext);
    const {data, resetDialog, handleClose} = props;
    const {user} = useAuth();
    const {setNotify} = useContext(NotifyContext);
    function handleCreateGame() {
        CommonService.createGame({
            creatorUsername: user.username,
            challengedUsername: data.user,
            deckID: data.deck
            }, user.token
        ).then(r =>{
            dispatch({
                type:"ADD_GAME",
                payload: r.data
            });
            handleClose();
            resetDialog();
            setNotify({isOpen:true, message:'Nueva partida creada', type:'success'});
        })
            .catch((err) => {
                console.log(err, 'new game');
                handleClose();
                setNotify({isOpen:true, message:'La partida no pudo ser creada', type:'error'})
            });
    }
    return (
        <Grid item xs={12}>
            <Button onClick={handleCreateGame} color="primary">
                Crear
            </Button>
        </Grid>
    )
}
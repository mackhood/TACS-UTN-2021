import React, {useContext} from "react";
import Button from "@material-ui/core/Button";
import CommonService from "../CommonService";
import {AppContext} from "../../Common/AppContext";
import {NotifyContext} from "../../Common/NotifyContextProvider";

export const DropGameButton = (props) => {
    const {game} = props;
    const {dispatch} = useContext(AppContext);
    const {setNotify} = useContext(NotifyContext);
    const handleDropGame = () => {
        CommonService.dropGame(game.id)
            .then(res => {
                dispatch({type:"END_GAME", payload: res.data});
                setNotify({isOpen:true, message:'Juego finalizado', type:'success', duration: 3000});
            })
            .catch((err) => {
                setNotify({isOpen:true, message:'No se pudo finalizar el juego', type:'error', duration: 3000})
            });
    }
    return (
        <Button variant="contained" onClick={() => handleDropGame(game)} color="primary" size="medium">
            Abandonar Partida
        </Button>
    )
}
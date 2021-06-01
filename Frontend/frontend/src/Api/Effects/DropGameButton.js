import React, {useContext} from "react";
import Button from "@material-ui/core/Button";
import CommonService from "../CommonService";
import {useAuth} from "../../Auth/useAuth";
import {AppContext} from "../../Common/AppContext";

export const DropGameButton = (props) => {
    const {game} = props;
    const {user} = useAuth();
    const {dispatch} = useContext(AppContext);
    const handleDropGame = () => {
        CommonService.dropGame(game.id, user.token)
            .then(res => {
                dispatch({type:"END_GAME", payload: res.data});
            })
            .catch();
    }
    return (
        <Button variant="contained" onClick={() => handleDropGame(game)} color="primary" size="medium">
            Abandonar Partida
        </Button>
    )
}
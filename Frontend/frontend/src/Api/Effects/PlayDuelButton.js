import React, {useContext} from "react";
import Button from "@material-ui/core/Button";
import CommonService from "../CommonService";
import Typography from "@material-ui/core/Typography";
import {NotifyContext} from "../../Common/NotifyContextProvider";
import * as _ from "lodash";

export const PlayDuelButton = (props) => {
    const {game, disabled, attribute, setGame} = props;
    const {setNotify} = useContext(NotifyContext);

    async function fetchGameData(data) {
        try{
            return await CommonService.getSingleGame({id: data.gameId});
        }catch (e) {
            console.log(e, 'err');
        }
    }
    const handlePlayDuel = (data) => {
        CommonService.postDuel({
            id: data.game.id,
            attribute: data.attribute
        })
        .then(response => {
            fetchGameData({gameId: data.game.id})
                .then((res) => {
                    let newDuels =  game.duels || [];
                    newDuels = _.union(newDuels, [response.data]);
                    setGame({
                        ...game,
                        duels: newDuels,
                        state: res.data.state,
                        stateCode: res.data.stateCode,
                        creator: res.data.creator,
                        challenged: res.data.challenged
                    });
                });
            setNotify({isOpen:true, message:'¡Duelo exitoso!', type:'success', duration: 3000})
        })
        .catch(err => {
            console.log(err, 'err');
            setNotify({isOpen:true, message:'No se pudo jugar el duelo', type:'error', duration: 3000})
        });
    }
    return (
        <Button
            variant="contained"
            onClick={() => handlePlayDuel({...game, attribute: attribute})}
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
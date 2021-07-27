import React, {useContext} from "react";
import AdminService from "../AdminService";
import {AppContext} from "../../Common/AppContext";
import Button from "@material-ui/core/Button";
import {useConfirm} from "material-ui-confirm";
import {NotifyContext} from "../../Common/NotifyContextProvider";

export const DeleteDeckButton = (props) => {

    const confirm = useConfirm();
    const {dispatch} = useContext(AppContext);
    const {deckId} = props;
    const {setNotify} = useContext(NotifyContext);

    function handleDeleteDeck() {

        confirm({ description: 'Desea eliminar el mazo?' })
            .then(() => {
                AdminService.deleteDeck(deckId).then(r =>{
                    dispatch({
                        type:"DELETE_DECK",
                        payload:{
                            id: parseInt(deckId)
                        }
                    });
                    setNotify({isOpen:true, message:'Mazo' + deckId + ' eliminado', type:'success', duration: 3000})
                })
                    .catch((err) => {
                        setNotify({isOpen:true, message:'No se pudo eliminar el mazo ' + deckId, type:'error', duration: 3000})
                    });
            })
            .catch(() => {
                setNotify({isOpen:true, message:'No se eliminó el mazo ' + deckId, type:'info'})
            });


    }

    return (
        <Button size="small" color="primary" onClick={() => {handleDeleteDeck(deckId)}}>
            Eliminar
        </Button>
    )
}
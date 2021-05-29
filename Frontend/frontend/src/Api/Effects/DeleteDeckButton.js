import React, {useContext} from "react";
import AdminService from "../AdminService";
import {useAuth} from "../../Auth/useAuth";
import {AppContext} from "../../Common/AppContext";
import Button from "@material-ui/core/Button";
import {useConfirm} from "material-ui-confirm";

export const DeleteDeckButton = (props) => {

    const confirm = useConfirm();
    const {dispatch} = useContext(AppContext);
    const {deckId, setNotify} = props;
    const {user} = useAuth();

    function handleDeleteDeck() {

        confirm({ description: 'Desea eliminar el mazo?' })
            .then(() => {
                AdminService.deleteDeck(deckId, user.token).then(r =>{
                    dispatch({
                        type:"DELETE_DECK",
                        payload:{
                            id: parseInt(deckId)
                        }
                    });
                    setNotify({isOpen:true, message:'Mazo' + deckId + ' eliminado', type:'success'})
                })
                    .catch((err) => {
                        console.log(err, 'new deck');
                        setNotify({isOpen:true, message:'No se pudo eliminar el mazo ' + deckId, type:'error'})
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
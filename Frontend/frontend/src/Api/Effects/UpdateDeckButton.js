import React, {useContext} from "react";
import {AppContext} from "../../Common/AppContext";
import {useAuth} from "../../Auth/useAuth";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import AdminService from "../AdminService";

export const UpdateDeckButton = (props) => {
    const {dispatch} = useContext(AppContext);
    const {disabled, deck, setNotify, resetForm} = props;
    const {user} = useAuth();

    function handleUpdateDeck() {


        AdminService.updateDeck(deck, user.token).then(response =>{
            dispatch({
                type:"UPDATE_DECK",
                payload:{
                    id: deck.id, name: deck.name, cardList: deck.cardList
                }
            });
            setNotify({isOpen:true, message:'Mazo actualizado', type:'success'})
        })
        .catch((err) => {
            console.log(err, 'new deck');
            setNotify({isOpen:true, message:'El mazo no pudo ser actualizado', type:'error'})
        });
    }
    return (
        <Grid item xs={12}>
            <Button
                variant="contained"
                color="primary"
                onClick={handleUpdateDeck}
                //disabled=true deshabilita el boton. Si el form es valido, setteamos disabled en false.
                disabled={!disabled}
            >
                Actualizar Mazo
            </Button>
        </Grid>
    )
}
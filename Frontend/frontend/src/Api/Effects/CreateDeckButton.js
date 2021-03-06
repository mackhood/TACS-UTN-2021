import React, {useContext} from "react";
import {AppContext} from "../../Common/AppContext";
import AdminService from "../AdminService";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import {NotifyContext} from "../../Common/NotifyContextProvider";

export const CreateDeckButton = (props) => {
    const {dispatch} = useContext(AppContext);
    const {setNotify} = useContext(NotifyContext);
    const {disabled, deck, resetForm} = props;
    function handleCreateDeck() {
        AdminService.createDeck(deck).then(r =>{
            dispatch({
                type:"ADD_DECK",
                payload:{
                    id: parseInt(r.data.id), name: deck.name, cardList: deck.cardList
                }
            });
            setNotify({isOpen:true, message:'Nuevo mazo creado', type:'success'})
            resetForm();
        })
            .catch((err) => {
                console.log(err, 'new deck');
                setNotify({isOpen:true, message:'El mazo no pudo ser creado', type:'error'})
            });
    }
    return (
        <Grid item xs={12}>
            <Button
                variant="contained"
                color="primary"
                onClick={handleCreateDeck}
                //disabled=true deshabilita el boton. Si el form es valido, setteamos disabled en false.
                disabled={!disabled}
            >
                Crear Mazo
            </Button>
        </Grid>
    )
}
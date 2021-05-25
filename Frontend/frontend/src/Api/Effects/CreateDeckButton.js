import React, {useContext} from "react";
import {AppContext} from "../../Common/AppContext";
import {useAuth} from "../../Auth/useAuth";
import AdminService from "../AdminService";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import {customAlphabet} from "nanoid";

const nanoid = customAlphabet('1234567890', 2)

export const CreateDeckButton = (props) => {
    const {dispatch} = useContext(AppContext);
    const {disabled, deck, setNotify, resetForm} = props;
    const {user} = useAuth();
    function handleCreateDeck() {
        AdminService.createDeck(deck, user.token).then(r =>{
            dispatch({
                type:"ADD_DECK",
                payload:{
                    id: parseInt(nanoid()), name: deck.name, cardList: deck.cardList
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
import React, {useContext, useEffect, useState} from "react";
import {AppContext} from "../../Common/AppContext";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import TransferList from "../../Components/TransferList";
import {useParams} from "react-router";
import {useHistory} from 'react-router-dom';
import * as _ from 'lodash';
import {UpdateDeckButton} from "../../Api/Effects/UpdateDeckButton";

export const UpdateDeck = (props) => {

    const [deckName, setDeckName] = useState("");
    const {state} = useContext(AppContext);
    const [heroeList, setHeroeList] = useState(state.heores);
    const [newDeckCardList, setNewDeckCardList] = useState([]);
    const {setNotify} = props;
    const [formIsValid, setFormIsValid] = useState(false);
    let history = useHistory();

    const [loading, setLoading] = useState(true);

    const {id} = useParams();
    console.log(id, 'id');
    console.log(useParams(), 'params');
    console.log(state.decks);
    useEffect(() => {
        //Busco el deck entre los del state --> Qué tan importante es tener la data más actualizada
        // preguntar a los profes
        let someDeck = _.find(state.decks, function(elem) {
            console.log(elem, 'elem');
            return elem.id === parseInt(id);
        });
        console.log(someDeck, 'deck');
        if (someDeck === undefined) {
            history.push('/admin/decks');
        }else{
            setDeckName(someDeck.name);
            setNewDeckCardList(someDeck.cardList);
        }
        setLoading(false);
    }, [])


    function resetForm() {
        setDeckName("");
        setNewDeckCardList([]);
    }
    function newDeckIsValid() {
        return deckName.length > 0 && newDeckCardList.length > 0
    }

    useEffect(() => {
        setFormIsValid(newDeckIsValid());
    }, [deckName, newDeckCardList])

    function handleChange(e) {
        const name = e.target.value;
        setDeckName(name);
    }

    return (
        loading ?
            <h1>Cargando mazo...</h1> :
            <>
                <UpdateDeckButton
                    disabled={formIsValid}
                    deck={{name: deckName, cardList: newDeckCardList}}
                    resetForm={resetForm}
                    setNotify={setNotify}
                />
                <Grid item xs={12}>
                    <TextField
                        name="deckName"
                        label="Ingrese un nombre"
                        variant="standard"
                        value={deckName}
                        onChange={handleChange}
                    />
                    <TransferList
                        left={heroeList}
                        setLeft={setHeroeList}
                        right={newDeckCardList}
                        setRight={setNewDeckCardList}
                    />
                </Grid>
            </>
    )
}
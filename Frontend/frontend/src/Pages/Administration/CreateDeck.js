import React, {useContext, useEffect, useState} from "react";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import {AppContext} from "../../Common/AppContext";
import TransferList from "../../Components/TransferList";
import {CreateDeckButton} from "../../Api/Effects/CreateDeckButton";
import {DeckView} from "./DeckView";
import * as _ from 'lodash';

export const CreateDeck = (props) => {

    const [deckName, setDeckName] = useState("");
    const {state} = useContext(AppContext);
    const [heroeList, setHeroeList] = useState(state.heroes);
    const [newDeckCardList, setNewDeckCardList] = useState([]);
    const [formIsValid, setFormIsValid] = useState(false);

    const [deckNames, setDeckNames] = useState([]);

    /**
     * Obtengo lista de nombre de decks para validar duplicados
     */
    useEffect(() => {
        let names = _.map(state.decks,(deck) => {return deck.name});
        setDeckNames(names);
    }, []);

    function resetForm() {
        setDeckName("");
        setNewDeckCardList([]);
        setHeroeList(state.heroes);
    }
    function newDeckIsValid() {
        return deckName.length > 0 && newDeckCardList.length > 0 && !deckNames.includes(deckName) && newDeckCardList.length % 2 === 0;
    }

    useEffect(() => {
        setFormIsValid(newDeckIsValid());
    }, [deckName, newDeckCardList])

    function handleChange(e) {
        const name = e.target.value;
        setDeckName(name);
    }

    return (
        <>
            <CreateDeckButton
                disabled={formIsValid}
                deck={{name: deckName, cardList: newDeckCardList}}
                resetForm={resetForm}
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
            <Grid item xs={12}>
                <DeckView
                    name={deckName}
                    cardList={newDeckCardList}
                />
            </Grid>
        </>
    )
}
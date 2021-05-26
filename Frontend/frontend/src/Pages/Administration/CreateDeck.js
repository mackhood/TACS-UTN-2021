import React, {useContext, useEffect, useState} from "react";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import {AppContext} from "../../Common/AppContext";
import TransferList from "../../Components/TransferList";
import {CreateDeckButton} from "../../Api/Effects/CreateDeckButton";
import {DisplayDeck} from "./DisplayDeck";

export const CreateDeck = (props) => {

    const [deckName, setDeckName] = useState("");
    const {state} = useContext(AppContext);
    const [heroeList, setHeroeList] = useState(state.heroes);
    const [newDeckCardList, setNewDeckCardList] = useState([]);
    const {setNotify} = props;
    const [formIsValid, setFormIsValid] = useState(false);

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
        <>
            <CreateDeckButton
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
            <Grid item xs={12}>
                <DisplayDeck
                    name={deckName}
                    cardList={newDeckCardList}
                />
            </Grid>
        </>
    )
}
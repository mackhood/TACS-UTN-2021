import React, {useContext} from "react";
import {AppContext} from "../../Common/AppContext";
import Grid from "@material-ui/core/Grid";
import DeckCardWithButtons from "../../Components/DeckCardWithButtons";
import {useHistory} from 'react-router-dom';


const NoDecksMessage = () => {return (
    <h1>Aun no hay ningún mazo</h1>
);}
export const DisplayDecks = () => {
    const {state} = useContext(AppContext);
    let history = useHistory();
    const navigateToUpdate = (id) => {
        //setCards(deck.cardList);
        //TODO navigate to admin/decks/:id
        console.log(id, 'id1');
        history.push('/admin/decks/' + id + '/update');
    }
    return (
        state.decks.length > 0 ? (
            state.decks.map((deck, index) => (
                <Grid item xs={12} sm={4} key={index}>
                    <DeckCardWithButtons
                        deck={deck}
                        navigateToUpdate={navigateToUpdate}
                        // deleteDeck={deleteDeck}
                        deleteDeck={() => {}}
                        // populateDeck={populateDeck}
                        populateDeck={() => {}}
                    />
                </Grid>
            ))
        ): (<NoDecksMessage/>)

)
}

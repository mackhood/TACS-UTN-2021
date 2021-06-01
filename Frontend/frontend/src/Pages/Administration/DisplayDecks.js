import React, {useContext} from "react";
import {AppContext} from "../../Common/AppContext";
import Grid from "@material-ui/core/Grid";
import DeckCardWithButtons from "../../Components/DeckCardWithButtons";
import {useHistory} from 'react-router-dom';


const NoDecksMessage = () => {return (
    <h1>Aun no hay ningún mazo</h1>
);}
export const DisplayDecks = (props) => {
    const {setNotify} = props;
    const {state} = useContext(AppContext);
    let history = useHistory();
    const navigateToUpdate = (id) => {
        history.push('/admin/decks/' + id + '/update');
    }
    const navigateToDeckView = (id) => {
        history.push('/admin/decks/' + id + '/display');
    }

    return (
        state.decks.length > 0 ? (
            state.decks.map((deck, index) => (
                <Grid item xs={12} sm={4} key={index}>
                    <DeckCardWithButtons
                        deck={deck}
                        navigateToUpdate={navigateToUpdate}
                        navigateToDeckView={navigateToDeckView}
                        setNotify={setNotify}
                    />
                </Grid>
            ))
        ): (<NoDecksMessage/>)

)
}

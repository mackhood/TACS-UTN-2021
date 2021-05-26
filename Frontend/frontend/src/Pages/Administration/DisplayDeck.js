import React, {useContext, useEffect, useState} from "react";
import {useParams} from "react-router";
import * as _ from "lodash";
import {useHistory} from "react-router-dom";
import {AppContext} from "../../Common/AppContext";
import {DeckView} from "./DeckView";

export const DisplayDeck =() => {

    let history = useHistory();
    const [loading, setLoading] = useState(true);
    const [deck, setDeck] = useState(null);
    const {state} = useContext(AppContext);
    const {id} = useParams();
    useEffect(() => {
        let someDeck = _.find(state.decks, function(elem) {
            return elem.id === parseInt(id);
        });
        if (someDeck === undefined) {
            history.push('/admin/decks');
        }else{
            setDeck(someDeck);
        }
        setLoading(false);
    })
    return (
        loading ? <h3>Loading...</h3> :
            <DeckView
                name={deck.name}
                cardList={deck.cardList}
            />
    )
}

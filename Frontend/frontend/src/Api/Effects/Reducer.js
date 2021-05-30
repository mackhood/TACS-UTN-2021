import * as _ from "lodash";

export const reducer = (state, action) => {
    if (action.type === "ADD_DECK"){
        // let newDecks = _.union(state.decks, [{id: parseInt(nanoid()), name:deckName, cardList:cardList}]);
        let newDecks = _.union(state.decks, [action.payload]);
        return {...state, decks: newDecks}
    }
    if (action.type === "UPDATE_DECK"){
        let newDecks = _.map(state.decks, function (elem) {
            return elem.id === action.payload.id ? {...elem, name:action.payload.name, cardList:action.payload.cardList} : elem;
        });
        return {...state, decks: newDecks}
    }
    if (action.type === "DELETE_DECK"){
        let newDecks = _.filter(state.decks, function (elem) {return elem.id !== action.payload.id;});
        return {...state, decks:newDecks}
    }

    // if (action.type === "")
}
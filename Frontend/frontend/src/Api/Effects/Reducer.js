import * as _ from "lodash";

export const reducer = (state, action) => {
    if (action.type === "ADD_DECK"){
        // let newDecks = _.union(state.decks, [{id: parseInt(nanoid()), name:deckName, cardList:cardList}]);
        let newDecks = _.union(state.decks, [action.payload]);
        return {...state, decks: newDecks}
    }
}
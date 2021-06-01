import * as _ from "lodash";

export const reducer = (state, action) => {
    if (action.type === "ADD_DECK"){
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

    if (action.type === "ADD_GAME"){
        let newGames = _.union(state.games, [action.payload]);
        return {...state, games: newGames}
    }

    if (action.type === "ADD_DUEL"){
        let newGames = _.map(state.games, function (elem) {
            if (elem.id === action.payload.id){
                let duels = elem.duels;
                let newDuels = _.union(duels, [action.payload]);
                return {...elem, duels: newDuels}
            }else{
                return elem;
            }
        });
        return {...state, games: newGames}
    }

    //TODO que devuelve terminar la partida?

    if (action.type === "END_GAME"){
        let newGames = _.map(state.games, function (elem) {
            return elem.id === action.payload.id ? {...elem, state: action.payload.state} : elem;
        });
        return {...state, games: newGames}
    }
}
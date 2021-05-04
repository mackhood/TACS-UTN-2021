import axios from 'axios'
import * as _ from 'lodash';
import {REACT_APP_DECK_REST_API_URL} from '../Common/Constants';

class AdminService{

    generateBody = (deck) =>{
        const cardIdList = _.map(deck.cardList, (card) => parseInt(card.id));
        return {name: deck.name, cardListId: cardIdList};
    }
    createDeck(deck, token){

        return axios.post(REACT_APP_DECK_REST_API_URL , this.generateBody(deck) , {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            });
    }

    modifyDeck(deck, token){
        return axios.put(REACT_APP_DECK_REST_API_URL + '/' + parseInt(deck.id) , this.generateBody(deck), {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        });
    }
    deleteDeck(deckId, token){
        return axios.delete(REACT_APP_DECK_REST_API_URL + '/' + parseInt(deckId) , {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        });
    }
}

export default new AdminService();
import axios from 'axios'

import {REACT_APP_DECK_REST_API_URL} from '../Common/Constants';

class AdminService{

    createDeck(deck, token){
        return axios.post(REACT_APP_DECK_REST_API_URL , deck, {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            });
    }

    modifyDeck(deck, token){
        return axios.put(REACT_APP_DECK_REST_API_URL + '/' + deck.id , deck, {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        });
    }
    deleteDeck(deckId, token){
        return axios.delete(REACT_APP_DECK_REST_API_URL + '/' + deckId , {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        });
    }
}

export default new AdminService();
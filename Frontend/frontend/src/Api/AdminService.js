import axios from 'axios'

const DECK_REST_API_URL = 'http://localhost:8080/decks';

class AdminService{

    createDeck(deck, token){
        return axios.post(DECK_REST_API_URL , deck, {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            });
    }

    modifyDeck(deck, token){
        return axios.put(DECK_REST_API_URL + '/' + deck.id , deck, {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        });
    }
    deleteDeck(deckId, token){
        return axios.delete(DECK_REST_API_URL + '/' + deckId , {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        });
    }
}

export default new AdminService();
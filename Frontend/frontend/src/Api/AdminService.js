import axios from 'axios'
import * as _ from 'lodash';
import {REACT_APP_DECK_REST_API_URL, REACT_APP_USER_REST_API_URL} from '../Common/Constants';

class AdminService{

    generateBody = (deck) =>{
        const cardIdList = _.map(deck.cardList, (card) => parseInt(card.id));
        return {name: deck.name, cardListId: cardIdList};
    }

    createDeck(deck, token){

        return axios.post(REACT_APP_DECK_REST_API_URL , this.generateBody(deck) , {
                'headers': {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token
                }
            });
    }

    updateDeck(deck, token){
        return axios.put(REACT_APP_DECK_REST_API_URL + '/' + parseInt(deck.id) , this.generateBody(deck), {
            'headers':
                {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token
                }
        }
        );
    }

    deleteDeck(deckId, token){
        return axios.delete(REACT_APP_DECK_REST_API_URL + '/' + parseInt(deckId) , {
            'headers':
                {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token
                }
        });
    }

    getDecks(token){
        return axios.get(REACT_APP_DECK_REST_API_URL , {
            "headers":{
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            }
        });
    }

    getUsers(token){
        return axios.get(REACT_APP_USER_REST_API_URL , {
            "headers":{
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            }
        });
    }
    
}

export default new AdminService();
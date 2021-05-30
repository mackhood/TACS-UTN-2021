import axios from 'axios'
import {REACT_APP_CARD_REST_API_URL} from '../Common/Constants';

class CommonService{

    getCards(token){
        return axios.get(REACT_APP_CARD_REST_API_URL , {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        });
    }

    createCard(card, token){
        return axios.post(REACT_APP_CARD_REST_API_URL , card, {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        });
    }
    deleteCard(cardId, token){
        return axios.delete(REACT_APP_CARD_REST_API_URL + '/' + cardId , {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        });
    }

}

export default new CommonService();
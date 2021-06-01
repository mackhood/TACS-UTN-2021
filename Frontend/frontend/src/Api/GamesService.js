import axios from 'axios'
import {REACT_APP_CARD_REST_API_URL} from '../Common/Constants';

class GamesService{

    createGame(token){
        return axios.get(REACT_APP_CARD_REST_API_URL , {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        });
    }

    
    
}

export default new GamesService();
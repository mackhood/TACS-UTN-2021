import axios from 'axios'
import {REACT_APP_CARD_REST_API_URL, REACT_APP_GAME_REST_API_URL} from '../Common/Constants';

class CommonService{

    getCards(token){
        return axios.get(REACT_APP_CARD_REST_API_URL , {
            "headers":{
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            }
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
    createGame(game, token){

        return axios.post(REACT_APP_GAME_REST_API_URL + '', game, {
            'headers':
                {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token
                }
        });
    }
    postDuel(data, token){
        return axios.post(REACT_APP_GAME_REST_API_URL + '/' + data.id + '/duels', data.attribute, {
            'headers':
                {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token
                }
        });
    }
    getGameDuels(data, token){
        return axios.get(REACT_APP_GAME_REST_API_URL + '/' + (data.id) + '/replay', {
            'headers':
                {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token
                }
        })
    }

    getSingleGame(data, token){
        return axios.get(REACT_APP_GAME_REST_API_URL + '/' + data.id, {
            'headers':
                {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token
                }
        })
    }

    getGames(token){
        return axios.get(REACT_APP_GAME_REST_API_URL , {
            'headers':
                {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token
                }
        })
    }

    dropGame(gameId, token){
        return axios.post(REACT_APP_GAME_REST_API_URL + '/' + gameId, null, {
            'headers':
                {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token
                }
        });
    }

}

export default new CommonService();
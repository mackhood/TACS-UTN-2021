import {apiAxiosInstance} from "./Axios";

class CommonService{

    getCards(){
        return apiAxiosInstance.get('/cards');
    }
    createGame(game){
        return apiAxiosInstance.post('/games', game);
    }
    postDuel(data){
        return apiAxiosInstance.post('/games/' + data.id + '/duels', {id: data.id, attribute: data.attribute});
    }
    getGameDuels(data){
        return apiAxiosInstance.get('/games/' + (data.id) + '/replay')
    }

    getSingleGame(data){
        return apiAxiosInstance.get('/games/' + data.id)
    }

    getGames(){
        return apiAxiosInstance.get('/games' )
    }
    getDeckCards(data){
        return apiAxiosInstance.get('/decks' + '/' + data.id + '/cards' );
    }

    getNextUserCard(data){
        return apiAxiosInstance.get('/games' + '/' + data.id + '/duels' );
    }

    dropGame(gameId){
        return apiAxiosInstance.post('/games/' + gameId, null);
    }

}

export default new CommonService();
import * as _ from 'lodash';
import {apiAxiosInstance} from './Axios';

class AdminService{

    generateBody = (deck) =>{
        const cardIdList = _.map(deck.cardList, (card) => parseInt(card.id));
        return {name: deck.name, cardListId: cardIdList};
    }

    createDeck(deck){
        return apiAxiosInstance.post('/decks', this.generateBody(deck));
    }

    updateDeck(deck){
        return apiAxiosInstance.put('/decks/' + parseInt(deck.id) , this.generateBody(deck));
    }

    deleteDeck(deckId){
        return apiAxiosInstance.delete('/decks/' + parseInt(deckId));
    }

    getDecks(){
        return apiAxiosInstance.get('/decks');
    }

    getUsers(){
        return apiAxiosInstance.get('/users');
    }

    getUserData(username){
        return apiAxiosInstance.get('/users/' + username);
    }
    
}

export default new AdminService();
import React, {useContext, useEffect} from "react";
import CommonService from "./Api/CommonService";
import {Route, Switch} from "react-router-dom";
import {UserGames} from "./Pages/Games/UserGames";
import Administration from "./Pages/Administration/Administration";
import {AppContext} from "./Common/AppContext";
import * as _ from 'lodash';
import AdminService from "./Api/AdminService";

export const SecuredApp = () => {

    const { dispatch } = useContext(AppContext);

    const heroeCardIsValid = (card) => {
        return _.values(card).every((attr) => attr !== null)
    }

    useEffect(() => {
        async function fetchData() {
            try {
                const cardResponse = await CommonService.getCards();
                let validHeroes = _.filter(cardResponse.data.data, (card)=>heroeCardIsValid(card));
                dispatch({ type: "LOAD_CARDS", payload: validHeroes });
                const userResponse = await AdminService.getUsers();
                dispatch({ type: "LOAD_USERS", payload: userResponse.data.data });
                const deckResponse = await AdminService.getDecks();
                dispatch({ type: "LOAD_DECKS", payload: deckResponse.data.data });
                const gamesResponse = await CommonService.getGames();
                dispatch({ type: "LOAD_GAMES", payload: gamesResponse.data.data });
            } catch (e) {
                console.log(e, 'err cards');
            }
        }

        fetchData();
    }, []);

    return (
        <Switch>
            <Route path="/games">
                <UserGames />
            </Route>
            <Route path="/admin/decks">
                <Administration />
            </Route>
        </Switch>
    )
}
import React, {useContext, useEffect} from "react";
import CommonService from "./Api/CommonService";
import {Route, Switch} from "react-router-dom";
import {UserGames} from "./Pages/Games/UserGames";
import Administration from "./Pages/Administration/Administration";
import {useAuth} from "./Auth/useAuth";
import {AppContext} from "./Common/AppContext";
import * as _ from 'lodash';
import AdminService from "./Api/AdminService";
import {Stats} from "./Pages/Administration/Stats";

export const SecuredApp = () => {

    const { dispatch } = useContext(AppContext);
    const { user } = useAuth();

    useEffect(() => {

        //TODO refactor: hacer un Promise.all()
        async function fetchData() {
            try {
                const cardResponse = await CommonService.getCards(user.token);
                //TODO me quedo los primeros 6 que son válidos. Esto tenemos que arreglarlo desde el back
                let validHeroes = _.take(cardResponse.data.data, 6);
                dispatch({ type: "LOAD_CARDS", payload: validHeroes });
                const userResponse = await AdminService.getUsers(user.token);
                dispatch({ type: "LOAD_USERS", payload: userResponse.data.data });
                const deckResponse = await AdminService.getDecks(user.token);
                dispatch({ type: "LOAD_DECKS", payload: deckResponse.data.data });
                const gamesResponse = await CommonService.getGames(user.token);
                dispatch({ type: "LOAD_GAMES", payload: gamesResponse.data.data });
            } catch (e) {
                console.log(e, 'err cards');
            }
        }

        if (user !== null) fetchData();

    }, [user]);

    return (
        <Switch>
            <Route path="/games">
                <UserGames />
            </Route>
            <Route path="/admin/decks">
                <Administration />
            </Route>
            <Route path="/admin/stats">
                <Stats />
            </Route>
        </Switch>
    )
}
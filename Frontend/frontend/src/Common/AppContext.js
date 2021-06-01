import React, {createContext, useReducer} from "react";
import {reducer} from "../Api/Effects/Reducer";
import getCards from "../Resources/getCards";
import getUsers from "../Resources/getUsers";

const initialState = {decks:[], heroes: getCards(), left: getCards(), right: [], users: getUsers(), games: []};

//Context de la app, guarda el state de manera global
export const AppContext = createContext({});

//Context Provider de la app
//Como el handling del state se hace mediante useReducer, en el AppContext guardamos tanto el state como el método dispatch para mutarlo
//Este componente envuelve al componente Administracion y lo renderiza como child component con disponibilidad de acceder al Context
export const AppContextProvider = (props) =>{
    const [state, dispatch] = useReducer(reducer, initialState);
    return (<AppContext.Provider value={{state, dispatch}}>
        {props.children}
    </AppContext.Provider>);
}
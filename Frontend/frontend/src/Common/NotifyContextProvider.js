import React, {createContext, useState} from "react";

const initialState = { isOpen: false, message: '', type: '', duration: 2000 };
export const NotifyContext = createContext({});
export const NotifyContextProvider = (props) =>{
    const [notify, setNotify] = useState(initialState);
    return (<NotifyContext.Provider value={{notify, setNotify}}>
        {props.children}
    </NotifyContext.Provider>);
}
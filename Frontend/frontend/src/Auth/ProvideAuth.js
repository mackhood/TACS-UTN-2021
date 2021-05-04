import React, {useState} from "react";
import {authContext} from './AuthContext'
import LoginService from "../Api/LoginService";

export function ProvideAuth({ children }) {
    const auth = useProvideAuth();
    return (
        <authContext.Provider value={auth}>
            {children}
        </authContext.Provider>
    );
}

function useProvideAuth() {
    const [user, setUser] = useState(null);

    const signin = async cb => {

        await LoginService
            .login({username: "admin", password: "admin"})
            .then(res => {
                setUser(res);
            });
        return cb();
    };

    const signout = async cb => {
        setTimeout(() => {}, 400);
        setUser(null);
        return cb();
    };

    return {
        user,
        signin,
        signout
    };
}
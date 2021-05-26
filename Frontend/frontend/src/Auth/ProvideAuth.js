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

    //TODO get username & password from login form
    const signin = async cb => {
        await LoginService
            .login({username: "admin", password: "admin"})
            .then(res => {
                setUser({username: "admin", token: res.data.token});
            });
        return cb();
    };

    const signout = async cb => {
        setTimeout(() => {
            setUser(null);
            return cb();
        }, 400);

    };

    return {
        user,
        signin,
        signout
    };
}
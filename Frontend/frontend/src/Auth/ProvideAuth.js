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
    const login = async data => {
        await LoginService
            .login(data.user)
            .then(res => {
                setUser({username: data.user.username, token: res.data.token});
                return data.callback();
            });

    };

    const signout = async cb => {
        setTimeout(() => {
            setUser(null);
            return cb();
        }, 400);

    };
    const register = async data => {
        await LoginService.register(data.user)
            .then(() => {
                return data.callback();
            })
            .catch(err => console.log(err, 'err'));
    }

    return {
        user,
        login,
        signout,
        register
    };
}
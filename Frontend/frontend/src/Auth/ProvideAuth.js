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

    const setUserData = (userData) => {
        setUser(userData);
    }
    const login = async user => {
        return await LoginService
            .login(user)
            .then(res =>
            {
                let userData = { username:user.username, token: res.data.token };
                setUserData(userData);
                localStorage.setItem('tacs', JSON.stringify(userData));
            })
            .catch(() => {
                localStorage.removeItem('tacs');
            });

    };

    const signout = async cb => {
        setTimeout(() => {
            setUser(null);
            localStorage.removeItem('tacs');
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
        register,
        setUserData
    };
}
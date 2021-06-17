import React, {useContext, useState} from "react";
import {authContext} from './AuthContext'
import LoginService from "../Api/LoginService";
import {NotifyContext} from "../Common/NotifyContextProvider";
import {apiAxiosInstance} from "../Api/Axios";

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
    const {setNotify} = useContext(NotifyContext);
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
                setNotify({isOpen:true, message:'Inicio de sesion exitoso', type:'success', duration: 3000})
                apiAxiosInstance.defaults.headers.common = { 'Content-Type': 'application/json','Authorization': `Bearer ${res.data.token}`}
            })
            .catch(() => {
                setNotify({ isOpen: true, message: 'Usuario no encontrado o contraseña inválida', type: 'error', duration: 3000 });
                localStorage.removeItem('tacs');
            });

    };

    const signout = async () => {
        setUser(null);
        localStorage.removeItem('tacs');
        return new Promise(resolve => {
            setTimeout(() => {
                resolve('logout');
            }, 2000);
        });

    };
    const register = async user => {
        return await LoginService.register(user)
    }

    return {
        user,
        login,
        signout,
        register,
        setUserData
    };

}
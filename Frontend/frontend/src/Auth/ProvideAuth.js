import React, {useContext, useState} from "react";
import {authContext} from './AuthContext'
import LoginService from "../Api/LoginService";
import {NotifyContext} from "../Common/NotifyContextProvider";

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
            })
            .catch(() => {
                setNotify({ isOpen: true, message: 'Usuario no encontrado o contraseña inválida', type: 'error', duration: 3000 });
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
                setNotify({ isOpen: true, message: 'Registro exitoso', type: 'success', duration: 3000 });
                return data.callback();
            })
            .catch(err => {
                setNotify({ isOpen: true, message: 'No se pudo realizar el registro', type: 'error', duration: 3000 });
                console.log(err, 'err')
            });
    }

    return {
        user,
        login,
        signout,
        register,
        setUserData
    };

}
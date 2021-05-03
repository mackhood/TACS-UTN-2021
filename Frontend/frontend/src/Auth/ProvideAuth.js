import React, {useState} from "react";
import {authContext} from './AuthContext'
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

    const signin = cb => {
        return fakeAuth.signin(() => {
            // setUser("user");
            setUser({
                username: "admin",
                jwt: "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNjIwMDYyNjg1LCJleHAiOjE2MjAwNjMyODV9._pA2cR6OfHyX9yBuj0CdbMHKBgOL5aSCQGcihXnx3E0"
            });
            cb();
        });
    };

    const signout = cb => {
        return fakeAuth.signout(() => {
            setUser(null);
            cb();
        });
    };

    return {
        user,
        signin,
        signout
    };
}

const fakeAuth = {
    isAuthenticated: false,
    signin(cb) {
        fakeAuth.isAuthenticated = true;
        setTimeout(cb, 100); // fake async
    },
    signout(cb) {
        fakeAuth.isAuthenticated = false;
        setTimeout(cb, 100);
    }
};
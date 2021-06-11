import React, {useEffect} from "react";
import {Route, Switch, useHistory} from "react-router-dom";
import Login from "./Pages/Login";
import PersistentDrawerLeft from "./Components/AppBar";
import {PageNotFound} from "./Components/404";
import Register from "./Pages/Register";
import {AppContextProvider} from "./Common/AppContext";
import {SecuredApp} from "./SecuredApp";
import {useAuth} from "./Auth/useAuth";

function App() {

    const {user, setUserData} = useAuth();
    let history = useHistory();
    useEffect(() => {
        const storedUser = localStorage.getItem('tacs');
        async function reloadUser() {

            let userData = JSON.parse(storedUser);
            setUserData(userData);
        }
        if (storedUser !== null){
            reloadUser().then(() => {history.push("/")});
        }
    }, []);
    return (
        <div className="App">
            <PersistentDrawerLeft/>
            <Switch>
                <Route exact path="/">
                  <div>Home Page</div>
                </Route>
                <Route exact path="/login">
                    <Login/>
                </Route>
                <Route exact path="/register">
                    <Register />
                </Route>
                <AppContextProvider>
                    <SecuredApp/>
                </AppContextProvider>
                <Route path="*">
                    <PageNotFound />
                </Route>
            </Switch>
        </div>
    );
}

export default App;

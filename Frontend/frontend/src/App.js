import React, {useContext, useEffect} from "react";
import {Route, Switch, useHistory} from "react-router-dom";
import Login from "./Pages/Login";
import PersistentDrawerLeft from "./Components/AppBar";
import {PageNotFound} from "./Components/404";
import Register from "./Pages/Register";
import {AppContextProvider} from "./Common/AppContext";
import {SecuredApp} from "./SecuredApp";
import {useAuth} from "./Auth/useAuth";
import Notification from "./Components/Notification";
import {NotifyContext, NotifyContextProvider} from "./Common/NotifyContextProvider";

function App() {

    const {setUserData} = useAuth();
    const {notify, setNotify} = useContext(NotifyContext);
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
            <Notification notify={notify} setNotify={setNotify}/>
            <Switch>
                <Route exact path="/">
                  <div>Home Page</div>
                </Route>
                <Route exact path="/login">
                    <NotifyContextProvider>
                        <Login/>
                    </NotifyContextProvider>
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

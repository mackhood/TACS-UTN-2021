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
import {apiAxiosInstance} from "./Api/Axios";

function App() {

    const {setUserData, signout} = useAuth();
    const {notify, setNotify} = useContext(NotifyContext);
    let history = useHistory();

    const UNAUTHORIZED = 401;
    apiAxiosInstance.interceptors.response.use(
        response => response,
        error => {
            const {status} = error.response;
            if (status === UNAUTHORIZED) {
                signout().then(() => {
                    history.replace('/login');
                });
            }
            return Promise.reject(error);
        }
    );


    useEffect(() => {
        const storedUser = localStorage.getItem('tacs');
        async function reloadUser() {

            let userData = JSON.parse(storedUser);
            apiAxiosInstance.defaults.headers.common = { 'Content-Type': 'application/json','Authorization': `Bearer ${userData.token}`}
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

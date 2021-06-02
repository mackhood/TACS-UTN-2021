import React from "react";
import {Route, Switch} from "react-router-dom";
import Login from "./Pages/Login";
import PersistentDrawerLeft from "./Components/AppBar";
import {PageNotFound} from "./Components/404";
import Register from "./Pages/Register";
import {AppContextProvider} from "./Common/AppContext";
import {SecuredApp} from "./SecuredApp";

function App() {
  return (
    <div className="App">
        <PersistentDrawerLeft/>
        <Switch>
            <Route exact path="/">
              <div>Home Page</div>
            </Route>
            <Route exact path="/login">
              <Login />
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

import React from "react";
import {Route, Switch} from "react-router-dom";
import Login from "./Pages/Login";
import Administration from "./Pages/Administration/Administration";
import PersistentDrawerLeft from "./Components/AppBar";
import {PageNotFound} from "./Components/404";
import Register from "./Pages/Register";
import {PrivateRoute} from "./Auth/PrivateRoute";
import {Main} from "./Pages/Main";

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
            <PrivateRoute exact path="/main">
                <Main/>
            </PrivateRoute>
            <Route path="/admin/decks">
              <Administration />
            </Route>
            <Route path="*">
                <PageNotFound />
            </Route>
        </Switch>
    </div>
  );
}

export default App;

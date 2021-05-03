import './App.css';
import React from "react";
import {Route, Switch} from "react-router-dom";
import Login from "./Pages/Login";
import Administration from "./Pages/Administration";
import PersistentDrawerLeft from "./Components/AppBar";
import {PrivateRoute} from "./Auth/PrivateRoute";

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

          <PrivateRoute exact path="/admin">
              <Administration />
          </PrivateRoute>
        </Switch>
    </div>
  );
}

export default App;

import './App.css';
import React from "react";
import {Route, Switch} from "react-router-dom";
import Login from "./Pages/Login";
import Administration from "./Pages/Administration";
import PersistentDrawerLeft from "./Components/AppBar";

function App() {
  return (
    <div className="App">
      <div>
        <PersistentDrawerLeft/>
      </div>
      <Switch>
        <Route exact path="/">
          <div>Hola</div>
        </Route>
        <Route exact path="/login">
          <Login />
        </Route>

        <Route exact path="/admin">
          <Administration />
        </Route>
      </Switch>
    </div>
  );
}

export default App;

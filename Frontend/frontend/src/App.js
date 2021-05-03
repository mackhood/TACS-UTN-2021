import './App.css';
import React from "react";
import {Link, Route, Switch} from "react-router-dom";
import Login from "./Pages/Login";
import Administration from "./Pages/Administration";

function App() {
  return (
    <div className="App">
      <div>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/login">Login</Link>
          </li>
          <li>
            <Link to="/admin">Gestionar Mazos</Link>
          </li>
        </ul>
      </div>
        <hr />
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

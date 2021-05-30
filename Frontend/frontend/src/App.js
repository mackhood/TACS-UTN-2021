import React from "react";
import {Route, Switch} from "react-router-dom";
import Login from "./Pages/Login";
import TablePage from "./Pages/TablePage";
import Game from './Pages/Game';
import Games from "./Pages/Games";
import PersistentDrawerLeft from "./Components/AppBar";
import {PageNotFound} from "./Components/404";
import Register from "./Pages/Register";
import {PrivateRoute} from "./Auth/PrivateRoute";
import {Main} from "./Pages/Main";
import Administration from "./Pages/Administration/Administration";

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
            <Route exact path="/games">
                <Games />
            </Route>
            <Route exact path="/stats">
                <TablePage />
            </Route>
            <Route exact path="/duels">
                <TablePage />
            </Route>
            <Route exact path="/game">
                <Game />
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

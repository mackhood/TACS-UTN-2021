import React from "react";
import {Route, Switch} from "react-router-dom";
import Login from "./Pages/Login";
import TablePage from "./Pages/Games/TablePage";
import PersistentDrawerLeft from "./Components/AppBar";
import {PageNotFound} from "./Components/404";
import Register from "./Pages/Register";
import {PrivateRoute} from "./Auth/PrivateRoute";
import {Main} from "./Pages/Main";
import Administration from "./Pages/Administration/Administration";
import {UserGames} from "./Pages/Games/UserGames";
import {AppContextProvider} from "./Common/AppContext";
import Game2 from "./Pages/Game2";

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

            <Route exact path="/duels">
                <TablePage />
            </Route>
            <PrivateRoute exact path="/main">
                <Main/>
            </PrivateRoute>
            <Route exact path="/game2">
                <Game2/>
            </Route>
            <AppContextProvider>
                <Route exact path="/games">
                    <UserGames/>
                </Route>
                <Route path="/admin/decks">
                    <Administration />
                </Route>
            </AppContextProvider>
            <Route path="*">
                <PageNotFound />
            </Route>
        </Switch>
    </div>
  );
}

export default App;

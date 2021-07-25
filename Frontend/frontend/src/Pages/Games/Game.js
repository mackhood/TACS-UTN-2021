import React, {useState} from "react";
import Grid from "@material-ui/core/Grid";
import {useHistory, useParams, useRouteMatch} from "react-router-dom";
import Box from '@material-ui/core/Box';
import Container from '@material-ui/core/Container';
import Button from "@material-ui/core/Button";
import Dialog from '@material-ui/core/Dialog';
import DialogTitle from '@material-ui/core/DialogTitle';
import {PlayDuelButton} from "../../Api/Effects/PlayDuelButton";
import {TurnAttributes} from "./GameComponents/TurnAttributes";
import {AttributeLabel} from "./GameComponents/AttributeLabel";
import {TurnCards} from "./GameComponents/TurnCards";
import {CardsWonLabel} from "./GameComponents/CardsWonLabel";
import {SeeDuels} from "./GameComponents/SeeDuels";
import {PlayersTurnLabel} from "./GameComponents/PlayerTurnLabel";
import {ShuffleCardsButton} from "./GameComponents/ShuffleCards";
import {usePlayGameTurn} from "./Domain/usePlayGameTurn";
import {DropGameButton} from "../../Api/Effects/DropGameButton";
import {TurnResult} from "./GameComponents/TurnResult";


export default function Game() {
    let history = useHistory();
    let {id} = useParams();
    const [
        game,
        setGame,
        enableGame,
        jugadorTurno,
        loading,
        currentDuel,
        showCards,
        handleRepartirCartas,
        sessionUser
    ] = usePlayGameTurn({id});
    const [atributoEnJuego, setAtributoEnJuego] = useState("Elegir atributo");
    const [openResult, setOpenResult] = useState(false);

    let {url} = useRouteMatch();

    //TODO get from API
    let attributes = ["intelligence", "strength", "speed", "durability", "power", "combat"];

    function handleClose (){
        setOpenResult(false);
    }

    function setAttribute(attr) {
        setAtributoEnJuego(attr);
    }
    const navigateToDuels = () => {
        history.push(url + '/duels');
    }

    return (
        loading ? <h3>Loading...</h3> :
        (
            <div>
                <Dialog disableBackdropClick disableEscapeKeyDown open={openResult} onClose={handleClose}>
                    <DialogTitle>GANADOR</DialogTitle>
                </Dialog>
                <Grid container alignContent={"center"}>
                    <Grid item xs={12}>
                        <TurnResult result={currentDuel.result}/>
                        {game && <DropGameButton game={game.game}/>}

                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <Container>

                            <PlayersTurnLabel
                                jugadorTurno={jugadorTurno}
                                game={game}
                            />
                            <br></br>
                            <TurnAttributes strings={attributes} callbackfn={(attr, index) => {

                                return (
                                    <Grid item xs={12} key={index}>
                                        <Button
                                            variant="contained"
                                            onClick={() => setAttribute(attr)}
                                            color="primary"
                                            size="large"
                                            fullWidth
                                        >
                                            {attr}
                                        </Button>
                                        <br></br>
                                        <br></br>
                                    </Grid>
                                )
                            }}/>
                            <br></br>
                            <AttributeLabel
                                enableGame={enableGame}
                                atributoEnJuego={atributoEnJuego}
                            />
                            <br></br>
                            <br></br>
                        </Container>
                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <Container>
                            <ShuffleCardsButton
                                enableGame={enableGame}
                                onClick={handleRepartirCartas}
                            />
                            <br></br>
                            <br></br>
                            <TurnCards
                                showCards={showCards}
                                currentDuel={currentDuel}
                            />

                            <br></br>
                            <br></br>
                            <Box component="span" display="block" bgcolor="blue">
                                <PlayDuelButton
                                    game={{game: game.game}}
                                    attribute={atributoEnJuego}
                                    disabled={!enableGame || atributoEnJuego === "Elegir atributo" || !showCards}
                                    setGame={setGame}
                                />
                            </Box>
                        </Container>
                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <Container>
                            <SeeDuels onClick={navigateToDuels}/>
                            <br></br>
                            <br></br>
                            <CardsWonLabel sessionUser={sessionUser}/>
                            <br/>
                            <br/>
                        </Container>
                    </Grid>
                    <Grid item xs={12}>
                        <pre>{JSON.stringify(game, null, 2)}</pre>
                        <pre>{JSON.stringify(currentDuel, null, 2)}</pre>
                    </Grid>
                </Grid>
            </div>
        )
    );

}


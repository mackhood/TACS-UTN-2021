import React from "react";
import Grid from "@material-ui/core/Grid";
import {useHistory, useParams, useRouteMatch} from "react-router-dom";
import Box from '@material-ui/core/Box';
import Container from '@material-ui/core/Container';
import Button from "@material-ui/core/Button";
import {PlayDuelButton} from "../../Api/Effects/PlayDuelButton";
import {TurnAttributes} from "./GameComponents/TurnAttributes";
import {AttributeLabel} from "./GameComponents/AttributeLabel";
import {TurnCards} from "./GameComponents/TurnCards";
import {CardsWonLabel} from "./GameComponents/CardsWonLabel";
import {PlayersTurnLabel} from "./GameComponents/PlayerTurnLabel";
import {ShuffleCardsButton} from "./GameComponents/ShuffleCards";
import {usePlayGameTurn} from "./Domain/usePlayGameTurn";
import {TurnResult} from "./GameComponents/TurnResult";
import {NextTurnButton} from "./GameComponents/NextTurnButton";
import {SeeDuels} from "./GameComponents/SeeDuels";


export default function Game() {
    let {id} = useParams();
    let {url} = useRouteMatch();
    let history = useHistory();
    const [
        game,
        setGame,
        enableGame,
        jugadorTurno,
        loading,
        currentDuel,
        setCurrenDuel,
        showCards,
        handleRepartirCartas,
        sessionUser,
        attributes,
        getNextTurn,
        enableNextTurnButton,
        winnerUsername
    ] = usePlayGameTurn({id});



    function setAttribute(attr) {
        setCurrenDuel({...currentDuel, attribute: attr});
    }
    function navigateToDuelsTable() {
        history.push(url + '/duels');
    }

    return (
        loading ? <h3>Loading...</h3> :
        (
            <div>
                <Grid container alignContent={"center"}>
                    <Grid item xs={12}>
                        {
                            currentDuel.result.result !== null &&
                                <Container>
                                    <TurnResult
                                        turnNumber={currentDuel.id}
                                        jugadorTurno={jugadorTurno}
                                        winnerUsername={winnerUsername}
                                        result={currentDuel.result}/>
                                        <br/>
                                </Container>
                        }
                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <Container>
                            <PlayersTurnLabel
                                jugadorTurno={jugadorTurno}
                                game={game.game}
                            />
                            <br/>
                            <TurnAttributes strings={attributes} callbackfn={(attr, index) => {

                                return (
                                    <Grid item xs={12} key={index}>
                                        <Button
                                            variant="contained"
                                            onClick={() => setAttribute(attr)}
                                            disabled={!enableGame}
                                            color="primary"
                                            size="large"
                                            fullWidth
                                        >
                                            {attr}
                                        </Button>
                                        <br/>
                                        <br/>
                                    </Grid>
                                )
                            }}/>
                            <br/>
                            <AttributeLabel atributoEnJuego={currentDuel.attribute}/>
                            <br/>
                            <br/>
                        </Container>
                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <Container>
                            <ShuffleCardsButton
                                enableGame={enableGame}
                                onClick={handleRepartirCartas}
                            />
                            <br/>
                            <br/>
                            <TurnCards
                                showCards={showCards || game.game.state === "FINISHED"}
                                currentDuel={currentDuel}
                            />

                            <br/>
                            <br/>
                            <Box component="span" display="block" bgcolor="blue">
                                <PlayDuelButton
                                    game={{game: game.game}}
                                    attribute={currentDuel.attribute}
                                    disabled={!enableGame || currentDuel.attribute === "Elegir atributo" || !showCards}
                                    setGame={setGame}
                                />
                            </Box>
                            <br/>
                            {game.duels.length !== 0 && jugadorTurno === sessionUser.username && (
                                <>
                                    <Box component="span" display="block" bgcolor="blue">
                                        <NextTurnButton
                                            getNextTurn={getNextTurn}
                                            disabled={enableNextTurnButton}
                                            currentDuel={currentDuel}
                                        />
                                    </Box>
                                    <br/>
                                </>
                            )}
                        </Container>
                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <Container>
                            <CardsWonLabel sessionUser={sessionUser}/>
                            <br/>
                            <br/>
                            <SeeDuels navigate={navigateToDuelsTable}/>
                        </Container>
                    </Grid>
                </Grid>
            </div>
        )
    );

}


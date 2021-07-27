import React, {useEffect, useState} from "react";
import CommonService from "../../../Api/CommonService";
import * as _ from "lodash";
import {useAuth} from "../../../Auth/useAuth";
import {NEW_TURN} from "../../../Common/Constants";

// Componente que se encarga de resolver la lógica del turno
// y generar la información de cada turno para mostrar por pantalla

export const usePlayGameTurn = (props) => {
    const {id} = props;
    const {user} = useAuth();
    const [game, setGame] = useState(null);
    const [sessionUser, setSessionUser] = useState({});
    const [enableGame, setEnableGame] = useState(false);
    const [enableNextTurnButton, setEnableNextTurnButton] = useState(true);
    const [enablePrevTurnButton, setEnablePrevTurnButton] = useState(true);
    const [jugadorTurno, setJugadorTurno] = useState(null);
    const [loading, setLoading] = useState(true);
    const [showCards, setShowCards] = useState(false);
    const [currentDuel, setCurrentDuel] = useState(null);
    const attributes = ["intelligence", "strength", "speed", "durability", "power", "combat"];

    /**
     * Cuando es el turno del usuario logeado habilito el juego
     * Cuando la partida sigue en juego  habilito el juego
     */
    useEffect(() => {
        if (game !== null && game.game.state !== "INPROGRESS"){
            setEnableGame(false);
            return true;
        }
        if (currentDuel && currentDuel.result.result !== null){
            setEnableGame(false);
            return true;
        }
        if (jugadorTurno !== null && sessionUser !== null) {
            let cardsAvailable = game.game.actualNumberCards > 0;
            setEnableGame((jugadorTurno === sessionUser.username) && cardsAvailable);
        }
    }, [jugadorTurno, sessionUser, enableGame, currentDuel]);

    /**
     * Cuando se actualiza game, obtengo el turno del próximo jugador
     */
    useEffect(() => {
        /**
         * When the API returns a game, jugadorTurno gets set
         */
        if (game !== null) setJugadorTurno(getNextPlayerUsername(game));
        /**
         * When game gets its duels, we seek the last turn.
         * When the game ended we set current duel to the last turn.
         * When the game continues we populate a new currentDuel.
         */
        if (game !== null) {
            const lastTurn = _.last(game.duels);
            if (lastTurn && lastTurn.result) {
                setCurrentDuel(
                    {
                        ...currentDuel,
                        attribute: lastTurn.attribute,
                        cardsLeft: lastTurn.cardsLeft,
                        creatorCard:{
                            card: {
                                name: lastTurn.creatorCard.name
                            },
                            powerstats: {
                                "strength": lastTurn.creatorCard.strength,
                                "intelligence": lastTurn.creatorCard.intelligence,
                                "speed": lastTurn.creatorCard.speed,
                                "durability": lastTurn.creatorCard.durability,
                                "power": lastTurn.creatorCard.power,
                                "combat": lastTurn.creatorCard.combat
                            }
                        },
                        challengedCard: {
                            card: {
                                name: lastTurn.challengedCard.name
                            },
                            powerstats: {
                                "strength": lastTurn.challengedCard.strength,
                                "intelligence": lastTurn.challengedCard.intelligence,
                                "speed": lastTurn.challengedCard.speed,
                                "durability": lastTurn.challengedCard.durability,
                                "power": lastTurn.challengedCard.power,
                                "combat": lastTurn.challengedCard.combat
                            }
                        },
                        id: lastTurn.id,
                        result: lastTurn.result
                    }
                );
            }
        }
    }, [game]);

    /**
     * When game gets updated, we enable/disable next and previous turns buttons
     */
    useEffect(() => {
        if (game !== null && currentDuel !== null){
            var oneDuel = _.find(game.duels, function(duel){ return duel.id === currentDuel.id; });
            //Si ya se jugó un duel
            if (oneDuel !== undefined){
                var index=_.indexOf(game.duels,oneDuel);
                //Si el duelo actual es al menos el segundo (posicion 1 del array) habilito el boton prevButton
                if (index > 0 && (index-1 >= 0) ) {
                    setEnablePrevTurnButton(true);
                }
                //Si estamos en el último duel y la partida no terminó habilito el nextButton
                if (index === game.duels.length-1) {
                    const lastTurn = _.last(game.duels);
                    if (lastTurn && lastTurn.result) setEnableNextTurnButton(true);
                }
                //Si tenemos duels siguientes al actual habilito el nextButton
                if (index < game.duels.length-1) setEnableNextTurnButton(true);
            }
        }
    }, [currentDuel, game])

    /**
     * Cuando está cargando el game o el sessionUser loading = true
     */
    useEffect(() => {
        setLoading(game === null || sessionUser === null);
    }, [game, sessionUser]);

    /**
     * Fetch & load game data
     */
    useEffect(() => {
        async function fetchData(){
            const responseDuels = await CommonService.getGameDuels({id: id});
            const responseGame = await CommonService.getSingleGame({id: id});
            let newDuels = game !== null ? game.duels : [];
            if (responseDuels.data.data !== []) newDuels = _.union(newDuels, responseDuels.data.data);
            setGame({
                ...game,
                game: responseGame.data,
                duels: newDuels
            });
            if (user.username === responseGame.data.creator.username){
                setSessionUser({
                    ...sessionUser,
                    username: responseGame.data.creator.username,
                    gainedCards: responseGame.data.creator.gainedCards,
                    isMyTurn: responseGame.data.creator.isMyTurn,
                    mainCards: responseGame.data.creator.mainCards,
                });
            }else{
                setSessionUser({
                    ...sessionUser,
                    username: responseGame.data.challenged.username,
                    gainedCards: responseGame.data.challenged.gainedCards,
                    isMyTurn: responseGame.data.challenged.isMyTurn,
                    mainCards: responseGame.data.challenged.mainCards
                });
            }
        }
        fetchData();
    }, []);


    const getNextPlayerUsername= (game) => {
        //TODO pedir que la api devuelva un booleano o un number
        if (game.game.creator.isMyTurn === "true") return game.game.creator.username;
        if (game.game.challenged.isMyTurn === "true") return game.game.challenged.username;
        return "Partido finalizado";
    }

    /**
     * Busco turno siguiente y lo seteo o creo uno nuevo si no habia
     * @param currentDuel
     */
    const getNextTurn = (currentDuel) => {
        if (currentDuel && currentDuel.id) {

            var oneDuel = _.find(game.duels, function (duel) {
                return duel.id === currentDuel.id;
            });
            var index = _.indexOf(game.duels, oneDuel);
            var nextDuel = game.duels[index+1];
            if (nextDuel !== undefined){
                setCurrentDuel({
                    ...currentDuel,
                    attribute: nextDuel.attribute,
                    cardsLeft: nextDuel.cardsLeft,
                    creatorCard:{
                        card: {
                            name: nextDuel.creatorCard.name
                        },
                        powerstats: {
                            "strength": nextDuel.creatorCard.strength,
                            "intelligence": nextDuel.creatorCard.intelligence,
                            "speed": nextDuel.creatorCard.speed,
                            "durability": nextDuel.creatorCard.durability,
                            "power": nextDuel.creatorCard.power,
                            "combat": nextDuel.creatorCard.combat
                        }
                    },
                    challengedCard: {
                        card: {
                            name: nextDuel.challengedCard.name
                        },
                        powerstats: {
                            "strength": nextDuel.challengedCard.strength,
                            "intelligence": nextDuel.challengedCard.intelligence,
                            "speed": nextDuel.challengedCard.speed,
                            "durability": nextDuel.challengedCard.durability,
                            "power": nextDuel.challengedCard.power,
                            "combat": nextDuel.challengedCard.combat
                        }
                    },
                    id: nextDuel.id,
                    result: nextDuel.result
                });
            }else{
                setCurrentDuel(NEW_TURN);
            }
        }else{
            console.log("Turno actual en juego, no puedo crear siguiente");
        }
    }
    /**
     * Si el turno actual está en juego, busco turno anterior y lo seteo
     */
    const getPrevTurn = (currentDuel) => {
        if (currentDuel && currentDuel.id) {

            var oneDuel = _.find(game.duels, function (duel) {
                return duel.id === currentDuel.id;
            });
            var index = _.indexOf(game.duels, oneDuel);
            let prevTurn = game.duels[index-1];
            if (prevTurn !== undefined){
                // setCurrentDuel(prevTurn);
                setCurrentDuel({
                    ...currentDuel,
                    attribute: prevTurn.attribute,
                    cardsLeft: prevTurn.cardsLeft,
                    creatorCard:{
                        card: {
                            name: prevTurn.creatorCard.name
                        },
                        powerstats: {
                            "strength": prevTurn.creatorCard.strength,
                            "intelligence": prevTurn.creatorCard.intelligence,
                            "speed": prevTurn.creatorCard.speed,
                            "durability": prevTurn.creatorCard.durability,
                            "power": prevTurn.creatorCard.power,
                            "combat": prevTurn.creatorCard.combat
                        }
                    },
                    challengedCard: {
                        card: {
                            name: prevTurn.challengedCard.name
                        },
                        powerstats: {
                            "strength": prevTurn.challengedCard.strength,
                            "intelligence": prevTurn.challengedCard.intelligence,
                            "speed": prevTurn.challengedCard.speed,
                            "durability": prevTurn.challengedCard.durability,
                            "power": prevTurn.challengedCard.power,
                            "combat": prevTurn.challengedCard.combat
                        }
                    },
                    id: prevTurn.id,
                    result: prevTurn.result
                });
            }
        }

    }
    const handleRepartirCartas = () => {
        async function fetchNextCard() {
            return await CommonService.getNextUserCard({id: game.game.id});
        }
        if (game.game.actualNumberCards > 0){
            setLoading(true);
            fetchNextCard().then((response)=>{
                setCurrentDuel(
                    {
                        ...currentDuel,
                        creatorCard:{
                            card: {
                                name: response.data.name
                            },
                            powerstats: {
                                "strength": response.data.strength,
                                "intelligence": response.data.intelligence,
                                "speed": response.data.speed,
                                "durability": response.data.durability,
                                "power": response.data.power,
                                "combat": response.data.combat
                            }
                        },
                        challengedCard: {
                            card: {
                                name: "??"
                            },
                            powerstats: {
                                "strength": "??",
                                "intelligence": "??",
                                "speed": "??",
                                "durability": "??",
                                "power": "??",
                                "combat": "??"
                            }
                        }
                    }
                );
            }).catch((err) => console.log(err)).then(() =>setLoading(false));

            setShowCards(true);
        }
    }

    return [
        game,
        setGame,
        enableGame,
        jugadorTurno,
        loading,
        currentDuel,
        setCurrentDuel,
        showCards,
        handleRepartirCartas,
        sessionUser,
        attributes,
        getNextTurn,
        getPrevTurn,
        enableNextTurnButton,
        enablePrevTurnButton,
    ];

}
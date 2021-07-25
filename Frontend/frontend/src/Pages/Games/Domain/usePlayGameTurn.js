import React, {useEffect, useState} from "react";
import CommonService from "../../../Api/CommonService";
import * as _ from "lodash";
import {useAuth} from "../../../Auth/useAuth";

// Componente que se encarga de resolver la lógica del turno
// y generar la información de cada turno para mostrar por pantalla

export const usePlayGameTurn = (props) => {
    const {id} = props;
    const {user} = useAuth();
    const [game, setGame] = useState(null);
    const [sessionUser, setSessionUser] = useState({});
    const [enableGame, setEnableGame] = useState(false);
    const [jugadorTurno, setJugadorTurno] = useState(null);
    const [loading, setLoading] = useState(true);
    const [showCards, setShowCards] = useState(false);
    const [currentDuel, setCurrenDuel] = useState({
        "creatorCard": {
            "id": null,
            "name": "",
            "powerstats": {
                "strength": null,
                "intelligence": null,
                "speed": null,
                "durability": null,
                "power": null,
                "combat": null
            }
        },
        "challengedCard": {
            "id": null,
            "name": "",
            "powerstats": {
                "strength": null,
                "intelligence": null,
                "speed": null,
                "durability": null,
                "power": null,
                "combat": null
            }

        },
        "attribute": null,
        "result": {
            "winner": null,
            "result": null
        }
    });

    /**
     * Cuando es el turno del usuario logeado habilito el juego
     */
    useEffect(() => {
        if (game !== null && game.game.state !== "INPROGRESS") {
            setEnableGame(false);
        }
        if (jugadorTurno !== null && sessionUser !== null) {
            let cardsAvailable = game.game.actualNumberCards > 0;
            setEnableGame((jugadorTurno === sessionUser.username) && cardsAvailable);
        }
    }, [jugadorTurno, sessionUser, enableGame]);

    /**
     * Cuando se actualiza game, obtengo el turno del próximo jugador
     */
    useEffect(() => {
        if (game !== null) setJugadorTurno(getNextPlayerUsername(game));
    }, [game]);

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
            if (responseDuels.data.data !== []) newDuels = _.union(newDuels, [responseDuels.data.data]);
            setGame({
                ...game,
                game: responseGame.data,
                duels: newDuels
            });
            if (user.username === responseGame.data.creator.username){
                setSessionUser({...sessionUser, username: responseGame.data.creator.username});
            }else{
                setSessionUser({...sessionUser, username: responseGame.data.challenged.username});
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
    function handleRepartirCartas() {
        async function fetchNextCard() {
            return await CommonService.getNextUserCard({id: game.game.id});
        }
        if (game.game.actualNumberCards > 0){
            setLoading(true);
            fetchNextCard().then((response)=>{
                setCurrenDuel(
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
        showCards,
        handleRepartirCartas,
        sessionUser
    ];

}
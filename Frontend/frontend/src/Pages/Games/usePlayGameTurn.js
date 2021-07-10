import React, {useState} from "react";

// Componente que se encarga de resolver la lógica del turno
// y generar la información de cada turno para mostrar por pantalla

export const usePlayGameTurn = () => {

    const [currentsPlayerUsername, setCurrentsPlayerUsername] = useState("");
    const [loggedUsername, setLoggedUsername] = useState("");
    const [selectedAttribute, setSelectedAttribute] = useState("");
    const [currentDuel, setCurrentDuel] = useState({
        "creatorCard": null,
        "challengedCard": null,
        "result": null
    });

    return ""
}
export const REACT_APP_CARD_REST_API_URL =
    process.env.REACT_APP_CARD_REST_API_URL ?
        process.env.REACT_APP_CARD_REST_API_URL:
        'http://localhost:8080/cards';

export const REACT_APP_DECK_REST_API_URL =
    process.env.REACT_APP_DECK_REST_API_URL ?
    process.env.REACT_APP_DECK_REST_API_URL:
        'http://localhost:8080/decks';

export const REACT_APP_GAME_REST_API_URL =
    process.env.REACT_APP_DECK_REST_API_URL ?
        process.env.REACT_APP_DECK_REST_API_URL:
        'http://localhost:8080/games';

export const REACT_APP_USER_REST_API_URL =
    process.env.REACT_APP_DECK_REST_API_URL ?
        process.env.REACT_APP_DECK_REST_API_URL:
        'http://localhost:8080/users';

export const REACT_APP_LOGIN_REST_API_URL =
    process.env.REACT_APP_LOGIN_REST_API_URL ?
    process.env.REACT_APP_LOGIN_REST_API_URL:
        'http://localhost:8080';

export const REACT_APP_REST_API_URL =
    process.env.REACT_APP_REST_API_URL ?
        process.env.REACT_APP_REST_API_URL:
        'http://localhost:8080';

export const NEW_TURN = {
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
    "attribute": "Elegir atributo",
    "result": {
        "winner": null,
        "result": null
    },
    "id": null
};

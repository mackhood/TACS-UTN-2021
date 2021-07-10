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

const TURNO = {
    "creatorCard": {
        "id": 4,
        "name": "Abomination",
        "powerstats": {
            "strength": 80,
            "intelligence": 63,
            "speed": 53,
            "durability": 90,
            "power": 62,
            "combat": 95
        }
    },
    "challengedCard": {
        "id": 1,
        "name": "A-Bomb",
        "powerstats": {
            "strength": 100,
            "intelligence": 38,
            "speed": 17,
            "durability": 80,
            "power": 24,
            "combat": 64
        }
    },
    "attribute": null,
    "result": null,
    "turnUsername": "player"
};

const GAME =
    {
        "game": {
            "id": 1,
            "creator": {
                "username": "test",
                "mainCards": 1,
                "gainedCards": 0,
                "isMyTurn": "false"
            },
            "challenged": {
                "username": "player",
                "mainCards": 1,
                "gainedCards": 0,
                "isMyTurn": "true"
            },
            "deckName": "Nuevo mazo",
            "actualNumberCards": 2,
            "state": "INPROGRESS",
            "stateCode": 1
        },
        "duels": [
            []
        ]
    };

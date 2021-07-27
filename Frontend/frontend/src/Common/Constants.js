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
        "card": {
            "name": "",
        },
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
        "card": {
            "name": "",
        },
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

export const REACT_APP_DECK_REST_API_URL =
    process.env.REACT_APP_DECK_REST_API_URL ?
    process.env.REACT_APP_DECK_REST_API_URL:
        'http://localhost:8080/decks';

export const REACT_APP_GAME_REST_API_URL =
    process.env.REACT_APP_DECK_REST_API_URL ?
        process.env.REACT_APP_DECK_REST_API_URL:
        'http://localhost:8080/games';

export const REACT_APP_DUEL_REST_API_URL =
    process.env.REACT_APP_DECK_REST_API_URL ?
        process.env.REACT_APP_DECK_REST_API_URL:
        'http://localhost:8080/games';

export const REACT_APP_CARD_REST_API_URL =
    process.env.REACT_APP_CARD_REST_API_URL ?
    process.env.REACT_APP_CARD_REST_API_URL:
        'http://localhost:8080/cards';

export const REACT_APP_LOGIN_REST_API_URL =
    process.env.REACT_APP_LOGIN_REST_API_URL ?
    process.env.REACT_APP_LOGIN_REST_API_URL:
        'http://localhost:8080';
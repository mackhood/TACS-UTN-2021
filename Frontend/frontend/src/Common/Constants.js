export const REACT_APP_DECK_REST_API_URL =
    process.env.REACT_APP_DECK_REST_API_URL ?
    process.env.REACT_APP_DECK_REST_API_URL:
        'http://localhost:8080/decks';

export const REACT_APP_CARD_REST_API_URL =
    process.env.REACT_APP_CARD_REST_API_URL ?
    process.env.REACT_APP_CARD_REST_API_URL:
        'http://localhost:8080/cards';

export const REACT_APP_LOGIN_REST_API_URL =
    process.env.REACT_APP_LOGIN_REST_API_URL ?
    process.env.REACT_APP_LOGIN_REST_API_URL:
        'http://localhost:8080';
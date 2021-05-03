import getCards from "./getCards";

export default function getDecks() {
    return [
        {
            data: [
                {
                    "id": 0,
                    "name": "Mazo 1",
                    "cardList": getCards()
                },
                {
                    "id": 4,
                    "name": "TEST1",
                    "cardList": getCards()
                },
                {
                    "id": 5,
                    "name": "Mazo campeon",
                    "cardList": getCards()
                }
            ],
        }
    ]
}
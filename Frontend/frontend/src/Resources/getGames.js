export default function getGames() {
    return {
        data: [
        {
            "id": "10",
            "creatorId": "1",
            "challengedId": "2",
            "deckId": "0",
            "gameStatus": "New",
            "duels":[
                {
                    "id": "100",
                    "winnerId": "1"
                },
                {
                    "id": "101",
                    "winnerId": "2"
                },
            ]
        },
        {
            "id": "11",
            "creatorId": "2",
            "challengedId": "3",
            "deckId": "4",
            "gameStatus": "InProgress",
            "duels":[
                {
                    "id": "102",
                    "winnerId": "2",
                    "winnerCardId": "4",
                    "loserCardId": "5",
                    "attributeId": "6"
                },
                {
                    "id": "103",
                    "winnerId": "3",
                    "winnerCardId": "8",
                    "loserCardId": "9",
                    "attributeId": "2"
                },
            ]
        },         
        {
            "id": "12",
            "creatorId": "2",
            "challengedId": "3",
            "deckId": "4",
            "gameStatus": "InProgress",
            "duels":[
                {
                    "id": "102",
                    "winnerId": "2",
                    "winnerCardId": "4",
                    "loserCardId": "5",
                    "attributeId": "6"
                },
                {
                    "id": "103",
                    "winnerId": "3",
                    "winnerCardId": "8",
                    "loserCardId": "9",
                    "attributeId": "2"
                },
            ]
        },  
        {
            "id": "13",
            "creatorId": "2",
            "challengedId": "3",
            "deckId": "4",
            "gameStatus": "InProgress",
            "duels":[
                {
                    "id": "102",
                    "winnerId": "2",
                    "winnerCardId": "4",
                    "loserCardId": "5",
                    "attributeId": "6"
                },
                {
                    "id": "103",
                    "winnerId": "3",
                    "winnerCardId": "8",
                    "loserCardId": "9",
                    "attributeId": "2"
                },
            ]
        },  
        {
            "id": "14",
            "creatorId": "2",
            "challengedId": "3",
            "deckId": "4",
            "gameStatus": "Finished",
            "duels":[
                {
                    "id": "102",
                    "winnerId": "2",
                    "winnerCardId": "4",
                    "loserCardId": "5",
                    "attributeId": "6"
                },
                {
                    "id": "103",
                    "winnerId": "3",
                    "winnerCardId": "8",
                    "loserCardId": "9",
                    "attributeId": "2"
                },
            ]
        },  
    ]
}
}
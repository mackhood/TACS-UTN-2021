import getCards from "./getCards";

export default function getDecks() {
    return [
        {
            data: [
                {
                    "id": 0,
                    "name": "Mazo 1",
                    "cardList": [{
                        "id": "10",
                        "name": "Agent Bob",
                        "powerstats": {
                            "intelligence": "10",
                            "strength": "8",
                            "speed": "13",
                            "durability": "5",
                            "power": "5",
                            "combat": "20"
                        },
                        "biography": {
                            "full-name": "Bob",
                            "alter-egos": "No alter egos found.",
                            "aliases": [
                                "Bob",
                                "agent of Hydra",
                                "Bob",
                                "agent of A.I.M"
                            ],
                            "place-of-birth": "-",
                            "first-appearance": "Cable & Deadpool #38 (May, 2007)",
                            "publisher": "Marvel Comics",
                            "alignment": "good"
                        },
                        "appearance": {
                            "gender": "Male",
                            "race": "Human",
                            "height": [
                                "5'10",
                                "178 cm"
                            ],
                            "weight": [
                                "181 lb",
                                "81 kg"
                            ],
                            "eye-color": "Brown",
                            "hair-color": "Brown"
                        },
                        "work": {
                            "occupation": "Mercenary, janitor; former pirate, terrorist",
                            "base": "Mobile; formerly Manhattan, Hellcarrier"
                        },
                        "connections": {
                            "group-affiliation": "A.I.M., Deadpool; formerly Agency X, Hydra",
                            "relatives": "Allison (ex-wife); Terry and Howie (sons)"
                        },
                        "image": {
                            "url": "https://www.superherodb.com/pictures2/portraits/10/100/10255.jpg"
                        }
                    }]
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
                },
                {
                    "id": 6,
                    "name": "Mazo campeon 2",
                    "cardList": getCards()
                }
            ],
        }
    ]
}
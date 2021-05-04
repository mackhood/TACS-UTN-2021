import React, {useState} from "react";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import TransferList from "../Components/TransferList";
import getCards from "../Resources/getCards";
import {makeStyles} from "@material-ui/core/styles";
import getDecks from "../Resources/getDecks";
import * as _ from 'lodash';
import DeckCardWithButtons from "../Components/DeckCardWithButtons";
import CardList from "../Components/CardList";
import {useAuth} from "../Auth/useAuth";
import AdminService from "../Api/AdminService";
import TextField from "@material-ui/core/TextField";
import {number} from "prop-types";
const { customAlphabet } = require('nanoid')
const nanoid = customAlphabet('1234567890', 2)

const useStyles = makeStyles((theme) => ({
    layout:{
        width: 'auto',
        marginLeft: theme.spacing(2),
        marginRight: theme.spacing(2),
        [theme.breakpoints.up(800 + theme.spacing(2) * 2)]: {
            width: 800,
            marginLeft: 'auto',
            marginRight: 'auto',
        },
        margin: 10
    },
}));

export default function Administration() {
    const [user] = useState(useAuth().user);
    const [decks, setDecks] = React.useState([]);
    const [heroes] = useState(getCards());
    const [left, setLeft] = React.useState(heroes);
    const [right, setRight] = React.useState([]);
    const [creating, setCreating] = useState(true);
    const [deckName, setDeckName] = useState("");

    const [cards, setCards] = useState([]);

    const [currentDeck, setCurrentDeck] = useState({});

    const classes = useStyles();

    const createDeck = async (cardList) => {
        await AdminService.createDeck(cardList, user).then(res => {
            //TODO la api deberia retornar el nuevo deck con su id auto generado
            let newDecks = _.union(decks, [{id: parseInt(nanoid()), name:deckName, cardList:cardList}]);
            setDecks(newDecks);
        }).catch(err => console.log(err, 'err'));
        setDeckName("");
        setLeft(heroes);
        setRight([]);
    }
    const updateDeck = async (deck) => {
        let newDeck = currentDeck;
        newDeck.cardList = deck;
        await AdminService.modifyDeck(newDeck, user).then(res => {
            let newDecks = _.map(decks, function (elem) {
                return elem.id === currentDeck.id ? {...deck, cardList: deck} : elem;
            });
            setDecks(newDecks);
        }).catch(err => console.log(err));

        setLeft(heroes);
        setRight([]);
        setCurrentDeck({});
    }
    const deleteDeck = async (deckId) => {
        await AdminService.deleteDeck(deckId, user).then(res => {
            let newDecks = _.filter(decks, function (elem) {
                return elem.id !== deckId;
            });
            setDecks(newDecks);
        }).catch(err=>console.log(err));

    }

    const populateDeck = (deck) => {
        setLeft(heroes);
        let deckIds = deck.cardList.map(function (obj) {
            return obj.id
        }).concat(right.map(function (obj) {
            return obj.id
        })).sort();
        let newLeft = _.filter(left, function (card) {
            return _.indexOf(deckIds, card.id) === -1;
        });
        setCreating(false);
        setCurrentDeck(deck);
        setLeft(newLeft);
        setRight(deck.cardList);
    }

    const showDeck = (deck) => {
        setCards(deck.cardList);
    }

    function handleChange(e) {
        const name = e.target.value;
        setDeckName(name);
    }

    return (
        <div className={classes.layout}>
            <Grid container alignItems={"center"} alignContent={"center"}>
                <div style={{width:"100%"}}>DECKS</div>

                    {decks.map((deck, index) => (
                        <Grid item xs={12} sm={4} key={index}>
                            <DeckCardWithButtons
                                deck={deck}
                                showDeck={showDeck}
                                deleteDeck={deleteDeck}
                                populateDeck={populateDeck}
                            />
                        </Grid>
                    ))}

            </Grid>
            <Grid container spacing={4} alignItems={"center"} alignContent={"center"}>
                <Grid item xs={12}>
                    <Button variant="contained" color="primary" disabled={right.length === 0 || deckName.length === 0} onClick={() => {
                        creating ? createDeck(right) : updateDeck(right);
                        setCreating(true);
                    }}>
                        {creating ? 'Crear Mazo' : 'Actualizar Mazo'}
                    </Button>
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        name="deckName"
                        label="Ingrese un nombre"
                        variant="standard"
                        onChange={handleChange}
                    />
                    <TransferList
                        left={left}
                        setLeft={setLeft}
                        right={right}
                        setRight={setRight}
                    />
                </Grid>
                <CardList cards={cards}/>
            </Grid>

        </div>
    );
}
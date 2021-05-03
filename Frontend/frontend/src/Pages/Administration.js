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
    const [decks, setDecks] = React.useState(getDecks()[0].data);
    const [heroes] = useState(getCards());
    const [left, setLeft] = React.useState(heroes);
    const [right, setRight] = React.useState([]);
    const [creating, setCreating] = useState(true);

    const [cards, setCards] = useState([]);

    const [currentDeck, setCurrentDeck] = useState({});

    const classes = useStyles();

    const createDeck = (newDeck) => {
        let newDecks = _.union(decks, [{id :-1, name:"saraza", cardList:newDeck}]);
        setDecks(newDecks);
        setLeft(heroes);
        setRight([]);
    }
    const updateDeck = (deck) => {
        let newDecks = _.map(decks, function (elem) {
            return elem.id === currentDeck.id ? {...deck, cardList: deck} : elem;
        });
        setDecks(newDecks);
        setLeft(heroes);
        setRight([]);
        setCurrentDeck({});
    }

    const deleteDeck = (deckId) => {
        let newDecks = _.filter(decks, function (elem) {
            return elem.id !== deckId;
        });
        setDecks(newDecks);
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
                    <Button variant="contained" color="primary" disabled={right.length === 0} onClick={() => {
                        creating ? createDeck(right) : updateDeck(right);
                        setCreating(true);
                    }}>
                        {creating ? 'Crear Mazo' : 'Actualizar Mazo'}
                    </Button>
                </Grid>
                <Grid item xs={12}>
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
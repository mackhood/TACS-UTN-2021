import * as PropTypes from "prop-types";
import Box from "@material-ui/core/Box";
import Typography from "@material-ui/core/Typography";
import Grid from "@material-ui/core/Grid";
import HeroeCard from "../../../Components/HeroeCard";
import React from "react";

export function TurnCards(props) {

    const {showCards, currentDuel} = props;
    return <>
        {showCards && currentDuel ?
            (
                <Box component="span" display="block" bgcolor="orange">
                    <Typography gutterBottom variant="h4" component="h2">
                        CARTAS
                    </Typography>
                    <Grid container alignItems={"center"} alignContent={"center"} justify="center"
                          spacing={4}>
                        <Grid item xs={12} sm={5}>
                            <Box component="span" display="block" bgcolor="orange">
                                <Typography gutterBottom variant="h4" component="h2">
                                    Carta creador
                                </Typography>
                                <HeroeCard
                                    name={currentDuel.creatorCard && currentDuel.creatorCard.card.name}
                                    powerstats={currentDuel.creatorCard && currentDuel.creatorCard.powerstats}
                                />
                            </Box>
                        </Grid>
                        <Grid item xs={12} sm={5}>
                            <Box component="span" display="block" bgcolor="orange">
                                <Typography gutterBottom variant="h4" component="h2">
                                    Carta desafiado
                                </Typography>
                                <HeroeCard
                                    name={currentDuel.challengedCard.card.name}
                                    powerstats={currentDuel.challengedCard.powerstats}
                                />
                            </Box>
                        </Grid>
                    </Grid>
                </Box>
            ) :
            (
                <div>
                    <Typography gutterBottom variant="h4" component="h2">
                        Por favor, repartir las cartas
                    </Typography>
                </div>
            )
        }
    </>;
}

TurnCards.propTypes = {
    showCards: PropTypes.bool,
    currentDuel: PropTypes.shape({
        result: PropTypes.any,
        challengedCard: PropTypes.shape({
            name: PropTypes.string,
            powerstats: PropTypes.shape({
                strength: PropTypes.number,
                durability: PropTypes.number,
                combat: PropTypes.number,
                power: PropTypes.number,
                speed: PropTypes.number,
                intelligence: PropTypes.number
            }),
            id: PropTypes.number
        }),
        creatorCard: PropTypes.shape({
            name: PropTypes.string,
            powerstats: PropTypes.shape({
                strength: PropTypes.number,
                durability: PropTypes.number,
                combat: PropTypes.number,
                power: PropTypes.number,
                speed: PropTypes.number,
                intelligence: PropTypes.number
            }),
            id: PropTypes.number
        }),
        attribute: PropTypes.any
    }),
    game: PropTypes.any
};
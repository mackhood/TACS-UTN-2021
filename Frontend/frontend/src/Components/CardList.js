import Grid from "@material-ui/core/Grid";
import HeroeCard from "./HeroeCard";
import React from "react";

export default function CardList(props) {

    const {cards} = props;
    return (
        <Grid container spacing={2} justify={"center"} alignItems={"center"}>
            {cards.map((heroe, index) => {
                return (
                    <Grid item xs={12} sm={6} key={index}>
                        <HeroeCard
                            name={heroe.name}
                            powerstats={{
                                combat: heroe.combat,
                                durability: heroe.durability,
                                intelligence: heroe.intelligence,
                                power: heroe.power,
                                speed: heroe.speed,
                                strength: heroe.strength,
                            }}
                            // image={heroe.image}
                        />
                    </Grid>

                )
            })}

        </Grid>
    )
}
import React from "react";
import Grid from "@material-ui/core/Grid";
import CardList from "../../Components/CardList";

export const DeckView =(props) => {

    const {name, cardList} = props;
    return (
        <Grid item xs={12}>
            {
                cardList.length > 0 && (
                    <>
                        <div style={{width:"100%"}}>{name}</div>
                        <CardList cards={cardList}/>
                    </>
                )
            }
        </Grid>
)
}

import React, {useContext} from "react";
import {makeStyles} from "@material-ui/core/styles";
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Grid from "@material-ui/core/Grid";
import {useHistory} from "react-router-dom"
import Button from "@material-ui/core/Button";
import {AppContext} from "../../Common/AppContext";

const useStyles = makeStyles((theme) => ({
  table: {
    minWidth: 650,
    maxWidth: 1000,

  },
  layout: {
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

export default function GameStats() {

  const classes = useStyles();
  let history = useHistory();
  const {state} = useContext(AppContext);
  const title = "ESTADISTICAS PARTIDAS";
  const tableHeaders = ["Estado", "Cantidad De Partidas"];
  const tableRows = [];

  const headers = (tableHeaders ? tableHeaders : [])
  const gameStates = [];
  state.games.map((game, index) => {
    if (! gameStates.includes(game.state)) {
      gameStates.push(game.state);
    }
  })
  
  
  const data = [];
  gameStates.map((gameState, index) => {
    const gamesCount = state.games.filter((currentGame) => currentGame.state === gameState).length;
    data.push ({ "gamestate": gameState, "games": gamesCount});
  })

  const rows = (tableRows ? data : []);

  let rowsList = rows.map((row, index) => (
    <TableRow key={index}>
      <TableCell component="th" scope="row"> {index+1} </TableCell>
      { Object.keys(row).map((x) => (
        <TableCell component="th" scope="row"> {row[x]} </TableCell>
      )) }
    </TableRow>
  ));

  return (
    <div className={classes.layout}>
      <Grid container spacing={4} alignItems={"center"} alignContent={"center"}>
        <Grid item xs={12} sm={4}>
          <Button variant="contained" color="primary" onClick={() => { history.goBack(); }}>
            Volver
          </Button>
        </Grid>
        <Grid item xs={12} sm={4}>
          <h1>{title}</h1>
        </Grid>

      </Grid>
      <TableContainer component={Paper}>
        <Table className={classes.table} size="small" aria-label="a dense table">
          <TableHead>
            <TableRow>
              <TableCell> # </TableCell>
              {headers.map((header, index) => (
                  <TableCell key={index}> { header} </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {rowsList}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );


}


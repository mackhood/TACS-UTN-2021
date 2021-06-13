import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Grid from "@material-ui/core/Grid";
import { useHistory } from "react-router-dom"
import Button from "@material-ui/core/Button";
import { AppContext } from "../../Common/AppContext";
import { useContext } from "react";
import Select from "@material-ui/core/Select";
import Input from "@material-ui/core/Input";
import MenuItem from "@material-ui/core/MenuItem";
import InputLabel from "@material-ui/core/InputLabel";
import FormControl from "@material-ui/core/FormControl";


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

export default function UserStats() {

  const classes = makeStyles((theme) => ({
    formControl: {
      margin: theme.spacing(1),
      minWidth: 120,
    }
  }));
  let history = useHistory();
  const { state } = useContext(AppContext);
  const [currentUser, setCurrentUser] = React.useState();
  const [tableRows, setTableRows] = React.useState([]);

  const tableHeaders = ["Partida", "Mazo", "Creador", "Desafiado", "Estado", "Resultado"];
  
  const headers = (tableHeaders ? tableHeaders : [])
  const rows = (tableRows ? tableRows : []);

  let rowsList = rows.map((row, index) => (
    <TableRow key={index}>
      <TableCell component="th" scope="row"> {index + 1} </TableCell>
      { Object.keys(row).map((x) => (
        <TableCell component="th" scope="row"> {row[x]} </TableCell>
      ))}
    </TableRow>
  ));

  function handleSelectChange(e) {
    const {name, value} = e.target;
    
    console.log(value);

    const gamesCreated = state.games.filter((game) => game.creator.username === value );
    const gamesChallenged = state.games.filter((game) => game.challenged.username === value );

    const allGames = gamesCreated.concat(gamesChallenged);

    const data = [];

    allGames.map((game, index) => {
      data.push( {gameId: game.id, deck: game.deckName, creator: game.challenged.username, challenged: game.creator.username, state: game.state, result: "Winner/Loser"} );
    });



    setTableRows(data);

  }



  return (
    <div className={classes.layout}>
      <Grid container spacing={4} alignItems={"center"} alignContent={"center"}>
        <Grid item xs={12} sm={4}>
          <Button variant="contained" color="primary" onClick={() => { history.goBack(); }}>
            Volver
          </Button>
        </Grid>
        <Grid item xs={12} sm={4}>
          <h2>
            Elegir usuario:
          </h2>
        </Grid>
        <Grid item xs={12} sm={4}>
          <FormControl className={classes.formControl}>
            <InputLabel>Usuario</InputLabel>
            <Select
              name="user"
              input={<Input />}
              onChange={handleSelectChange}
            >
              {state.users.map((user, index) => (
                <MenuItem key={index} value={user.username}>{user.username}</MenuItem>
              ))}
            </Select>
          </FormControl>
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


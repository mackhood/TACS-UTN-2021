import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import * as _ from 'lodash';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Grid from "@material-ui/core/Grid";
import Games from "./Games";
import { BrowserRouter as Router, Route, Link, useHistory} from "react-router-dom"
import { useLocation } from "react-router-dom"
import Button from "@material-ui/core/Button";

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

export default function TablePage(props) {

  const location = useLocation();
  const { title, tableHeaders, tableRows, previousPage } = props; //location.state;

  const classes = useStyles();

  function createData(name, calories, fat, carbs, protein) {
    return { name, calories, fat, carbs, protein };
  }

  const headers = (tableHeaders ? tableHeaders : [
    "Dessert", "calories", "fat", "carbs", "protein"
  ])

  const rows = (tableRows ? tableRows : [
    createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
    createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
    createData('Eclair', 262, 16.0, 24, 6.0),
    createData('Cupcake', 305, 3.7, 67, 4.3),
    createData('Gingerbread', 356, 16.0, 49, 3.9),
    createData('Gingerbread', 356, 16.0, 49, 3.9),
    createData('Gingerbread', 356, 16.0, 49, 3.9),
  ]);

  return (
    <div className={classes.layout}>
      <Grid container spacing={4} alignItems={"center"} alignContent={"center"}>
        <Grid item xs={12} sm={4}>
          <Link to="/Games">
            <Button variant="contained" color="primary">
              Volver
                                    </Button>
          </Link>
        </Grid>
        <Grid item xs={12} sm={4}>
          <h1>ESTADISTICAS</h1>
          <h2>
            {(title ? { title } : "Titulo")}
          </h2>
        </Grid>

      </Grid>
      <TableContainer component={Paper}>
        <Table className={classes.table} size="small" aria-label="a dense table">
          <TableHead>
            <TableRow>
              {headers.map((header, index) => (
                <TableCell> { header} </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row) => (
              <TableRow key={row.name}>
                <TableCell component="th" scope="row">{row.name}</TableCell>
                <TableCell align="right">{row.calories}</TableCell>
                <TableCell align="right">{row.fat}</TableCell>
                <TableCell align="right">{row.carbs}</TableCell>
                <TableCell align="right">{row.protein}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );

}


import React, {useEffect, useState} from "react";
import {makeStyles} from "@material-ui/core/styles";
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import PropTypes from 'prop-types';
import Box from '@material-ui/core/Box';
import Collapse from '@material-ui/core/Collapse';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import KeyboardArrowDownIcon from '@material-ui/icons/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@material-ui/icons/KeyboardArrowUp';
import {useHistory, useParams} from "react-router-dom";
import CommonService from "../../../Api/CommonService";
import * as _ from 'lodash';
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";

const useRowStyles = makeStyles({
    root: {
        '& > *': {
            borderBottom: 'unset',
        },
    },
});

function createData(duel) {
    return {
        id: duel.id,
        creatorCard: duel.creatorCard.name,
        challengedCard: duel.challengedCard.name,
        attribute: duel.attribute,
        result: {"winner": duel.result.winner || '-', "result": duel.result.result},
    };
}

function Row(props) {
    const { row } = props;
    const [open, setOpen] = React.useState(false);
    const classes = useRowStyles();

    return (
        <React.Fragment>
            <TableRow className={classes.root}>
                <TableCell>
                    <IconButton aria-label="expand row" size="small" onClick={() => setOpen(!open)}>
                        {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
                    </IconButton>
                </TableCell>
                <TableCell component="th" scope="row">
                    {row.id}
                </TableCell>
                <TableCell align="right">{row.creatorCard}</TableCell>
                <TableCell align="right">{row.challengedCard}</TableCell>
                <TableCell align="right">{row.attribute}</TableCell>
            </TableRow>
            <TableRow>
                <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
                    <Collapse in={open} timeout="auto" unmountOnExit>
                        <Box margin={1}>
                            <Typography variant="h6" gutterBottom component="div">
                                Resultado del duelo
                            </Typography>
                            <Table size="small" aria-label="purchases">
                                <TableHead>
                                    <TableRow>
                                        <TableCell>Ganador</TableCell>
                                        <TableCell>Resultado</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    <TableRow>
                                        <TableCell component="th" scope="row">
                                            {row.result.winner}
                                        </TableCell>
                                        <TableCell>{row.result.result}</TableCell>
                                    </TableRow>
                                </TableBody>
                            </Table>
                        </Box>
                    </Collapse>
                </TableCell>
            </TableRow>
        </React.Fragment>
    );
}

Row.propTypes = {
    row: PropTypes.shape({
        calories: PropTypes.number.isRequired,
        carbs: PropTypes.number.isRequired,
        fat: PropTypes.number.isRequired,
        history: PropTypes.arrayOf(
            PropTypes.shape({
                amount: PropTypes.number.isRequired,
                customerId: PropTypes.string.isRequired,
                date: PropTypes.string.isRequired,
            }),
        ).isRequired,
        name: PropTypes.string.isRequired,
        price: PropTypes.number.isRequired,
        protein: PropTypes.number.isRequired,
    }).isRequired,
};

export function CollapsibleTable() {

    let {id} = useParams();
    const history = useHistory()

    const goBack = () => {
        history.goBack()
    }

    const [duels, setDuels] = useState([]);
    /**
     * Load game duels
     */
    useEffect(() => {
        CommonService.getGameDuels({id:id}).then(response => {
            let responseData = response.data.data;
            let newDuels = _.map(responseData, (e) => createData(e));
            setDuels(newDuels);
        });
    }, []);

    return (
        <>
            <Grid item xs={12} sm={4}>
                <Button variant="contained" color="primary" onClick={goBack}>
                    Volver
                </Button>
            </Grid>
            <TableContainer component={Paper}>
                <Table aria-label="collapsible table">
                    <TableHead>
                        <TableRow>
                            <TableCell />
                            <TableCell>Turno nro</TableCell>
                            <TableCell align="right">Carta creador</TableCell>
                            <TableCell align="right">Carta desafiado</TableCell>
                            <TableCell align="right">Atributo</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {duels !== [] && duels.map((duel) => (
                            <Row key={duel.id} row={duel} />
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </>
    );
}


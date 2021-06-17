import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import Input from "@material-ui/core/Input";
import MenuItem from "@material-ui/core/MenuItem";
import DialogActions from "@material-ui/core/DialogActions";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import React, {useState} from "react";
import {makeStyles} from "@material-ui/core/styles";
import {CreateGameButton} from "../../Api/Effects/CreateGameButton";


const useStyles = makeStyles((theme) => ({
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    formControl: {
        margin: theme.spacing(1),
        minWidth: 120,
    },
}));

export const CreateGameDialog = (props) => {


    const {handleClose, open, decks, users} = props;

    const [selectData, setSelectData] = useState({user:null, deck: null});

    const classes = useStyles();

    function resetDialog() {
        setSelectData({user:null, deck: null});
    }
    function handleSelectChange(e) {
        const {name, value} = e.target;

        setSelectData({
            ...selectData,
            [name]:value
        });
    }
    return (
        <Dialog disableBackdropClick disableEscapeKeyDown open={open} onClose={handleClose}>
            <DialogTitle>CREAR PARTIDA</DialogTitle>
            <DialogContent>
                <form className={classes.container}>
                    <FormControl className={classes.formControl}>
                        <InputLabel>Mazo</InputLabel>
                        <Select
                            name="deck"
                            onChange={handleSelectChange}
                            input={<Input />}
                        >
                            {decks.map((deck, index) => (
                                <MenuItem key={index} value={deck.id}>{deck.name}</MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                    <FormControl className={classes.formControl}>
                        <InputLabel>Contrincante</InputLabel>
                        <Select
                            name="user"
                            input={<Input />}
                            onChange={handleSelectChange}
                        >
                            {users.map((user, index) => (
                                <MenuItem key={index} value={user.username}>{user.username}</MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                </form>
            </DialogContent>
            <DialogActions>
                <Button onClick={handleClose} color="primary">
                    Cancel
                </Button>
                <CreateGameButton
                    data={selectData}
                    resetDialog={resetDialog}
                    handleClose={handleClose}
                />
            </DialogActions>
        </Dialog>
    )
}
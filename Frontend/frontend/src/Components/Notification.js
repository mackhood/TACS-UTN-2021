import React from "react";
import Snackbar from "@material-ui/core/Snackbar";
import Alert from "@material-ui/lab/Alert";
import makeStyles from "@material-ui/core/styles/makeStyles";

const useStyles= makeStyles(theme => ({
    root:{
        top: theme.spacing(9)
    }
}))
export default function Notification({notify, setNotify}) {
    const classes = useStyles();
    const handleClose = (e, reason) => {
        if (reason === 'clickaway') return;

        setNotify({...notify, isOpen: false});
    }
    return (
        <Snackbar
            className={classes.root}
            open={notify.isOpen}
            autoHideDuration={notify.duration || 2000}
            anchorOrigin={{vertical:'top', horizontal:'center'}}
            onClose={handleClose}
        >
            <Alert severity={notify.type}>
                {notify.message}
            </Alert>
        </Snackbar>
    )
}
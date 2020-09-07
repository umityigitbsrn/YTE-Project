import React from "react";
import CssBaseline from '@material-ui/core/CssBaseline';
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import {makeStyles} from "@material-ui/core/styles";
import Button from "@material-ui/core/Button";
import { Link } from 'react-router-dom';

const useStyles = makeStyles((theme) => ({
    appBar: {
        borderBottom: `1px solid ${theme.palette.divider}`,
    },
    toolbar: {
        flexWrap: 'wrap',
    },
    toolbarTitle: {
        flexGrow: 1,
    },
    link: {
        margin: theme.spacing(1, 1.5),
    },
}));

export default function AppNavbar(){
    const classes = useStyles();

    return (
        <div className="AppNavbar">
            <React.Fragment>
                <CssBaseline />
                <AppBar position="static" color="default" elevation={0} className={classes.appBar}>
                    <Toolbar className={classes.toolbar}>
                        <Typography variant="h6" color="inherit" noWrap className={classes.toolbarTitle}>
                            Activity App
                        </Typography>

                        <Button color="primary" variant="outlined" className={classes.link}>
                            <Link to='/'>Home</Link>
                        </Button>

                        <Button color="primary" variant="outlined" className={classes.link}>
                            <Link to='/activities'>Continue</Link>
                        </Button>

                        <Button color="primary" variant="outlined" className={classes.link}>
                            <Link to='/sign_in'>Sign In</Link>
                        </Button>

                        <Button color="primary" variant="outlined" className={classes.link}>
                            <Link to='/sign_up'>Sign Up</Link>
                        </Button>

                        <Button color="secondary" variant="outlined" className={classes.link}>
                            <Link to='/'>Log Out</Link>
                        </Button>
                    </Toolbar>
                </AppBar>
            </React.Fragment>
        </div>
    );
}
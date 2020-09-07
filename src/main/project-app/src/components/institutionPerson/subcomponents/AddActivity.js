import React, {useState} from 'react';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import {useParams, useHistory} from 'react-router-dom';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import {useFormik} from "formik";
import {toast, ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import axios from "axios";
import DateFnsUtils from '@date-io/date-fns'; // choose your lib
import {MuiPickersUtilsProvider, DateTimePicker} from '@material-ui/pickers';
import AppNavbar from "../../AppNavbar";

const useStyles = makeStyles((theme) => ({
    paper: {
        marginTop: theme.spacing(8),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(3),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
    redirectButton: {
        marginTop: theme.spacing(1),
    },
}));

export default function AddActivity() {
    //Styles
    const classes = useStyles();

    //States
    const [selectedStartDate, setSelectedStartDate] = useState(new Date());
    const [selectedEndDate, setSelectedEndDate] = useState(new Date());

    //Formik
    const formik = useFormik({
        initialValues: {
            name: '',
            quota: '',
        },
        onSubmit: values => {
            const newValues = {
                name: values.name,
                startDate: selectedStartDate,
                endDate: selectedEndDate,
                quota: values.quota
            };
            alert(JSON.stringify(newValues, null, 2));
            updateActivity(newValues);
            setTimeout(redirectPage, 2000);
        },

    });

    //React-toastify
    const toastOptions = {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: true,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: false,
        progress: undefined,
    };

    const {username, activityId} = useParams();
    const history = useHistory();

    //Functions
    const updateActivity = (inputData) => {
        console.log(username);
        console.log(activityId);
        axios.post(("/api/institution_person/" + username + "/activity/"), inputData)
            .then((response) => {
                if (response.data.messageType === "SUCCESS") {
                    toast.success(response.data.message, toastOptions);
                } else {
                    toast.error(response.data.message, toastOptions);
                }
            });
    }

    const redirectPage = () => {
        history.push('/' + username);
    }

    return (
        <div>
            <AppNavbar />
            <Container component="main" maxWidth="xs">
                <CssBaseline />
                <div className={classes.paper}>
                    <Typography component="h1" variant="h5">
                        Add Activity
                    </Typography>
                    <form className={classes.form} onSubmit={formik.handleSubmit}>
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                                <TextField
                                    name="name"
                                    variant="outlined"
                                    required
                                    fullWidth
                                    type="text"
                                    id="name"
                                    label="Activity Name"
                                    autoFocus
                                    onChange={formik.handleChange}
                                    value={formik.values.name}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                                    <DateTimePicker value={selectedStartDate} onChange={setSelectedStartDate}></DateTimePicker>
                                </MuiPickersUtilsProvider>
                            </Grid>
                            <Grid item xs={12}>
                                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                                    <DateTimePicker value={selectedEndDate} onChange={setSelectedEndDate}></DateTimePicker>
                                </MuiPickersUtilsProvider>
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    variant="outlined"
                                    required
                                    fullWidth
                                    name="quota"
                                    label="Activity Quota"
                                    type="number"
                                    id="quota"
                                    onChange={formik.handleChange}
                                    value={formik.values.password}
                                />
                            </Grid>
                        </Grid>
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            color="primary"
                            className={classes.submit}
                        >
                            Add
                        </Button>
                    </form>
                    <ToastContainer />
                </div>
            </Container>
        </div>
    );
}
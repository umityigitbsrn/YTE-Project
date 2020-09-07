import React,{useState, useEffect} from "react";
import InstitutionPersonActivityTable from "./subcomponents/InstitutionPersonActivityTable";
import axios from "axios";
import {useParams} from "react-router-dom";
import {toast, ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import AppNavbar from "../AppNavbar";


export default function InstitutionPersonActivities(){
    const [rows, setRows] = useState([]);

    const toastOptions = {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: true,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: false,
        progress: undefined,
    };

    const {username} = useParams();

    useEffect(() => {
        axios.get("/api/institution_person/" + username + "/activity")
            .then(response => {
                setRows(response.data)
            })
    }, [username])

    const deleteActivity = (activityId) => {
        axios.delete("/api/institution_person/" + username + "/activity/" + activityId)
            .then(response => {
                if(response.data.messageType === "SUCCESS"){
                    setRows(rows.filter((activity) => activity.id !== activityId));
                    toast.success(response.data.message, toastOptions);
                } else {
                    toast.error(response.data.message, toastOptions);
                }
            })
    }

    return(
        <div>
            <AppNavbar/>
            <InstitutionPersonActivityTable rows={rows} username={username} deleteActivity={deleteActivity}/>
            <ToastContainer />
        </div>
    );
}
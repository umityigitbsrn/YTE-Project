import React,{useState, useEffect} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";
import {toast, ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import AppNavbar from "../../AppNavbar";
import EnrolledPersonMainTable from "./EnrolledPersonMainTable";




export default function EnrolledPersonTable(){
    const [rows, setRows] = useState([]);

    const {activityId} = useParams();

    useEffect(() => {
        axios.get("/api/activities/" + activityId + "/enrolled_person")
            .then(response => {
                setRows(response.data)
            })
    }, [activityId])

    return(
        <div>
            <AppNavbar/>
            <EnrolledPersonMainTable rows={rows} activityId = {activityId}/>
        </div>
    );
}
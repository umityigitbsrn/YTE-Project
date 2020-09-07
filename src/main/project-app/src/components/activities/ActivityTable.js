import React,{useState, useEffect} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";
import {toast, ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import AppNavbar from "../AppNavbar";
import ActivityMainTable from "./subcomponents/ActivityMainTable";


export default function ActivityTable(){
    const [rows, setRows] = useState([]);

    const {username} = useParams();

    useEffect(() => {
        axios.get("/api/activities/")
            .then(response => {
                setRows(response.data)
            })
    }, [username])

    return(
        <div>
            <AppNavbar/>
            <ActivityMainTable rows = {rows}/>
        </div>
    );
}
import * as React from 'react';
import Paper from '@material-ui/core/Paper';
import {
    Chart,
    BarSeries,
    Title,
    ArgumentAxis,
    ValueAxis,
} from '@devexpress/dx-react-chart-material-ui';
import { Animation } from '@devexpress/dx-react-chart';
import {useParams} from 'react-router-dom';
import {useEffect, useState} from "react";
import axios from "axios";
import AppNavbar from "../../AppNavbar";


export default function BarChartInstitutionPerson(){
    const [data, setData] = useState([]);

    const {username} = useParams();

    useEffect(() => {
        axios.get("/api/institution_person/" + username + "/stats")
            .then(response => {
                setData(response.data);
            })
    }, [username])

    return (
        <div>
            <AppNavbar/>
            <Paper>
                <Chart
                    data={data}
                >
                    <ArgumentAxis />
                    <ValueAxis max={data.length} />

                    <BarSeries
                        valueField="size"
                        argumentField="activityName"
                    />
                    <Title text="Stat Of Activities" />
                    <Animation />
                </Chart>
            </Paper>
        </div>
    );
}

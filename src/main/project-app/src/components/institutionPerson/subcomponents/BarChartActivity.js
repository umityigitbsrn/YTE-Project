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

export default function BarChartActivity(){
    const [data, setData] = useState([]);

    const {activityId} = useParams();

    useEffect(() => {
        axios.get("/api/activities/" + activityId + "/stats")
            .then(response => {
                const newStat = [
                    {dayOfWeek: "Monday", stat: response.data.monday},
                    {dayOfWeek: "Tuesday", stat: response.data.tuesday},
                    {dayOfWeek: "Wednesday", stat: response.data.wednesday},
                    {dayOfWeek: "Thursday", stat: response.data.thursday},
                    {dayOfWeek: "Friday", stat: response.data.friday},
                    {dayOfWeek: "Saturday", stat: response.data.saturday},
                    {dayOfWeek: "Sunday", stat: response.data.sunday},
                ];
                setData(newStat);
            })
    }, [activityId])

    return (
        <div>
            <AppNavbar/>
            <Paper>
                <Chart
                    data={data}
                >
                    <ArgumentAxis />
                    <ValueAxis max={7} />

                    <BarSeries
                        valueField="stat"
                        argumentField="dayOfWeek"
                    />
                    <Title text="Weekly Stat" />
                    <Animation />
                </Chart>
            </Paper>
        </div>
    );
}

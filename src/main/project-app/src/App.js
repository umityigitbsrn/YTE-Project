import React from 'react';
//import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Home from "./components/Home";
import SignIn from "./components/signInUp/SignIn";
import SignUp from "./components/signInUp/SignUp";
import InstitutionPersonActivities from "./components/institutionPerson/IntitutionPersonActivities";
import EditActivity from "./components/institutionPerson/subcomponents/EditActivity";
import AddActivity from "./components/institutionPerson/subcomponents/AddActivity";
import ActivityTable from "./components/activities/ActivityTable";
import EnrollToActivity from "./components/activities/subcomponents/EnrollToActivity";
import EnrolledPersonTable from "./components/institutionPerson/subcomponents/EnrolledPersonTable";
import BarChartActivity from "./components/institutionPerson/subcomponents/BarChartActivity";
import BarChartInstitutionPerson from "./components/institutionPerson/subcomponents/BarChartIntitutionPerson";

function App(){
    return (
        <div className="App">
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Home} />
                    <Route path='/sign_in' exact={true} component={SignIn} />
                    <Route path='/sign_up' exact={true} component={SignUp}></Route>
                    <Route path='/activities' exact={true} component={ActivityTable}></Route>
                    <Route path='/:username' exact={true} component={InstitutionPersonActivities}></Route>
                    <Route path='/:username/update/:activityId' exact={true} component={EditActivity}></Route>
                    <Route path='/:username/new' exact={true} component={AddActivity}></Route>
                    <Route path='/:username/statistic' exact={true} component={BarChartInstitutionPerson}></Route>
                    <Route path='/activities/:activityId/statistic' exact={true} component={BarChartActivity}></Route>
                    <Route path='/activities/:activityId/enroll' exact={true} component={EnrollToActivity}></Route>
                    <Route path='/activities/:activityId/person_list' exact={true} component={EnrolledPersonTable}></Route>
                </Switch>
            </Router>
        </div>
    );
}

// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }

export default App;

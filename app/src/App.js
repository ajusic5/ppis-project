import logo from './logo.svg';
import React, { Component} from 'react';
import './App.css';
import LogIn from './pages/LogIn.js';
import SignUp from './pages/SignUp.js';
import Home from "./pages/Home.js";
import PatientPage from "./pages/PatientPage.js";
import Services from "./pages/Services.js";
import MyAppointments from "./pages/MyAppointments.js";
import DoctorPage from "./pages/DoctorPage.js";
import NewAppointment from "./pages/NewAppointment.js";
import NewService from "./pages/NewService.js";
import MyServices from "./pages/MyServices.js";
import DailyAppointments from "./pages/DailyAppointments.js";
import AddDiagnosisAndTherapy from "./pages/AddDiagnosisAndTherapy.js";
import AdminPage from "./pages/AdminPage";
import NewDoctor from "./pages/NewDoctor.js";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';


class App extends Component {

    render() {
        return <Router>
            <Routes>
                <Route path='/' element={<Home/>} />
                <Route path='/LogIn' element={<LogIn/>} />
                <Route path='/SignUp' element={<SignUp/>} />
                <Route path='/PatientPage/:id' element={<PatientPage/>} />
                <Route path='/Services' element={<Services/>} />
                <Route path='/MyAppointments/:id' element={<MyAppointments/>} />
                <Route path='/DoctorPage/:id' element={<DoctorPage/>} />
                <Route path='/NewAppointment/:id' element={<NewAppointment/>} />
                <Route path='/MyServices/:id' element={<MyServices/>} />
                <Route path='/NewService/:id' element={<NewService/>} />
                <Route path='/DailyAppointments/:id' element={<DailyAppointments/>} />
                <Route path='/DailyAppointments/AddDiagnosisAndTherapy/:id/:docId' element={<AddDiagnosisAndTherapy/>} />
                <Route path='/AdminPage' element={<AdminPage/>}/>
                <Route path='/NewDoctor' element={<NewDoctor/>} />

            </Routes>
        </Router>
    }
}

export default App;

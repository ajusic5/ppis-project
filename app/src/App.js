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


import {useState} from 'react'
import {useEffect} from 'react'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';


class App extends Component {

    render() {
        return <Router>
            <Routes>
                <Route path='/LogIn' element={<LogIn/>} />
                <Route path='/SignUp' element={<SignUp/>} />
                <Route path='/Home' element={<Home/>} />
                <Route path='/PatientPage' element={<PatientPage/>} />
                <Route path='/Services' element={<Services/>} />
                <Route path='/MyAppointments' element={<MyAppointments/>} />
                <Route path='/DoctorPage' element={<DoctorPage/>} />
                <Route path='/NewAppointment' element={<NewAppointment/>} />
                <Route path='/MyServices' element={<MyServices/>} />
                <Route path='/NewService' element={<NewService/>} />
                <Route path='/DailyAppointments' element={<DailyAppointments/>} />
                <Route path='/AddDiagnosisAndTherapy/:id/:docId' element={<AddDiagnosisAndTherapy/>} />

            </Routes>
        </Router>
    }
}

export default App;

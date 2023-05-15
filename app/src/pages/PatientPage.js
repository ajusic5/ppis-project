import React from 'react';
import '../style/PatientPage.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
// import AuthService from "../services/AuthService";
import axios from "axios";
// import UserService from "../services/UserService";

const API_URL = "http://localhost:8080/";

class PatientPage extends React.Component {
    constructor(props) {
        super(props);

    }

    render() {
        return (
            <div>
                <div className="column1-user-page">
                    <h2 className='userPage-h2' onClick={event => {
                        event.preventDefault();
                        window.location.href = './ManageAccount';
                    }}><i className="fas fa-user-circle"></i></h2>
                    <h2 style={{textAlign: "center"}}>{"Hello " }</h2>
                    <button className='button-logout' /*onClick={AuthService.logout}*/><a style={{color: "white"}}>LOG
                        OUT</a></button>
                </div>
                <div className="column2-user-page">
                    <h2 style={{marginLeft: "24%"}} className='h2-style' onClick={event => {
                        event.preventDefault();
                        window.location.href = './Home';
                    }}>Patient Page</h2>
                    {<a href="/Services" className="button"><i className="fas fa-book"></i>&nbsp; All services</a>}
                    <a href="/MyAppointments" className="button"><i className="fas fa-book"></i>&nbsp; My appointments</a>
                    <a href="/Home" className="button"><i className="fas fa-home"></i>&nbsp; Home</a>
                </div>
            </div>
        );
    }
}

export default PatientPage;
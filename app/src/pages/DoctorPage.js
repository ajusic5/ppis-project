import React from 'react';
import '../style/PatientPage.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
// import AuthService from "../services/AuthService";
import axios from "axios";
// import UserService from "../services/UserService";

const API_URL = "http://localhost:8080/";

class DoctorPage extends React.Component {
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
                    }}>Doctor Page</h2>
                    {<a href="/MyServices" className="button"><i className="fa fa-plus"></i>&nbsp; My services</a>}
                    <a href="/DailyAppointments" className="button"><i className="fas fa-book"></i>&nbsp; All appointments for the day</a>
                    <a href="/Home" className="button"><i className="fas fa-home"></i>&nbsp; Home</a>
                </div>
            </div>
        );
    }
}

export default DoctorPage;
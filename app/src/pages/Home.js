import React from 'react';
import Banner from './Banner';
import '../style/Home.css'
import swal from 'sweetalert';

const Home = () => {
    //swal alert
    const mailSendBtn = () => {
        return swal("WOW!! Your subscription is done you will get update when we setup our mail server", {
            button: false,
            icon: "success"
        });
    }
    return (
        <div id='home'>
            <Banner></Banner>
        </div>
    );
};

export default Home;
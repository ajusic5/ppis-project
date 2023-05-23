import React, { Component, useState, useEffect, useCallback } from 'react';
import Box from '@mui/material/Box';
import '../style/Home.css';
import InputLabel from '@mui/material/InputLabel';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import AssignmentIcon from '@mui/icons-material/Assignment';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import axios from "axios";
import { createTheme, ThemeProvider } from '@mui/material/styles';
import {useParams} from "react-router-dom";

const theme = createTheme();


const NewService = () => {

    const [serviceId, setServiceId] = useState('')
    const [service, setService] = useState('')
    const [services, setServices] = useState([])
    const [doctorId, setDoctorId] = useState('')


    const fetchData = () => {
        axios.get("http://localhost:8080/api/services", {headers: {
                'Content-Type': 'application/json'
            }})
            .then(response => {
                return response.data

            })
            .then(data => {
                // let id = "1" //dodati da bude fkt prijavljenog liječnika
                setServices(data)
                let id = window.location.href.split('/')[4]
                 setDoctorId(id)
            })
    }

    useEffect(() => {
        fetchData()
    }, [])

    const handleChangeService = function(event) {
        setService(event.target.value)
        // console.log(event.target.value)
        setServiceId(event.target.value)
    }
    const handleSubmit = (e) => {

        e.preventDefault();
        // console.log(doctorId)
        console.log(window.location.href)

        // console.log(window.location.href.split('/')[0])
        // console.log(window.location.href.split('/')[1])
        // console.log(window.location.href.split('/')[2])
        // console.log(window.location.href.split('/')[3])
        // console.log(window.location.href.split('/')[4])
        // console.log(window.location.href.split('/')[5])

        // console.log(id)

        // setDoctorId(id)

        console.log(doctorId)

        const formData = {
            'doctorId': doctorId,
            'serviceId': serviceId
        }
        console.log(formData)
        axios.post("http://localhost:8080/api/services/doctor_add", formData, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(r => {
            console.log("#")
            console.log(r.status)
            if (r.status === 204) {
                window.location.href = 'http://localhost:3000/MyServices/' + doctorId;

            }
        }).catch(function (error) {
            console.log(error);
        });

    };

    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="xs">
                <CssBaseline/>
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',

                    }}
                >
                    <Avatar sx={{m: 1, bgcolor: 'primary.main'}}>
                        <AssignmentIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        New Service
                    </Typography>
                    <Box component="form" noValidate onSubmit={handleSubmit} sx={{mt: 3}} >

                        <Box sx={{ minWidth: 120 , mt:2}}>
                            <FormControl fullWidth>

                                <InputLabel id="demo-simple-select-label3">Services</InputLabel>
                                <Select
                                    required
                                    labelId="demo-simple-select-helper-label3"
                                    id={serviceId}
                                    label="Services"
                                    value={service}
                                    onChange={handleChangeService}
                                > {services.map(s => (
                                    <MenuItem key={s.serviceId} value={s.serviceId}>{s.serviceName}</MenuItem>
                                ))}
                                </Select>
                            </FormControl>
                        </Box>

                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{mt: 3, mb: 2}}
                        >
                            ADD
                        </Button>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
    );
}
export default NewService;
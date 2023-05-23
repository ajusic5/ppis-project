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

const theme = createTheme();


const NewAppointment = () => {

    const [service, setService] = useState('')
    const [services, setServices] = useState([])
    const [patientId, setPatientId] = useState('')
    const [typeOfExamination, setTypeOfExamination] = useState('')
    const [year, setYear] = useState('')
    const [month, setMonth] = useState('')
    const [day, setDay] = useState('')
    const [hours, setHours] = useState('')
    const [minutes, setMinutes] = useState('')
    const [seconds, setSeconds] = useState('')

    const fetchData = () => {
        axios.get("http://localhost:8080/api/services", {headers: {
                'Content-Type': 'application/json'
            }})
            .then(response => {
                return response.data

            })
            .then(data => {

                setServices(data)
                let id = window.location.href.split('/')[4]

                setPatientId(id)
            })
    }

    useEffect(() => {
        fetchData()
    }, [])

    const handleChangeDay = function(event) {
        setDay(event.target.value)
    }
    const handleChangeMonth = function(event) {
        setMonth(event.target.value)
        // if(month < 10)
        //     setMonth("0" + month)
    }
    const handleChangeYear = function(event) {
        setYear(event.target.value)
    }
    const handleChangeHours= function(event) {
        setHours(event.target.value)
        // if(hours < 10)
        //     setHours("0" + hours)
    }
    const handleChangeMinutes = function(event) {
        setMinutes(event.target.value)
        // if(minutes < 10)
        //     setMinutes("0" + minutes)
    }
    const handleChangeService = function(event) {
        setService(event.target.value)
    }
    const handleSubmit = (e) => {

        e.preventDefault();

        // axios.delete("http://localhost:8080/api/examinations/" {
        //     headers:{
        //         'Content-Type': 'application/json'
        //     }
        // }).then(response =>{
        //     setPosts((prevPosts) =>
        //         prevPosts.filter((_, index) => index !== postIndex - 1)
        //     );
        // }

        let d = day
        if(day < 10)
            d = "0" + day

        let m = month
        if(month < 10)
            m = "0" + month

        let h = hours
        if(hours < 10)
            h = "0" + hours

        let min = minutes
        if(minutes < 10)
            min  = "0" + minutes

        console.log(year + '-' + m + '-' + d + 'T' + h + ':' + min + ':00')
        const formData = {
            'patientId':patientId,
            'dateAndTimeOfAppointment': year + '-' + m + '-' + d + 'T' + h + ':' + min + ':00',
            'typeOfExamination':service
        }
        console.log(formData)
        axios.post("http://localhost:8080/api/examinations", formData, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(r => {
            if (r.status === 201) {
                window.location.href = 'http://localhost:3000/MyAppointments/' + patientId;

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
                        New Appointment
                    </Typography>
                    <Box component="form" noValidate onSubmit={handleSubmit} sx={{mt: 3}} >
                        <Grid container spacing={2}>
                            <Grid item xs={12} sm={4}>
                                <TextField
                                    autoComplete="given-name"
                                    name="day"
                                    required
                                    fullWidth
                                    id="day"
                                    label="Day"
                                    autoFocus
                                    value={day}
                                    onChange={handleChangeDay}
                                />
                            </Grid>
                            <Grid item xs={12} sm={4}>
                                <TextField
                                    required
                                    fullWidth
                                    id="month"
                                    label="Month"
                                    name="month"
                                    autoComplete="month"
                                    value={month}
                                    onChange={handleChangeMonth}
                                />
                            </Grid>
                            <Grid item xs={12} sm={4}>
                                <TextField
                                    required
                                    fullWidth
                                    id="year"
                                    label="Year"
                                    name="year"
                                    autoComplete="year"
                                    value={year}
                                    onChange={handleChangeYear}
                                />
                            </Grid>
                        </Grid>
                        <Box sx={{ minWidth: 120 , mt:2}}>
                            <FormControl fullWidth>
                                <InputLabel id="demo-simple-select-label">Hours</InputLabel>
                                <Select
                                    required
                                    labelId="demo-simple-select-helper-label"
                                    id="demo-simple-select-helper"
                                    name="hours"
                                    label="Hours"
                                    value={hours}
                                    onChange={handleChangeHours}
                                >
                                    <MenuItem value={8}>08</MenuItem>
                                    <MenuItem value={9}>09</MenuItem>
                                    <MenuItem value={10}>10</MenuItem>
                                    <MenuItem value={11}>11</MenuItem>
                                    <MenuItem value={12}>12</MenuItem>
                                    <MenuItem value={13}>13</MenuItem>
                                    <MenuItem value={14}>14</MenuItem>
                                    <MenuItem value={15}>15</MenuItem>
                                </Select>
                            </FormControl>
                        </Box>

                        <Box sx={{ minWidth: 120 , mt:2}}>
                            <FormControl fullWidth>
                                <InputLabel id="demo-simple-select-label2">Minutes</InputLabel>

                                <Select
                                    required
                                    labelId="demo-simple-select-helper-label2"
                                    id="demo-simple-select-helper2"
                                    label="Minutes"
                                    value={minutes}
                                    onChange={handleChangeMinutes}
                                >
                                    <MenuItem value={0}>00</MenuItem>
                                    <MenuItem value={15}>15</MenuItem>
                                    <MenuItem value={30}>30</MenuItem>
                                    <MenuItem value={45}>45</MenuItem>
                                </Select>
                            </FormControl>
                        </Box>

                        <Box sx={{ minWidth: 120 , mt:2}}>
                            <FormControl fullWidth>

                                <InputLabel id="demo-simple-select-label3">Services</InputLabel>
                                <Select
                                    required
                                    labelId="demo-simple-select-helper-label3"
                                    id="demo-simple-select-helper3"
                                    label="Services"
                                    value={service}
                                    onChange={handleChangeService}
                                > {services.map(s => (
                                    <MenuItem key={s.serviceId} value={s.serviceName}>{s.serviceName}</MenuItem>
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
export default NewAppointment;
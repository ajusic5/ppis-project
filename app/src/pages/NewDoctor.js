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
import PersonIcon from '@mui/icons-material/Person';
import { createTheme, ThemeProvider } from '@mui/material/styles';

const theme = createTheme();


const NewDoctor = () => {

    const [service, setService] = useState('')
    const [services, setServices] = useState([])
    const [patientId, setPatientId] = useState('')
    const [typeOfExamination, setTypeOfExamination] = useState('')
    const [year, setYear] = useState('')
    const [month, setMonth] = useState('')
    const [day, setDay] = useState('')
    const [name, setName] = useState('')
    const [surname, setSurname] = useState('')
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [speciality, setSpeciality] = useState('')
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
                let id = "1" //dodati da bude fkt prijavljenog pacijenta
                setServices(data)
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
    const handleChangeSurname= function(event) {
        setSurname(event.target.value)

    }
    const handleChangeName = function(event) {
        setName(event.target.value)

    }
    const handleChangeUsername = function(event) {
        setUsername(event.target.value)
    }

    const handleChangePassword = function(event) {
        setPassword(event.target.value)
    }

    const handleChangeSpeciality = function(event) {
        setSpeciality(event.target.value)
    }

    const handleSubmit = (e) => {

        e.preventDefault();

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
            'name': name,
            'surname': surname,
            'username' : username,
            'password': password,
            'dateOfBirth': year + '-' + m + '-' + d,
            'fieldOfExpertise': speciality
        }
        console.log(formData)
        axios.post("http://localhost:8080/api/doctors", formData, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(r => {
            if (r.status === 201) {
                window.location.href = './AdminPage';

            }
        }).catch(function (error) {
            alert("Doctor with this username already exists!")
            window.location.href = './NewDoctor';
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
                        <PersonIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        New Doctor
                    </Typography>
                    <Box component="form" noValidate onSubmit={handleSubmit} sx={{mt: 3}} >
                        <Box sx={{ minWidth: 120 , mb:2}}>
                        <Grid container spacing={2}>
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    autoComplete="given-name"
                                    name="name"
                                    required
                                    fullWidth
                                    id="name"
                                    label="Name"
                                    autoFocus
                                    value={name}
                                    onChange={handleChangeName}
                                />
                            </Grid>
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    required
                                    fullWidth
                                    id="surname"
                                    label="Surname"
                                    name="surname"
                                    autoComplete="surname"
                                    value={surname}
                                    onChange={handleChangeSurname}
                                />
                            </Grid>
                        </Grid>
                    </Box>

                        <Box sx={{ minWidth: 120 , mb:2}}>
                            <Grid container spacing={2}>
                                <Grid item xs={12} >
                                    <TextField
                                        autoComplete="given-name"
                                        name="username"
                                        required
                                        fullWidth
                                        id="username"
                                        label="Username"
                                        autoFocus
                                        value={username}
                                        onChange={handleChangeUsername}
                                    />
                                </Grid>
                            </Grid>
                        </Box>

                        <Box sx={{ minWidth: 120 , mb:2}}>
                            <Grid container spacing={2}>
                                <Grid item xs={12} >
                                    <TextField
                                        required
                                        fullWidth
                                        name="password"
                                        label="Password"
                                        type="password"
                                        id="password"
                                        autoComplete="new-password"
                                        onChange={handleChangePassword}
                                    />
                                </Grid>
                            </Grid>
                        </Box>
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
                            <Grid container spacing={2}>
                                <Grid item xs={12} >
                                    <TextField
                                        autoComplete="given-name"
                                        name="speciality"
                                        required
                                        fullWidth
                                        id="speciality"
                                        label="Field of expertise"
                                        autoFocus
                                        value={speciality}
                                        onChange={handleChangeSpeciality}
                                    />
                                </Grid>
                            </Grid>
                        </Box>

                        {/*<Box sx={{ minWidth: 120 , mt:2}}>*/}
                        {/*    <FormControl fullWidth>*/}

                        {/*        <InputLabel id="demo-simple-select-label3">Services</InputLabel>*/}
                        {/*        <Select*/}
                        {/*            required*/}
                        {/*            labelId="demo-simple-select-helper-label3"*/}
                        {/*            id="demo-simple-select-helper3"*/}
                        {/*            label="Services"*/}
                        {/*            value={service}*/}
                        {/*            onChange={handleChangeService}*/}
                        {/*        > {services.map(s => (*/}
                        {/*            <MenuItem key={s.serviceId} value={s.serviceName}>{s.serviceName}</MenuItem>*/}
                        {/*        ))}*/}
                        {/*        </Select>*/}
                        {/*    </FormControl>*/}
                        {/*</Box>*/}

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
export default NewDoctor;
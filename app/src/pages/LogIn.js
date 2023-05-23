import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import axios from "axios";
import {Link, useNavigate} from 'react-router-dom';
import { useEffect, useState } from "react";
import Grid from "@mui/material/Grid";



const theme = createTheme();

export default function LogIn() {
    const [patient, setPatient] = useState(null)
    const [doctor, setDoctor] = useState(null)
    const [admin, setAdmin] = useState(null)
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

    const handleChangeUsername = function(event) {
        setUsername(event.target.value)
    }

    const handleChangePassword = function(event) {
        setPassword(event.target.value)
    }
    const handleSubmit = (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        console.log(username)
        console.log("###")

        axios.get(`http://localhost:8080/api/users/username/` + username, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(r => {
            //  console.log(r.data)
            // console.log(r.data.role)
            // console.log(r.data.role === 'patient')
            if (r.status === 200 && r.data.role === 'patient' ) {
                console.log("Ummm?")
                axios.get('http://localhost:8080/api/patients/username/' + r.data.username,{
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }).then(res => {
                    // console.log(res.data.id)
                    // console.log("Helllo!!!!")

                    window.location.href='http://localhost:3000/PatientPage/' + res.data.id ;

                }).catch(function (error) {
                    window.alert("You are not registered");
                     window.location.href = './LogIn';
                });
            }

            else if (r.status === 200 && r.data.role === 'doctor' ) {
                // console.log("Ummm?")
                axios.get('http://localhost:8080/api/doctors/username/' + r.data.username,{
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }).then(res => {
                    // console.log(res.data.id)
                    // console.log("Helllo!!!!")

                    window.location.href='http://localhost:3000/DoctorPage/' + res.data.id ;

                }).catch(function (error) {
                    window.alert("You are not registered");
                    window.location.href = './LogIn';
                });
            }
            else if  (r.status === 200 && r.data.role === 'admin' ){
                window.location.href='http://localhost:3000/AdminPage';
            }

        }).catch(function (error) {
            window.alert("You are not registered");
            // window.location.href = './LogIn';
        });

        // console.log({
        //     username: data.get('username'),
        //     password: data.get('password'),
        // });
    };

    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="xs">
                <CssBaseline />
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{ m: 1, bgcolor: 'primary.main' }}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Sign in
                    </Typography>
                    <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="username"
                            label="Username"
                            name="username"
                            autoComplete="username"
                            autoFocus
                            onChange={handleChangeUsername}

                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            name="password"
                            label="Password"
                            type="password"
                            id="password"
                            autoComplete="current-password"
                            onChange={handleChangePassword}

                        />

                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                        >
                            Sign In
                        </Button>
                        <Grid container justifyContent="flex-center">
                            <Grid item>
                                <a href='./SignUp' variant="body2">
                                    Don't have an account? Sign up
                                </a>
                            </Grid>
                        </Grid>

                    </Box>
                </Box>

            </Container>
        </ThemeProvider>
    );
}

import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Alert from '@mui/material/Alert';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';

import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import axios from "axios";
import {confirmAlert} from "react-confirm-alert";
import { createTheme, ThemeProvider } from '@mui/material/styles';

const theme = createTheme();

class SignUp extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            firstName: '',
            lastName: '',
            username: '',
            password: '',
            year: '',
            month: '',
            day:'',
            firstNameValidationMessage: '',
            lastNameValidationMessage: '',
            usernameValidationMessage: '',
            passwordValidationMessage: '',
            insuranceCardNumber: ''
        }
    }
    handleSubmit = e => {
        // console.log("Ummmmm?!")
        e.preventDefault();
        console.log(new Date())
        if(this.state.day < 10)
            this.state.day = "0" + this.state.day;

        if(this.state.month < 10)
            this.state.month = "0" + this.state.month;

        if (this.validateUser()) {
            const formData = {
                'name': this.state.firstName,
                'surname': this.state.lastName,
                'username': this.state.username,
                'password': this.state.password,
                'dateOfBirth': this.state.year + '-' + this.state.month + '-' + this.state.day,
                'insuranceCardNumber': this.state.insuranceCardNumber,
                'roleId': 1
            };
            console.log(formData.dateOfBirth);
            axios.post(`http://localhost:8080/api/patients`, formData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(r => {
                if (r.status === 201) {
                    window.location.href = './LogIn';
                }

            }).catch(function (error) {
                window.alert("Username already taken");
                window.location.href = './SignUp';
            });
        }
    }
    onChange = event => {
        this.setState({[event.target.name]: event.target.value})
        console.log(event.target.value)
    }

    validateUser() {
        let isValid = true;
        console.log(isValid);

        if (!this.state.firstName.trim()) {
            isValid = false;
            // console.log(isValid);
            // console.log("Firstname")
            this.setState({firstNameValidationMessage: "First name is required!"});
        } else if (this.state.firstName.length > 20) {
            this.setState({firstNameValidationMessage: "First name can't be longer than twenty characters!"});
        } else {
            this.setState({firstNameValidationMessage: ""});
        }
        if (!this.state.lastName.trim()) {
            isValid = false;
            // console.log(isValid);
            // console.log("Lastname")
            this.setState({lastNameValidationMessage: "Last name is required!"});
        } else if (this.state.lastName.length > 20) {
            this.setState({lastNameValidationMessage: "Last name can't be longer than twenty characters!"});
        } else {
            this.setState({lastNameValidationMessage: ""});
        }

        if (!this.state.username.trim()) {
            isValid = false;
            // console.log(isValid);
            // console.log("Username")
            this.setState({usernameValidationMessage: "Username is required!"});
        } else {
            this.setState({usernameValidationMessage: ""});
        }

        if (!this.state.password) {
            isValid = false;
            // console.log(isValid);
            // console.log("Password")
            this.setState({passwordValidationMessage: 'Password is required!'});
            this.setState({passwordValidationMessage: ''});
        }
        return isValid;
    }

    render()
    {
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
                            <LockOutlinedIcon/>
                        </Avatar>
                        <Typography component="h1" variant="h5">
                            Sign up
                        </Typography>
                        <Box component="form" noValidate onSubmit={this.handleSubmit} sx={{mt: 3}}>
                            <Grid container spacing={2}>
                                <Grid item xs={12} sm={6}>
                                    <TextField
                                        autoComplete="given-name"
                                        name="firstName"
                                        required
                                        fullWidth
                                        id="firstName"
                                        label="First Name"
                                        autoFocus
                                        onChange={this.onChange}
                                    />
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <TextField
                                        required
                                        fullWidth
                                        id="lastName"
                                        label="Last Name"
                                        name="lastName"
                                        autoComplete="family-name"
                                        onChange={this.onChange}
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        required
                                        fullWidth
                                        id="username"
                                        label="Username"
                                        name="username"
                                        autoComplete="username"
                                        onChange={this.onChange}
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        required
                                        fullWidth
                                        name="password"
                                        label="Password"
                                        type="password"
                                        id="password"
                                        autoComplete="new-password"
                                        onChange={this.onChange}
                                    />
                                </Grid>
                                <Grid item xs={12} sm={4}>
                                    <TextField
                                        autoComplete="given-name"
                                        name="day"
                                        required
                                        fullWidth
                                        id="day"
                                        label="Day"
                                        autoFocus
                                        onChange={this.onChange}
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
                                        onChange={this.onChange}
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
                                        onChange={this.onChange}
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        required
                                        fullWidth
                                        name="insuranceCardNumber"
                                        label="Insurance Card Number"
                                        type="insuranceCardNumber"
                                        id="insuranceCardNumber"
                                        autoComplete="insuranceCardNumber"
                                        onChange={this.onChange}
                                    />
                                </Grid>

                            </Grid>
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                sx={{mt: 3, mb: 2}}
                            >
                                Sign Up
                            </Button>
                            <Grid container justifyContent="flex-end">
                                <Grid item>
                                    <Link href='./LogIn' variant="body2">
                                        Already have an account? Sign in
                                    </Link>
                                </Grid>
                            </Grid>
                        </Box>
                    </Box>
                </Container>
            </ThemeProvider>
        );
    }
}

export default SignUp;
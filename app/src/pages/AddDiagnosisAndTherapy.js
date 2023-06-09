import React, { Component, useState, useEffect, useCallback } from 'react';
import Box from '@mui/material/Box';
import '../style/Home.css';
import {useNavigate, useParams} from "react-router-dom";
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


const AddDiagnosisAndTherapy = () => {

    const [diagnosis, setDiagnosis] = useState('')
    const [therapy, setTherapy] = useState('')
    const [service, setService] = useState('')
    const [patientId, setPatientId] = useState('')
    const {docId, id} = useParams()


    const fetchData = () => {

        console.log(id)
        console.log(docId)
        axios.get("http://localhost:8080/api/examinations/" + id, {headers: {
                'Content-Type': 'application/json'
            }})
            .then(response => {
                return response.data

            })
            .then(data => {
                setService(data)
                setPatientId(id)
                setTherapy(data.therapy)
                console.log(data.therapy)
                setDiagnosis(data.diagnosis)
                console.log(data.diagnosis)
            })
    }

    useEffect(() => {
        fetchData()
    }, [])


    const handleDiagnosisChange = function(event) {
        setDiagnosis(event.target.value)
    }

    const handleTherapyChange = function (event){
        setTherapy(event.target.value)
    }
    const handleSubmit = (e) => {

        e.preventDefault();
        console.log(diagnosis)
        console.log(therapy)
        console.log(docId)

        const formData = {
            'examinationId':service.id,
            'patientId': service.patientId,
            'doctorId': docId,
            'typeOfExamination': service.typeOfExamination,
            'dateAndTimeOfAppointment': service.dateAndTimeOfAppointment,
            'dateAndTimeOfReservation': service.dateAndTimeOfReservation,
            'diagnosis': diagnosis,
            'therapy': therapy,
            'successful': true,
            'archived': true
        }
        console.log(formData)
        axios.put("http://localhost:8080/api/examinations/" + id, formData, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(r => {
            console.log(r.status)
            if (r.status === 200) {
                window.location.href='http://localhost:3000/DailyAppointments/' + docId;
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
                        Diagnosis and therapy
                    </Typography>
                    <Box component="form" noValidate onSubmit={handleSubmit} sx={{mt: 3}}  gap={1}>
                        <Grid container spacing={2} sx = {{paddingBottom:3}}>
                            <Grid item xs={12}>
                                <TextField
                                    autoComplete="given-name"
                                    name="diagnosis"
                                    required
                                    fullWidth
                                    id="Diagnosis"
                                    label="Diagnosis"
                                    autoFocus
                                    value={diagnosis}
                                    onChange={handleDiagnosisChange}
                                />
                            </Grid>
                        </Grid>
                        <Grid container spacing={2}>
                            <Grid item xs={12} >
                                <TextField
                                    autoComplete="given-name"
                                    name="Therapy"
                                    required
                                    fullWidth
                                    id="Therapy"
                                    label="Therapy"
                                    autoFocus
                                    value={therapy}
                                    onChange={handleTherapyChange}
                                />
                            </Grid>
                        </Grid>


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
export default AddDiagnosisAndTherapy;
import React, { Component, useState, useEffect, useCallback } from 'react';
import Box from '@mui/material/Box';
import axios from "axios";
import '../style/Home.css';
import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import TableBody from "@mui/material/TableBody";
import {Button} from "@mui/material";
import TableContainer from "@mui/material/TableContainer";
import { useNavigate } from "react-router-dom";


function Item(props) {
    const { sx, ...other } = props;
    return (
        <Box
            sx={{
                bgcolor: (theme) => (theme.palette.mode === 'dark' ? '#101010' : 'whitesmoke'),
                color: (theme) => (theme.palette.mode === 'dark' ? 'grey.300' : 'primary.main'),
                border: '1px solid',
                borderColor: (theme) =>
                    theme.palette.mode === 'dark' ? 'grey.800' : 'primary.main',
                p: 1,
                m: 1,
                borderRadius: 2,
                fontSize: '0.875rem',
                fontWeight: '700',
                ...sx,
            }}
            {...other}
        />
    );
}


const DailyAppointments = () => {

    const [doctorId, setDoctorId] = useState('')
    const [posts, setPosts] = useState([])

    const fetchData = () => {
        let id = 1
        setDoctorId(1) //regulisati preko autentifikacije, ali preko ovog id, preko doctorId ga blokira CORS iako je dodano da pusti
        axios.get("http://localhost:8080/api/examinations/doctor/" + id, {headers: {
                'Content-Type': 'application/json'
            }})
            .then(response => {
                return response.data

            })
            .then(data => {
                setPosts(data)
            })
    }

    useEffect(() => {
        fetchData()
    }, [])

    return (

        <TableContainer style={{ width: '50%'}} className='div' align="center" component={Paper}>
            <Table  sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell align={"center"}>Date and time of appointment</TableCell>
                        <TableCell align={"center"} >Type of examination</TableCell>
                        <TableCell align={"center"}>Add diagnosis and therapy</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody align="center">
                    {posts.map((post, postIndex) => (
                        <TableRow key={post.id}>
                            <TableCell component="th" scope="row" padding={"normal"} align={"center"}>
                                {post.dateAndTimeOfAppointment}
                            </TableCell>
                            <TableCell component="th" scope="row" align={"center"}>
                                {post.typeOfExamination}
                            </TableCell>
                            <TableCell align={"center"}>
                                <Button
                                    id={post.id}
                                    variant="outlined"
                                    color="error"
                                    onClick={event => {
                                        event.preventDefault();
                                        window.location.href='./AddDiagnosisAndTherapy/' + event.target.id + '/' + doctorId;
                                    }}                                >
                                    Edit
                                </Button>
                            </TableCell>
                        </TableRow>
                    ))}
                    <TableRow>
                        <TableCell align={"center"} colSpan={3}>
                            <Button
                                fullWidth={1}
                                variant="outlined"
                                color="secondary"
                                onClick={event => {
                                    event.preventDefault();
                                    window.location.href='http://localhost:3000/DoctorPage/' + doctorId;
                                }}                                >
                                Go back
                            </Button>
                        </TableCell>
                    </TableRow>

                </TableBody>
            </Table>
        </TableContainer>
    )
}
export default DailyAppointments;
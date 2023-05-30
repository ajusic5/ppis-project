import * as React from "react";
import { Button } from "@mui/material";
import { useEffect, useState } from "react";
import axios from "axios";
import '../style/AccessForms.css';
import Paper from '@mui/material/Paper';
import Alert from '@mui/material/Alert';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import {useParams} from "react-router-dom";

function MyAppointments() {
    const [posts, setPosts] = useState([]);
    const {id} = useParams()

    const fetchData = () => {
        // let id = 1
        axios.get("http://localhost:8080/api/examinations/patient/" + id, { headers:{
                'Content-Type': 'application/json'
            }
        })
            .then((response) => {
                return response.data;
            })

            .then((data) => {
                setPosts(data);
                console.log(data)
            });
    };

    useEffect(() => {
        fetchData();
    }, []);

    const handleDelete = (postIndex) => {

        axios.delete("http://localhost:8080/api/examinations/" + postIndex, {
            headers:{
                'Content-Type': 'application/json'
            }
        }).then(response =>{
            if(response.data.toString() !='Appointment deleted'){
                window.alert("More than 24 hours passed since reservation! Cannot cancel it through application, call the staff!");
            }
            else{
                // setPosts((prevPosts) =>
                //     prevPosts.filter((_, index) => index !== postIndex - 1)
                // );
                window.location.reload()
            }

        })

    };


    return (
        <TableContainer style={{ width: '50%'}} className='div' align="center" component={Paper}>
            <Table  sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell align={"center"}>Date and time of reservation</TableCell>
                        <TableCell align={"center"}>Date and time of appointment</TableCell>
                        <TableCell align={"center"} >Type of examination</TableCell>
                        <TableCell align={"center"}>Cancel appointment</TableCell>
                    </TableRow>
                </TableHead>
        <TableBody align="center">
            {posts.map((post, postIndex) => (
                <TableRow key={post.examinationId}>
                    <TableCell component="th" scope="row" padding={"normal"} align={"center"}>
                        {post.dateAndTimeOfReservation}
                    </TableCell>
                    <TableCell component="th" scope="row" padding={"normal"} align={"center"}>
                        {post.dateAndTimeOfAppointment}
                    </TableCell>
                    <TableCell component="th" scope="row" align={"center"}>
                        {post.typeOfExamination}
                    </TableCell>

                    <TableCell align={"center"}>
                        <Button
                            variant="outlined"
                            color="error"
                            onClick={() => handleDelete(post.examinationId)}
                        >
                            Cancel
                        </Button>
                    </TableCell>
                </TableRow>
            ))}
            <TableRow>
                <TableCell align={"center"} colSpan={4}>
                    <Button
                        fullWidth={true}
                        variant="outlined"
                        color="primary"
                        onClick={event => {
                            event.preventDefault();
                            window.location.href='http://localhost:3000/NewAppointment/' + id;
                        }}
                    >
                        Add appointment
                    </Button>
                </TableCell>
            </TableRow>
            <TableRow>
                <TableCell align={"center"} colSpan={4}>
                    <Button
                        fullWidth={true}
                        variant="outlined"
                        color="secondary"
                        onClick={event => {
                            event.preventDefault();
                            window.location.href='http://localhost:3000/PatientPage/' + id;
                        }}                                >
                        Go back
                    </Button>
                </TableCell>
            </TableRow>
        </TableBody>
            </Table>
        </TableContainer>
    );
}
export default MyAppointments
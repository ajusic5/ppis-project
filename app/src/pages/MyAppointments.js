import * as React from "react";
import { Button } from "@mui/material";
import { useEffect, useState } from "react";
import axios from "axios";
import '../style/AccessForms.css';
import Paper from '@mui/material/Paper';

import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';

function MyAppointments() {
    const [posts, setPosts] = useState([]);

    const fetchData = () => {
        let id = 1
        axios.get("http://localhost:8080/api/examinations/patient/" + id, { headers:{
                'Content-Type': 'application/json'
            }
        })
            .then((response) => {
                return response.data;
            })

            .then((data) => {
                setPosts(data);
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
            setPosts((prevPosts) =>
                prevPosts.filter((_, index) => index !== postIndex - 1)
            );
        })

    };
    const handleAdd = () =>{

    }

    return (
        <TableContainer style={{ width: '50%'}} className='div' align="center" component={Paper}>
            <Table  sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell align={"center"}>Date and time of appointment</TableCell>
                        <TableCell align={"center"} >Type of examination</TableCell>
                        <TableCell align={"center"}>Cancel appointment</TableCell>
                    </TableRow>
                </TableHead>
        <TableBody align="center">
            {posts.map((post, postIndex) => (
                <TableRow key={post.examinationId}>
                    <TableCell component="th" scope="row" padding={"normal"} align={"center"}>
                        {post.dateAndTimeOfAppointment}
                    </TableCell>
                    <TableCell component="th" scope="row" align={"center"}>
                        {post.typeOfExamination}
                    </TableCell>
                    {/*<TableCell>{post.body}</TableCell>*/}
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
                <TableCell align={"center"} colSpan={5}>
                    <Button
                        fullWidth={1}
                        variant="outlined"
                        color="error"
                        onClick={event => {
                            event.preventDefault();
                            window.location.href='./NewAppointment';
                        }}
                    >
                        Add
                    </Button>
                </TableCell>
            </TableRow>
        </TableBody>
            </Table>
        </TableContainer>
    );
}
export default MyAppointments
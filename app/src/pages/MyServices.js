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
import {useParams} from "react-router-dom";

function MyServices(props) {
    const [posts, setPosts] = useState([]);
    const [doctorId, setDoctorId] = useState('')
    const {id} = useParams()

    const fetchData = () => {
       console.log(id)
        axios.get("http://localhost:8080/api/services/doctor/" + id, { headers:{
                'Content-Type': 'application/json'
            }
        })
            .then((response) => {
                return response.data;
            })

            .then((data) => {
                setPosts(data);
                console.log(posts)
                setDoctorId(id) //promijeniti kad se bude radila autentifikacija
            });
    };

    useEffect(() => {
        fetchData();
    }, []);

    const handleDelete = (postIndex) => {

        const formData = {
            "doctorId": doctorId,
            "serviceId": postIndex
        }

        axios.post("http://localhost:8080/api/services/doctor_delete", formData,{
            headers:{
                'Content-Type': 'application/json'
            }
        }).then(response =>{
            window.location.reload()
            // setPosts((prevPosts) =>
            //     prevPosts.filter((_, index) => index !== postIndex - 1)
            // );
        })

    };
    const handleAdd = () =>{

    }

    return (
        <TableContainer style={{ width: '50%'}} className='div' align="center" component={Paper}>
            <Table  sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell align={"center"}>My services</TableCell>
                        <TableCell align={"center"}>Delete the service</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody align="center">
                    {posts.map((post, postIndex) => (
                        <TableRow key={post.serviceId}>
                            <TableCell component="th" scope="row" padding={"normal"} align={"center"}>
                                {post.serviceName}
                            </TableCell>

                            <TableCell align={"center"}>
                                <Button
                                    variant="outlined"
                                    color="error"
                                     onClick={() => handleDelete(post.serviceId)}
                                >
                                    Delete
                                </Button>
                            </TableCell>
                        </TableRow>
                    ))}
                    <TableRow>
                        <TableCell align={"center"} colSpan={5}>
                            <Button
                                fullWidth={1}
                                variant="outlined"
                                color="primary"
                                onClick={event => {
                                    event.preventDefault();
                                    console.log(id)
                                    window.location.href='http://localhost:3000/NewService/' + id;
                                }}
                            >
                                Add service
                            </Button>
                        </TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell align={"center"} colSpan={3}>
                            <Button
                                fullWidth={1}
                                variant="outlined"
                                color="secondary"
                                onClick={event => {
                                    event.preventDefault();
                                    console.log(id)
                                    window.location.href='http://localhost:3000/DoctorPage/' + id;
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
export default MyServices
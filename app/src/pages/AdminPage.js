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

function AdminPage() {
    const [posts, setPosts] = useState([]);

    const fetchData = () => {
        let id = 1
        axios.get("http://localhost:8080/api/doctors", { headers:{
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

        axios.delete("http://localhost:8080/api/doctors/" + postIndex, {
            headers:{
                'Content-Type': 'application/json'
            }
        }).then(response =>{
            console.log(response)
            if(response.status!=204){
                window.alert("Could not delete the doctor!");
            }
            else{
                window.location.reload()
                // setPosts((prevPosts) =>
                //     prevPosts.filter((_, index) => index !== postIndex - 1)
                // );
            }

        })

    };
    const handleAdd = () =>{

    }

    return (
        <TableContainer style={{ width: '50%'}} className='div' align="center" component={Paper}>
            <Table  sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell align={"center"}>Name</TableCell>
                        <TableCell align={"center"} >Surname</TableCell>
                        <TableCell align={"center"}>Delete Doctor</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody align="center">
                    {posts.map((post, postIndex) => (
                        <TableRow key={post.doctorId}>
                            <TableCell component="th" scope="row" padding={"normal"} align={"center"}>
                                {post.name}
                            </TableCell>
                            <TableCell component="th" scope="row" align={"center"}>
                                {post.surname}
                            </TableCell>
                            {/*<TableCell>{post.body}</TableCell>*/}
                            <TableCell align={"center"}>
                                <Button
                                    variant="outlined"
                                    color="error"
                                    onClick={() => handleDelete(post.doctorId)}
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
                                    window.location.href='./NewDoctor';
                                }}
                            >
                                Add
                            </Button>
                        </TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell align={"center"} colSpan={3}>
                            <Button
                                fullWidth={true}
                                variant="outlined"
                                color="secondary"
                                onClick={event => {
                                    event.preventDefault();
                                    window.location.href='http://localhost:3000';
                                }}                                >
                                Log Out
                            </Button>
                        </TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
    );
}
export default AdminPage
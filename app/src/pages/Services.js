import React, { Component, useState, useEffect, useCallback } from 'react';
import Box from '@mui/material/Box';
import axios from "axios";
import '../style/Home.css';

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


const Services = () => {

    const [services, setServices] = useState([])

    const fetchData = () => {
        axios.get("http://localhost:8080/api/services", {headers: {
                'Content-Type': 'application/json'
            }})
            .then(response => {
                return response.data

            })
            .then(data => {
                setServices(data)
            })
    }

    useEffect(() => {
        fetchData()
    }, [])

    return (

        <div style={{ width: '50%'}} className='div'>
            <Box sx={{ display: 'grid', gridTemplateRows: 'repeat(3, 1fr)' }}>
                {services.length > 0 && (
                    <Item>
                        {services.map(user => (
                            <Item key={user.serviceId}>{user.serviceName}</Item>
                        ))}
                    </Item>
                )}
            </Box>
        </div>
    )
}
export default Services;
import {
    TableContainer,
    Paper,
    Table,
    TableHead,
    TableRow,
    TableCell,
    TableBody,
    CircularProgress,
    Container,
    IconButton,
    Tooltip, Button
} from "@mui/material";


import React, {useEffect, useState} from "react";
import {BACKEND_API_URL} from "../../constants";
import axios from "axios";
import { Hospital } from "../../models/Hospital";

interface HospitalWithAge extends Hospital {
    avg_wage: number;
}
export const HospitalsByLowestDoctorSalary = () => {
    const [loading, setLoading] = useState(false);
    const [data, setData] = useState<HospitalWithAge[]>([]);


    useEffect(() => {
        const url = `${BACKEND_API_URL}/hospitals/filter`;
        const axiosHospital = async () => {
            setLoading(true);
            await axios.get<HospitalWithAge[]>(url)
                .then(response => {
                    setData(response.data)
                    setLoading(false);
                }, error => {
                    console.log(error);
                });
        };
        axiosHospital();
    }, []);


    return (
        <Container sx={{maxWidth:"xl", padding: '4em'}}>

            <h1>Hospitals by lowest wage of doctor</h1>

            {loading && <CircularProgress />}
            {!loading && data.length === 0 && <p>No hospitals found</p>}
            {!loading && data.length > 0 && (
                <TableContainer component={Paper}>
                    <Table sx={{ minWidth: 650 }}  aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell>#</TableCell>
                                <TableCell align="center">Name</TableCell>
                                <TableCell align="center">Address</TableCell>
                                <TableCell align="center">Phone Number</TableCell>
                                <TableCell align="center">Number of Beds</TableCell>
                                <TableCell align="center">Lowest Salary</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {data.map((hospital, index) => (
                                <TableRow key={index}>
                                    <TableCell component="th" scope="row">
                                        {index + 1}
                                    </TableCell>
                                    <TableCell align="center">{hospital.name}</TableCell>
                                    <TableCell align="center">{hospital.address}</TableCell>
                                    <TableCell align="center">{hospital.phoneNumber}</TableCell>
                                    <TableCell align="center">{hospital.numberOfBeds}</TableCell>
                                    <TableCell align="center">{hospital.avg_wage}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            )}
        </Container>
    );
};
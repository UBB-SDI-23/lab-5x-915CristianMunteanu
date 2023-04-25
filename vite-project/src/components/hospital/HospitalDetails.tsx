import {Card, CardActions, CardContent, IconButton} from "@mui/material";
import { Container } from "@mui/system";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { BACKEND_API_URL } from "../../constants";
import EditIcon from "@mui/icons-material/Edit";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import axios from "axios";
import { Hospital } from "../../models/Hospital";

export const HospitalDetails = () => {
    const { hospitalId } = useParams();
    const [hospital, setHospital] = useState<Hospital>();
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const url = `${BACKEND_API_URL}/hospitals/${hospitalId}`
        const axiosHospital = async () => {
            setLoading(true);
            await axios.get(url)
                .then(response => {
                    console.log(response.data);
                    const hospital = response.data;
                    setHospital(hospital);
                    setLoading(false);
                }, error => {
                    console.log(error);
                });
        };
        axiosHospital();


    }, [hospital]);


    return (
        <Container>
            <Card>
                <CardContent>
                <h1>Hospital Details</h1>
                <p>Hospital name: {hospital?.name}</p>
                <p>Hospital address: {hospital?.address}</p>
                <p>Hospital phoneNumber: {hospital?.phoneNumber}</p>
                <p>Hospital number of beds: {hospital?.numberOfBeds}</p>
                <p>Doctors:</p>
                <ul>
                    {hospital?.doctors?.map((doctor, index) => (
                        <CardContent key={doctor.id}>
                            <li>Doctor details</li>
                            <p >First Name : {doctor?.firstName}</p>
                            <p >Last Name : {doctor?.lastName}</p>
                            <p >Specialization : {doctor.specialization}</p>
                            <p >Phone Number : {doctor.contactNumber}</p>
                        </CardContent>
                    ))}
                </ul>
                </CardContent>
                <CardActions>
                    <IconButton component={Link} sx={{ mr: 3 }} to={`/hospitals/${hospitalId}/edit`}>
                        <EditIcon />
                    </IconButton>

                    <IconButton component={Link} sx={{ mr: 3 }} to={`/hospitals/${hospitalId}/delete`}>
                        <DeleteForeverIcon sx={{ color: "red" }} />
                    </IconButton>
                </CardActions>
            </Card>
        </Container>
    );
};


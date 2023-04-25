import {Link, useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";

import axios from "axios";
import {BACKEND_API_URL} from "../../constants";
import {Container} from "@mui/system";
import {Button, Card, CardActions, CardContent, IconButton, TextField} from "@mui/material";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import { Hospital } from "../../models/Hospital";





export const HospitalUpdate = () => {

    const navigate = useNavigate();

    const { hospitalId } = useParams();
    const [hospital, setHospital] = useState<Hospital>({
        name: "",
        address:"",
        phoneNumber:"",
        numberOfBeds:0,
    });

    const [loading, setLoading] = useState(false);


    useEffect(() => {
        const url = `${BACKEND_API_URL}/hospital/${hospitalId}`
        const axiosHospital = async () => {
            setLoading(true);
            await axios.get(url)
                .then(response => {
                    const hospital = response.data;
                    setHospital(hospital);
                    setLoading(false);
                }, error => {
                    console.log(error);
                });
        };
        axiosHospital();

    }, [hospitalId]);


    const updateHospital = async (event: { preventDefault: () => void }) => {
        event.preventDefault();
        try {
            setHospital(hospital);
            const response = await axios.put(`${BACKEND_API_URL}/hospitals/${hospitalId}`, hospital);
            navigate("/hospitals");
        } catch (error) {
            console.log(error);
        }
    };



    return (
        <Container>
            <Card>
                <CardContent>
                    <IconButton component={Link} sx={{ mr: 3 }} to={`/hospitals`}>
                        <ArrowBackIcon />
                    </IconButton>{" "}
                </CardContent>
            </Card>
            <Card >
                <CardContent>
                    <p>{hospital?.id}</p>
                    <form onSubmit={updateHospital}>
                    <TextField
                            id="nameOfHospital"
                            label="Name"
                            variant="outlined"
                            fullWidth
                            sx={{ mb: 2 }}
                            onChange={(event) => setHospital({ ...hospital, name: event.target.value })}
                        />
                        <TextField
                            id="address"
                            label="Address"
                            variant="outlined"
                            fullWidth
                            sx={{ mb: 2 }}
                            onChange={(event) => setHospital({ ...hospital, address: event.target.value })}
                        />
                        <TextField
                            id="phoneNumber"
                            label="Phone Number"
                            variant="outlined"
                            fullWidth
                            sx={{ mb: 2 }}
                            onChange={(event) => setHospital({ ...hospital, phoneNumber: event.target.value})}
                        />
                        <TextField
                            id="numberOfBeds"
                            label="Number of Beds"
                            variant="outlined"
                            fullWidth
                            sx={{ mb: 2 }}
                            onChange={(event) => setHospital({ ...hospital, numberOfBeds: parseInt(event.target.value) })}
                        />
                        <Button type="submit">Update Hospital</Button>
                    </form>
                </CardContent>
            </Card>
        </Container>
    );
};
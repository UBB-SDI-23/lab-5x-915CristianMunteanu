import { Button, Card, CardActions, CardContent, IconButton, TextField } from "@mui/material";
import { Container } from "@mui/system";
import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { BACKEND_API_URL } from "../../constants";
import EditIcon from "@mui/icons-material/Edit";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import axios from "axios";
import { Hospital } from "../../models/Hospital";

export const HospitalAdd = () => {
    const navigate = useNavigate();

    const [hospital, setHospital] = useState<Hospital>({
        name: "",
        address:"",
        phoneNumber:"",
        numberOfBeds:0,
    });

    const addHospital = async (event: { preventDefault: () => void }) => {
        event.preventDefault();
        try {
            await axios.post(`${BACKEND_API_URL}/hospitals`, hospital);
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
                    <form onSubmit={addHospital}>
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
                        
                        <Button type="submit">Add Hospital</Button>
                    </form>
                </CardContent>
                <CardActions></CardActions>
            </Card>
        </Container>
    );
};
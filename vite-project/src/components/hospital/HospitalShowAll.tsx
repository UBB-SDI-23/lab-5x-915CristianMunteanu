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
	Tooltip,
	Button,
} from "@mui/material";
import React from "react";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import ReadMoreIcon from "@mui/icons-material/ReadMore";
import EditIcon from "@mui/icons-material/Edit";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import AddIcon from "@mui/icons-material/Add";
import { Hospital } from "../../models/Hospital";
import { BACKEND_API_URL } from "../../constants";

export const AllHospitals = () => {
	const [loading, setLoading] = useState(false);
	const [hospitals, setHospitals] = useState<Hospital[]>([]);

	useEffect(() => {
		setLoading(true);
		fetch(`${BACKEND_API_URL}/hospitals`)
			.then((response) => response.json())
			.then((data) => {
				setHospitals(data);
				setLoading(false);
			});
	}, []);
	const [sortOrder, setSortOrder] = useState<'asc' | 'desc'>('asc');

    const handleSortByAdmin = () => {
        const sortedHospitals = [...hospitals].sort((a, b) => {
            if (sortOrder === 'asc') {
                return a.name.localeCompare(b.name);
            } else {
                return b.name.localeCompare(a.name);
            }
        });
        setHospitals(sortedHospitals);
        setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
    };
	return (
		<Container>
			<h1>All hospitals</h1>

			{loading && <CircularProgress />}
			{!loading && hospitals.length === 0 && <p>No hospitals found</p>}
			{!loading && (
				<IconButton component={Link} sx={{ mr: 3 }} to={`/hospitals/add`}>
					<Tooltip title="Add a new hospital" arrow>
						<AddIcon color="primary" />
					</Tooltip>
				</IconButton>
			)}
			{!loading && (
                <Button type={"submit"} component={Link} sx={{mr : 3}} to={'by-lowest-wage/'}>Check this statistical report by lowest doctor salary.</Button>
            )}
			<Button onClick={handleSortByAdmin}>Sort by name</Button>
			{!loading && hospitals.length > 0 && (
				<TableContainer component={Paper}>
					<Table sx={{ minWidth: 650 }} aria-label="simple table">
						<TableHead>
							<TableRow>
								<TableCell>#</TableCell>
								<TableCell align="center">Hospital Name</TableCell>
								<TableCell align="center">Address</TableCell>
								<TableCell align="center">Phone Number</TableCell>
								<TableCell align="center">Operations</TableCell>
							</TableRow>
						</TableHead>
						<TableBody>
							{hospitals.map((hospital, index) => (
								<TableRow key={hospital.id}>
									<TableCell component="th" scope="row">
										{index + 1}
									</TableCell>
									<TableCell component="th" scope="row">
										<Link to={`/hospitals/${hospital.id}/details`} title="View hospital details">
											{hospital.name}
										</Link>
									</TableCell>
									<TableCell align="right">{hospital.address}</TableCell>
									<TableCell align="right">{hospital.phoneNumber}</TableCell>
									<TableCell align="right">
										<IconButton
											component={Link}
											sx={{ mr: 3 }}
											to={`/hospitals/${hospital.id}/details`}>
											<Tooltip title="View hospital details" arrow>
												<ReadMoreIcon color="primary" />
											</Tooltip>
										</IconButton>

										<IconButton component={Link} sx={{ mr: 3 }} to={`/hospitals/${hospital.id}/edit`}>
											<EditIcon />
										</IconButton>

										<IconButton component={Link} sx={{ mr: 3 }} to={`/hospitals/${hospital.id}/delete`}>
											<DeleteForeverIcon sx={{ color: "red" }} />
										</IconButton>
									</TableCell>
								</TableRow>
							))}
						</TableBody>
					</Table>
				</TableContainer>
			)}
		</Container>
	);
};
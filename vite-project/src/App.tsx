import * as React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { AppHome } from "./components/AppHome";
import { AppMenu } from "./components/AppMenu";
import { AllHospitals } from "./components/hospital/HospitalShowAll";
import { HospitalDetails } from "./components/hospital/HospitalDetails";
import { HospitalAdd } from "./components/hospital/HospitalAdd";
import { HospitalDelete } from "./components/hospital/HospitalDelete";
import { HospitalUpdate } from "./components/hospital/HospitalUpdate";
import { HospitalsByLowestDoctorSalary } from "./components/hospital/HospitalsByLowestDoctorSalary";

function App() {
	return (
		<React.Fragment>
			<Router>
				<AppMenu />

				<Routes>
					<Route path="/" element={<AppHome />} />
					<Route path="/hospitals" element={<AllHospitals />} />
					<Route path="/hospitals/:hospitalId/details" element={<HospitalDetails/>}/>
					<Route path="/hospitals/add" element={<HospitalAdd/>}/>
					<Route path="/hospitals/by-lowest-wage/" element={<HospitalsByLowestDoctorSalary />}/>
					<Route path="/hospitals/:hospitalId/delete" element={<HospitalDelete/>}/>
					<Route path="/hospitals/:hospitalId/edit" element={<HospitalUpdate/>}/>
					<Route path="/hospitals/:hospitalId/edit" element={<HospitalUpdate/>}/>
				</Routes>
			</Router>
		</React.Fragment>
	);
}

export default App;
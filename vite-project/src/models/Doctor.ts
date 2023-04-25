import { Hospital } from "./Hospital";
import { Patient } from "./Patient";

export interface Doctor{
    id:number;
    lastName:string;
    firstName:string;
    specialization:string;
    salary:number;
    contactNumber:string;
    hospitalId:Hospital;
    patient:Patient[];
}
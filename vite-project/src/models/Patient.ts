import { Doctor } from "./Doctor";

export interface Patient{
    id:number;
    firstName:string;
    lastName:string;
    illness:string;
    phoneNumber:string;
    roomNumber:number;
    doctors:Doctor[];
}
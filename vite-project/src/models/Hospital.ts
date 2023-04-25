import { Doctor } from "./Doctor";

export interface Hospital{
    id?:number;
    name:string;
    address:string;
    phoneNumber:string;
    numberOfBeds:number;
    doctors?: Doctor[];
}
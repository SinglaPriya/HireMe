import { Recruiter } from './Recruiter';
import { Seeker } from './Seeker';


export class Job{
    jid: number;
    companyName: string;
    role: string;
    jobDesc: string;
    recruiter: Recruiter;
    applicants: Seeker[];
}
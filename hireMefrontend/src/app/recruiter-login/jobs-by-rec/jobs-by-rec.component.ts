import { Component, OnInit } from '@angular/core';
import { Recruiter } from 'src/app/objects/Recruiter';
import { GetJobsService } from 'src/app/services/get-jobs.service';
import { Router } from '@angular/router';
import { Job } from 'src/app/objects/Job';
import { FormGroup, FormControl } from '@angular/forms';
import { Seeker } from 'src/app/objects/Seeker';

@Component({
  selector: 'app-jobs-by-rec',
  templateUrl: './jobs-by-rec.component.html',
  styleUrls: ['./jobs-by-rec.component.css']
})
export class JobsByRecComponent implements OnInit {

  recruiter: Recruiter;
  jobs: Job[];
  applicants: Seeker[];
  addJobForm: FormGroup;
  job: Job;
  successMsg: string;
  errorMsg: string;

  constructor(private jobsService: GetJobsService, private route: Router) {
    this.recruiter = JSON.parse(sessionStorage.getItem('recruiter')) as Recruiter;
    this.jobsByRec();

    this.addJobForm = new FormGroup({
      companyName: new FormControl(),
      role: new FormControl(),
      jobDesc: new FormControl()
    });
   }

   jobsByRec() {
    this.jobsService.getJobsByRec(this.recruiter.email)
    .then(res => {
      console.log(res);
      this.jobs = JSON.parse(JSON.stringify(res)) as Job[];
    })
    .catch();
   }

   addJob() {
    this.successMsg = null;
    this.errorMsg = null;
    const job = new Job();
    job.companyName = this.addJobForm.get('companyName').value;
    job.jobDesc = this.addJobForm.get('jobDesc').value;
    job.role = this.addJobForm.get('role').value;
    job.recruiter = this.recruiter;
    this.jobsService.addJob(job)
    .then(res => this.successMsg = res)
    .catch(err => this.errorMsg = err);
   }

   viewApplicants(job: Job){
     this.applicants = job.applicants;

   }

  ngOnInit() {
  }

}

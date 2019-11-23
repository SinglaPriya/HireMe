import { Component, OnInit, Input } from '@angular/core';
import { Job } from 'src/app/objects/Job';
import { GetJobsService } from 'src/app/services/get-jobs.service';
import { Seeker } from 'src/app/objects/Seeker';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-all-jobs',
  templateUrl: './all-jobs.component.html',
  styleUrls: ['./all-jobs.component.css']
})
export class AllJobsComponent implements OnInit {

  applied = false;
  jobs: Job[];
  appliedJobs: Job[];
  appliedJob: Job;
  seeker: Seeker;
  buttonVal = 'Apply';

  constructor(private jobsService: GetJobsService, private route: ActivatedRoute) {
    this.seeker = JSON.parse(sessionStorage.getItem('seeker')) as Seeker;
    this.displayJobs();
    console.log(' in applu job ' + sessionStorage.getItem('seeker'));
   }

  displayJobs() {
    this.jobsService.getAllJobs()
      .then(res => {
        console.log(res);
        this.jobs = JSON.parse(JSON.stringify(res)) as Job[];
      })
      .catch();
    this.getAppliedJobs();
  }

  getAppliedJobs() {
    this.jobsService.getAppliedJobs(this.seeker.email)
    .then(res => {
      // console.log("applied jobs"+JSON.stringify(res));
      this.appliedJobs = JSON.parse(JSON.stringify(res)) as Job[];
    })
    .catch();

  }

  applyJob(job: Job) {
    this.appliedJob = job;
    this.jobsService.applyJob(job, this.seeker.email)
    .then(res => console.log(res))
    .catch();
    this.displayJobs();
    location.reload();
  }

  checkIfJobApplied(job: Job) {
    if (this.appliedJobs.length > 0) {
      for (const j of this.appliedJobs) {
        if (j.jobDesc === job.jobDesc && j.role === job.role && j.companyName === job.companyName) { return true; }
      }
      return false;
    } else { return false; }

  }

  ngOnInit() {
  }

}

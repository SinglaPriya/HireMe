import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Job } from '../objects/Job';

@Injectable({
  providedIn: 'root'
})
export class GetJobsService {

  private allJobsUrl = 'http://localhost:8888/jobsApi/allJobs';
  private allJobsByRecUrl = 'http://localhost:8888/jobsApi/allJobsByRec/';
  private applyJobUrl = 'http://localhost:8888/jobsApi/applyJob' ;
  private addJobUrl = 'http://localhost:8888/jobsApi/addJob' ;
  private appliedJobsBUrl = 'http://localhost:8888/jobSeekerApi/getAppliedJobs/';


  constructor(private http: HttpClient) { }

  getAllJobs(): Promise<any> {
    return this.http.get(this.allJobsUrl, {
      headers: new HttpHeaders(
          {
            'Content-Type': 'application/json',
          }
        )
    })
    .toPromise()
    .then(res => JSON.parse(JSON.stringify(res)))
    .catch(err => this.handleError(err));
  }

  getJobsByRec(rEmail: string) {

    return this.http.get(this.allJobsByRecUrl + rEmail, {
      headers: new HttpHeaders(
          {
            'Content-Type': 'application/json',
          }
        )
    })
    .toPromise()
    .then(res => JSON.parse(JSON.stringify(res)))
    .catch(err => this.handleError(err));
  }

  applyJob(job: Job, sEmail: string) {
    return this.http.post(this.applyJobUrl + '/' + sEmail, job)
    .toPromise()
    .then(res => JSON.stringify(res))
    .catch(err => this.handleError(err));

  }

  addJob(job: Job) {
    return this.http.post(this.addJobUrl, job)
    .toPromise()
    .then(res => JSON.stringify(res))
    .catch(err => this.handleError(err));

  }

  getAppliedJobs(sEmail: string) {
    return this.http.get(this.appliedJobsBUrl+sEmail)
    .toPromise()
    .then(res => JSON.parse(JSON.stringify(res)))
    .catch(err => this.handleError(err))

  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
}




}

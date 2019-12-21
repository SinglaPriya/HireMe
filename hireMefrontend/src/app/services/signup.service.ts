import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Seeker } from '../objects/Seeker';


@Injectable({
  providedIn: 'root'
})
export class SignupService {

  private sSignupUrl = 'http://localhost:8888/jobSeekerApi/registerSeeker';
  private rSignupUrl = 'http://localhost:8888/recruiterApi/registerRecruiter';

  constructor(private http: HttpClient) { }

  registerSeeker(seeker: any): Promise<string> {
    console.log(seeker);
    console.log(JSON.stringify(seeker));
    return this.http.post(this.sSignupUrl, JSON.stringify(seeker), {
      headers: new HttpHeaders(
          {
            'Content-Type': 'application/json',
          }
        )
    })
    .toPromise()
    .then(res => res.toString())
    .catch(err => this.handleError(err));
  }

  registerRecruiter(recruiter: any): Promise<string> {
    console.log(recruiter);
    console.log(JSON.stringify(recruiter));
    return this.http.post(this.rSignupUrl, JSON.stringify(recruiter), {
      headers: new HttpHeaders(
          {
            'Content-Type': 'application/json',
          }
        )
    })
    .toPromise()
    .then(res => res.toString())
    .catch(err => this.handleError(err));
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
}


}

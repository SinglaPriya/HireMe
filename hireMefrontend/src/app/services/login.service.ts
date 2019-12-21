import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Seeker } from '../objects/Seeker';
import { Recruiter } from '../objects/Recruiter';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private sLoginUrl = 'http://localhost:8888/jobSeekerApi/loginSeeker';
  private rLoginUrl = 'http://localhost:8888/recruiterApi/loginRecruiter';


  constructor(private http: HttpClient) { }

  loginSeeker(seeker: any): Promise<Seeker> {
    console.log(seeker);
    console.log(JSON.stringify(seeker));
    return this.http.post(this.sLoginUrl, JSON.stringify(seeker), {
      headers: new HttpHeaders(
          {
            'Content-Type': 'application/json',
          }
        )
    })
    .toPromise()
    .then(res => {
      (JSON.parse(JSON.stringify(res)));
      console.log(res);
      sessionStorage.setItem('seeker', JSON.stringify(res));
      console.log('ss ' + sessionStorage.getItem('seeker'));
    })
    .catch(err => this.handleError(err));
  }

  loginRecruiter(recruiter: any): Promise<Recruiter> {
    console.log(recruiter);
    console.log(JSON.stringify(recruiter));
    return this.http.post(this.rLoginUrl, JSON.stringify(recruiter), {
      headers: new HttpHeaders(
          {
            'Content-Type': 'application/json',
          }
        )
    })
    .toPromise()
    .then(res =>{
       (JSON.parse(JSON.stringify(res)));
       console.log(res);
       sessionStorage.setItem('recruiter', JSON.stringify(res));
       console.log('ss ' + sessionStorage.getItem('recruiter'));
      })
    .catch(err => this.handleError(err));
  }

  isSeekerLogin(){
    const seeker = sessionStorage.getItem('seeker');
    return !(seeker == null);
  }

  isRecruiterLogin(){
    const recruiter = sessionStorage.getItem('recruiter');
    return !(recruiter == null);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
}
}

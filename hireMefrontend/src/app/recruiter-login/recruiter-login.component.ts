import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { Recruiter } from '../objects/Recruiter';
import { SignupService } from '../services/signup.service';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-recruiter-login',
  templateUrl: './recruiter-login.component.html',
  styleUrls: ['./recruiter-login.component.css']
})
export class RecruiterLoginComponent implements OnInit {

  signupForm: FormGroup;
  loginForm: FormGroup;
  successMsg: string;
  errorMsg: string;

  constructor( private rSignupService: SignupService, private rLoginService: LoginService , private router: Router) {
    this.loginForm = new FormGroup({
      lemail: new FormControl(),
      lpassword: new FormControl(),
    });
  
    this.signupForm = new FormGroup({
      sname: new FormControl(),
      semail: new FormControl(),
      spassword: new FormControl(),
    });
    this.errorMsg = null;
    this.successMsg = null;
   }

  

   Login() {
    this.errorMsg = null;
    this.successMsg = null;
    let recruiter: Recruiter = new Recruiter();
    recruiter.email = this.loginForm.get('lemail').value;
    recruiter.name = null;
    recruiter.password = this.loginForm.get('lpassword').value;
    this.rLoginService.loginRecruiter(recruiter)
    .then(res => {
      console.log("rec" + JSON.parse(sessionStorage.getItem('recruiter')));
      console.log(sessionStorage.getItem('recruiter'));
      this.router.navigate(['jobsByRec']);
    })
    .catch(err => this.errorMsg = err);
   }

  signUp() {
    this.errorMsg = null;
    this.successMsg = null;
    // tslint:disable-next-line: prefer-const
    let recruiter: Recruiter = new Recruiter();
    recruiter.email = this.signupForm.get('semail').value;
    recruiter.name = this.signupForm.get('sname').value;
    recruiter.password = this.signupForm.get('spassword').value;
    this.rSignupService.registerRecruiter(recruiter)
    .then(res => this.successMsg = res)
    .catch(err => this.errorMsg = err);
  }

  ngOnInit() {
  }

}

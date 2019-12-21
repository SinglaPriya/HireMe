import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { Seeker } from '../objects/Seeker';
import { SignupService } from '../services/signup.service';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-seeker-login',
  templateUrl: './seeker-login.component.html',
  styleUrls: ['./seeker-login.component.css']
})
export class SeekerLoginComponent implements OnInit {

  signupForm: FormGroup;
  loginForm: FormGroup;
  successMsg: string;
  errorMsg: string;
  constructor(private router: Router , private sSignupService: SignupService, private sLoginService: LoginService ) {

    this.signupForm = new FormGroup({
      sname: new FormControl(),
      semail: new FormControl(),
      spassword: new FormControl(),
    });

    this.loginForm = new FormGroup({
      lemail: new FormControl('',Validators.email),
      lpassword: new FormControl('',Validators.required),
    });

   
   }

  Login() {
    this.errorMsg = null;
    this.successMsg = null;

    let seeker: Seeker = new Seeker();
    seeker.email = this.loginForm.get('lemail').value;
    seeker.name = null;
    seeker.password = this.loginForm.get('lpassword').value;
    this.sLoginService.loginSeeker(seeker)
    .then(res => {
      console.log(JSON.parse(sessionStorage.getItem('seeker')));
      this.router.navigate(['alljobs']);
    })
    .catch(err => this.errorMsg = err);
   }

  signUp() {
    this.errorMsg = null;
    this.successMsg = null;

    // tslint:disable-next-line: prefer-const
    let seeker: Seeker = new Seeker();
    seeker.email = this.signupForm.get('semail').value;
    seeker.name = this.signupForm.get('sname').value;
    seeker.password = this.signupForm.get('spassword').value;
    this.sSignupService.registerSeeker(seeker)
    .then(res => this.successMsg = res)
    .catch(err => this.errorMsg = err);
  }

  ngOnInit() {
  }

}

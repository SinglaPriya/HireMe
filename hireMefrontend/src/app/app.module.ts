import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RecruiterLoginComponent } from './recruiter-login/recruiter-login.component';
import { SeekerLoginComponent } from './seeker-login/seeker-login.component';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { SignupService } from './services/signup.service';
import { AllJobsComponent } from './seeker-login/all-jobs/all-jobs.component';
import { JobsByRecComponent } from './recruiter-login/jobs-by-rec/jobs-by-rec.component';
import { RouterModule } from '@angular/router';
import { LoginService } from './services/login.service';
import { GetJobsService } from './services/get-jobs.service';
import { RouterGuardSeekerService } from './services/router-guard-seeker.service';
import { RouteGuardRecruiterService } from './services/router-guard-recruiter.service';
import { HeaderComponent } from './header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    RecruiterLoginComponent,
    SeekerLoginComponent,
    AllJobsComponent,
    JobsByRecComponent,
    HeaderComponent,
  ],
  imports: [
    RouterModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [SignupService, LoginService, GetJobsService, RouterGuardSeekerService, RouteGuardRecruiterService],
  bootstrap: [AppComponent]
})
export class AppModule { }

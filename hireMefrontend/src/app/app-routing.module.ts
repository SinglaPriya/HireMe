import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RecruiterLoginComponent } from './recruiter-login/recruiter-login.component';
import { AppComponent } from './app.component';
import { AllJobsComponent } from './seeker-login/all-jobs/all-jobs.component';
import { SeekerLoginComponent } from './seeker-login/seeker-login.component';
import { RouterGuardSeekerService } from './services/router-guard-seeker.service';
import { JobsByRecComponent } from './recruiter-login/jobs-by-rec/jobs-by-rec.component';
import { RouteGuardRecruiterService } from './services/router-guard-recruiter.service';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'recruiterLogin', component: RecruiterLoginComponent},
  {path: 'seekerLogin', component: SeekerLoginComponent},
  {path: 'alljobs', component: AllJobsComponent, canActivate: [RouterGuardSeekerService]},
  {path: 'jobsByRec', component: JobsByRecComponent, canActivate: [RouteGuardRecruiterService]},
  {path: '**', component: AppComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { Injectable } from '@angular/core';
import { LoginService } from './login.service';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouteGuardRecruiterService implements CanActivate {

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.loginService.isRecruiterLogin) {
          return true;
        }
    return false;
}

  constructor(private loginService: LoginService,
              private router: Router) { }
}

import { Injectable } from '@angular/core';
import { LoginService } from './login.service';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouterGuardSeekerService implements CanActivate{

  constructor(private loginService: LoginService,
    private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      if (this.loginService.isSeekerLogin()) {
            return true;
          }
      return false;
  }
}

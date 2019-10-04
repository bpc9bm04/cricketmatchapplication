import { Injectable } from '@angular/core';
import {CanActivate } from '@angular/router'
import { Router } from '@angular/router';
import { AuthenticationService } from './modules/authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardService implements CanActivate{

  constructor(private router: Router, private auth: AuthenticationService) {

   }

   canActivate(){
    if(!this.auth.isTokenExpired()){
      return true;
    }
    this.router.navigate(['/login']);
    return false;
   }
}

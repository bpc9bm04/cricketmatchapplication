import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from './modules/authentication/authentication.service';
import { AfterViewChecked } from '@angular/core/src/metadata/lifecycle_hooks';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewChecked { 
  title = 'CMatchesUI';
  showLogout : boolean;
  constructor(private authenticationService: AuthenticationService, private router: Router) { 
    
  }
  ngAfterViewChecked(){
    if(!this.authenticationService.isTokenExpired()){
      this.showLogout = true;
    }
  }
   
  ngOnInit() {
    this.showLogout = false;
  }
  logout(){
    console.log('logout -->');
    this.showLogout = false;
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}


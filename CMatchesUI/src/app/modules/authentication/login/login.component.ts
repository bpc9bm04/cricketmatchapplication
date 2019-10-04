import { Component, OnInit } from '@angular/core';
import { User } from '../register/User';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user : User;
  constructor(private authenticationService: AuthenticationService, private router: Router) { 
    this.user = new User();
  }

  ngOnInit() {
  }

  loginUser(){
    console.log('user -->', this.user);
    this.authenticationService.login(this.user).subscribe(data=>{
      console.log('data --- ', data);
      if(data['token']){
        console.log('token' , data['token']);
        this.authenticationService.setToken(data['token']);
        this.router.navigate(['matches/all']);
      }
    });
  }
}

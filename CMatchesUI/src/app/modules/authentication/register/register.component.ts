import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { User } from './User';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'authentication-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser : User;
  constructor(private authService: AuthenticationService, private router : Router, private snackBar: MatSnackBar) {
    this.newUser = new User();
   }

  ngOnInit() {
  }

  registerUser(){
    console.log('user details -->', this.newUser)
    this.authService.registerUser(this.newUser).subscribe((data => {
      console.log('user data ', data);
      this.snackBar.open("Successfully register.", '',{
        duration:2000,
      });
      this.router.navigate(['/login']);
    }));
  }
}

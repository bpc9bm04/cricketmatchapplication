import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as jwt_decode from 'jwt-decode';
export const TOKEN_NAME : string = "jwt_token";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  springEndpoint:string;
  constructor(private httpClient: HttpClient) {
    this.springEndpoint = "http://localhost:8089/user";
   }

   registerUser(newUser){
     const url = this.springEndpoint + "/register";
     console.log('usrl ---' , url);
     return this.httpClient.post(url, newUser, {responseType: 'text'});
   }

   login(user){
    const url = this.springEndpoint + "/login";
    console.log('usrl ---' , url);
    return this.httpClient.post(url, user);
   }

   setToken(token){
    return localStorage.setItem(TOKEN_NAME , token);
   }

   getToken(){
     return localStorage.getItem(TOKEN_NAME);
   }

   deleteToken(){
     return localStorage.removeItem(TOKEN_NAME);
   }

   logout(){
    this.deleteToken();
   }

getTokenExpirationDate(token: string): Date{
  const decoded = jwt_decode(token);
  if(decoded.exp === undefined){
    return null;
  }
  const date = new Date(0);
  date.setUTCSeconds(decoded.exp);
  return date;
}

   isTokenExpired(token? :string) :boolean{
     if(!token) token = this.getToken();
     if(!token) return true;

     const tokenExpiryDate = this.getTokenExpirationDate(token);
     if(tokenExpiryDate === undefined || tokenExpiryDate ===null) {
       return false;
     }
     console.log( 'token date value ',tokenExpiryDate.valueOf);
     console.log( 'current date value ',new Date().valueOf);
     return !(tokenExpiryDate.valueOf()> new Date().valueOf());
   }
}

import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import {map} from 'rxjs/operators/';
import { retry } from 'rxjs/internal/operators/retry';
import { HttpInterceptor } from '@angular/common/http/src/interceptor';
import { HttpRequest } from '@angular/common/http';
import { HttpHandler } from '@angular/common/http';

import { AuthenticationService } from '../authentication/authentication.service';
import { HttpEvent } from '@angular/common/http/src/response';
import { HttpHeaders } from '@angular/common/http/src/headers';


@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor{
  
  
  
  constructor(private authenticationService: AuthenticationService) {
   

   } 

   intercept( httpRequest : HttpRequest<any>,  next: HttpHandler) : Observable<HttpEvent<any>>{
     console.log('intercept ');
     let str = httpRequest.url;
     
     if(str.indexOf('cricapi') == -1){
      httpRequest = httpRequest.clone({
        setHeaders: {
          Authorization: 'Bearer '+ this.authenticationService.getToken()          
          } 
      });   
    
    }
    return next.handle(httpRequest);
   }

  }

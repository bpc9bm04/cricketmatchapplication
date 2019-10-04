import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {MatchModule} from './modules/match/match.module';
import { AppComponent } from './app.component';
import {RouterModule, Routes} from '@angular/router';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatToolbarModule, MatToolbar} from '@angular/material/toolbar';
import { MatButtonModule} from '@angular/material/button';
import { MatDialogModule} from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { AuthguardService } from './authguard.service';

const appRoute: Routes = [
  {
    path:'',
    redirectTo: 'login',
    pathMatch: 'full',
    
  }
];
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    MatchModule,
    AuthenticationModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatDialogModule,
    FormsModule,
    MatInputModule,
    RouterModule.forRoot(appRoute),
  ],
  providers: [AuthguardService],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
//import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule} from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule} from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule} from '@angular/material/form-field';
import {MatchService} from './match.service';
import {InterceptorService} from './interceptor.service';


import { CricContainerComponent } from './components/cric-container/cric-container.component';
import { ContainerComponent } from './components/container/container.component';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { FavouritelistComponent} from './components/favouritelist/favouritelist.component';
import { QuickviewComponent } from './components/quickview/quickview.component';
import { MatchRouterModule} from './match-router.module';
import {MatchdetailsComponent } from './components/matchdetails/matchdetails.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';


@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    MatchRouterModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    MatDialogModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule
  ],
declarations: [CricContainerComponent, ContainerComponent, ThumbnailComponent, FavouritelistComponent,
   QuickviewComponent, MatchdetailsComponent],
  exports: [CricContainerComponent, ContainerComponent, ThumbnailComponent ,MatchRouterModule],
  providers: [MatchService, {
    provide:HTTP_INTERCEPTORS,
    useClass: InterceptorService,
    multi: true,
  }],
  entryComponents: [MatchdetailsComponent],
})
export class MatchModule { }

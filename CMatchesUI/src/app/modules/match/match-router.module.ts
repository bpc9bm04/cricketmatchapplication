import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import { ContainerComponent} from './components/container/container.component';

import { FavouritelistComponent} from './components/favouritelist/favouritelist.component';
import { CricContainerComponent } from './components/cric-container/cric-container.component';
import { QuickviewComponent } from './components/quickview/quickview.component';
import { AuthguardService } from '../../authguard.service';

const matchRoutes: Routes = [
    {
        path: 'matches',
        children: [    
            {
                path: '',
                redirectTo: 'matches/all',
                pathMatch: 'full'
            },   
            {
                path:'all',
                component: CricContainerComponent,               
                
            },     
            {
                path:'favouritelist',
                component: FavouritelistComponent,
                canActivate:[AuthguardService]
            },
            {
                path:'quickview',
                component: QuickviewComponent,
                canActivate:[AuthguardService]
            },
            
        ],
    },
];
@NgModule({
imports: [
    RouterModule.forChild(matchRoutes),
],
exports: [
    RouterModule,
]
})

export class MatchRouterModule{}
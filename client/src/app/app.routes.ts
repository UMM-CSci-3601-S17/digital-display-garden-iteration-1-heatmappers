// Imports
import { ModuleWithProviders }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { BedListComponent } from './Beds/bed-list.component';
import {UserListComponent} from "./users/user-list.component";

// Route Configuration
export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'users', component: UserListComponent },
    { path: 'beds', component: BedListComponent }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);
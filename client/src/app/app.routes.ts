// Imports
import { ModuleWithProviders }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { BedsComponent } from './Beds/beds.component';
import {UserListComponent} from "./users/user-list.component";

// Route Configuration
export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'users', component: UserListComponent },
    { path: 'beds', component: BedsComponent },
    { path: 'home', component: HomeComponent }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);
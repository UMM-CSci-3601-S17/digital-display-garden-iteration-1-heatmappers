import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { HttpModule, JsonpModule } from '@angular/http';

import { AppComponent }         from './app/app.component';
import { NavbarComponent } from './app/navbar/navbar.component';
import { HomeComponent} from './app/home/home.component';
import { BedsComponent} from './app/Beds/beds.component';
import { UserListComponent } from './app/users/user-list.component';
import { UserListService } from './app/users/user-list.service';
import { routing } from './app/app.routes';
import { FormsModule } from '@angular/forms';

import { PipeModule } from './pipe.module';

//import { DemoComponent } from './demo.component';
import { DisqusModule } from 'angular2-disqus';  //or for angular-cli the path will be ../../node_modules/angular2-disqus
import { MdCardModule } from '@angular2-material/card';
import { MdToolbarModule } from '@angular2-material/toolbar';
import { MdButtonModule } from '@angular2-material/button';
import { MdInputModule } from '@angular2-material/input';




@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        JsonpModule,
        routing,
        FormsModule,
        PipeModule,
        DisqusModule,
        MdCardModule,
        MdToolbarModule,
        MdButtonModule,
        MdInputModule
    ],
    declarations: [
        AppComponent,
        HomeComponent,
        NavbarComponent,
        UserListComponent,
        BedsComponent
    ],
    providers: [ UserListService ],
    bootstrap: [ AppComponent ]
})

export class AppModule {}

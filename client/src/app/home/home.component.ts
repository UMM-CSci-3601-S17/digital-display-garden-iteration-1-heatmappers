import { Component } from '@angular/core';

@Component({
    templateUrl: 'home.component.html'
})

// Component class
export class HomeComponent {
    public text: string;

    constructor() {
        this.text = "Welcome to the Digital Display Garden!";
    }

    public goQROnIphone() {
        window.location.href='http://www.cnn.com/';
    }

    public goQROnAndroid() {
        window.location.href='http://www.cnn.com/';
    }

}
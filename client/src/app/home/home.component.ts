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
        window.location.href='https://itunes.apple.com/us/app/quick-scan-qr-code-reader/id483336864?mt=8';
    }

    public goQROnAndroid() {
        window.location.href='https://play.google.com/store/apps/details?id=com.ihandysoft.barcode&hl=en';
    }

}
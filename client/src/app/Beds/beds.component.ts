import { Component } from '@angular/core';

@Component({
    templateUrl: 'beds.component.html'
})

// Component class
export class BedsComponent {
    public text: string;

    constructor() {
        this.text = "This is a beds page!";
    }
}
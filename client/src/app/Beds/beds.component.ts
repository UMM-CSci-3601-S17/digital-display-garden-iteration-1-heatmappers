import {Component, OnInit} from '@angular/core';
import {BedListService} from "./bed-list.service";
import {Bed} from "./bed";

@Component({
    templateUrl: 'beds.component.html'
})

// Component class
export class BedsComponent implements OnInit{
    public text: string;

    // constructor() {
    //     this.text = "This is a beds page!";
    // }

    public bed: Bed = null;
    private id: string;

    constructor(private bedsListService: BedListService) {
        // this.beds = this.bedsListService.getBedss();
    }

    private subscribeToServiceForId() {
        if (this.id) {
            this.bedsListService.getBedsById(this.id).subscribe(
                bed => this.bed = bed,
                err => {
                    console.log(err);
                }
            );
        }
    }

    setId(id: string) {
        this.id = id;
        this.subscribeToServiceForId();
    }

    ngOnInit(): void {
        this.subscribeToServiceForId();
    }
}
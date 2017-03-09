import { Component, OnInit } from '@angular/core';
import { BedListService } from "./bed-list.service";
import { Bed } from "./bed";
import { FilterBy } from "../users/filter.pipe";

@Component({
    selector: 'bed-list-component',
    templateUrl: 'bed-list.component.html',
    providers: [ FilterBy ]
})

export class BedListComponent implements OnInit {
    public beds: Bed[];


    constructor(private bedListService: BedListService) {
        // this.todos = this.todoListService.getTodos();
    }

    ngOnInit(): void {
        this.bedListService.getBeds().subscribe(
            beds => this.beds = beds,
            err => {
                console.log(err);
            })
    };

    //Try to make it so when a flower is clicked it would route to that flower
    // private requestData(_id: any): any {
    //     let holder = Array();
    //     let searchAdd = "?";
    //     let parameters = "";
    //
    //     if (_id != "")
    //         holder["_id"] = _id;
    //
    //     for (let param in holder) {
    //         parameters = parameters + searchAdd + param  + "=" + holder[param];
    //         searchAdd = "&";
    //     }
    //
    //     return parameters;
    // }
    //
    // public request(_id: any) {
    //     let requestParam: string = this.requestData(_id);
    //     this.bedListService.getFilteredFlower(requestParam).subscribe(
    //         beds => this.beds = beds,
    //         err => {
    //             console.log(err);
    //         }
    //     );
    // }

}
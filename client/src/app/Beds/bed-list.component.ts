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
    public searchOwner: string = "";
    public searchCategory: string = "";
    public searchStatus: string = "";

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

    private requestData(_id: any): any {
        let holder = Array();
        let searchAdd = "?";
        let parameters = "";

        if (_id != "")
            holder["_id"] = _id;

        for (let param in holder) {
            parameters = parameters + searchAdd + param  + "=" + holder[param];
            searchAdd = "&";
        }

        return parameters;
    }

    public request(_id: any) {
        let requestParam: string = this.requestData(_id);
        this.bedListService.getFilteredFlower(requestParam).subscribe(
            beds => this.beds = beds,
            err => {
                console.log(err);
            }
        );
    }

        // private requestData(owner: string, category: string, status: any): string {
    //     let holder = Array();
    //     let searchAdd = "?";
    //     let parameters = "";
    //
    //
    //     if (owner != "")
    //         holder["owner"] = owner;
    //
    //     if (category != "")
    //         holder["category"] = category;
    //
    //     if (status != "")
    //         holder["status"] = status;
    //
    //     for (let param in holder) {
    //         parameters = parameters + searchAdd + param  + "=" + holder[param];
    //         searchAdd = "&";
    //     }
    //
    //     return parameters;
    // }
    //
    // public request(owner: string, category: string, status: any) {
    //     let requestParam: string = this.requestData(owner, category, status);
    //     this.todoListService.getFilteredTodos(requestParam).subscribe(
    //         todos => this.todos = todos,
    //         err => {
    //             console.log(err);
    //         }
    //     );
    //
    //
    // }
}
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Bed} from './bed';
import { Observable } from "rxjs";

@Injectable()
export class BedListService {

    private bedUrl: string = API_URL + "flowers" ;

    constructor(private http: Http) {
    }


    getBeds(): Observable<Bed[]> {
        return this.http.request(this.bedUrl).map(res => res.json());
    }

    getBedsById(id: string): Observable<Bed> {
        return this.http.request(this.bedUrl + "/" + id).map(res => res.json());
    }

    getBedwithFlowers(): Observable<Bed[]> {
        return this.http.request(this.bedUrl+ "/getBeds").map(res => res.json());
    }


    //// getFlowersInBedById(id: string): Observable<Todo> {
    //     return this.http.request(this.bedUrl + "/" + id).map(res => res.json());
    // }
    //



    getFlowersInBedById(id: string): Observable<Bed> {
         return this.http.request(this.bedUrl + "/" + id).map(res => res.json());
     }

    // getFilteredBeds(parameters: string): Observable<Todo[]> {
    //     return this.http.request(this.bedUrl + parameters).map(res => res.json());
    // }

}
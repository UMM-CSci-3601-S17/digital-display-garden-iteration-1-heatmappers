// import { ComponentFixture, TestBed, async } from "@angular/core/testing";
// import { Bed } from "./bed";
// import { BedComponent } from "./beds.component";
// import { BedListService } from "./bed-list.service";
// import { Observable } from "rxjs";
// import { PipeModule } from "../../pipe.module";
//
// describe("Bed component", () => {
//
//     let bedComponent: BedComponent;
//     let fixture: ComponentFixture<BedComponent>;
//
//     let bedListServiceStub: {
//         getBedById: (bedId: string) => Observable<Bed> // might use ObjectID?
//     };
//
//     beforeEach(() => {
//         // stub UserService for test purposes
//         bedListServiceStub = {
//             getBedsById: (bedId: string) => Observable.of([
//                 {
//                     id: "uniqueId1",
//                     location:"Bed1"
//                 },
//                 {
//                     id:"uniqueId2",
//                     location:"Bed2"
//                 },
//                 {
//                     id:"uniqueId3",
//                     location:"Bed3"
//                 }
//             ].find(bed => bed.id === bedId))
//         };
//
//         TestBed.configureTestingModule({
//             declarations: [ BedComponent ],
//             providers:    [ { provide: BedListService, useValue: bedListServiceStub } ]
//         })
//     });
//
//     beforeEach(async(() => {
//         TestBed.compileComponents().then(() => {
//             fixture = TestBed.createComponent(BedComponent);
//             bedComponent = fixture.componentInstance;
//         });
//     }));
//
//     it("can retrieve Bed 1 by ID", () => {
//         bedComponent.setId("uniqueID1");
//         expect(bedComponent.bed).toBeDefined();
//         expect(bedComponent.bed.location).toBe("Bed1");
//
//     });
//
//     it("returns undefined for Santa", () => {
//         bedComponent.setId("Santa");
//         expect(bedComponent.bed).not.toBeDefined();
//     });
//
// });
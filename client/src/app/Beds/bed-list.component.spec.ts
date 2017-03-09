// import { ComponentFixture, TestBed, async } from "@angular/core/testing";
// import { Bed } from "./bed";
// import { BedListComponent } from "./bed-list.component";
// import { BedListService } from "./bed-list.service";
// import { Observable } from "rxjs";
// import { PipeModule } from "../../pipe.module";
//
// describe("Bed list", () => {
//
//     let bedList: BedListComponent;
//     let fixture: ComponentFixture<BedListComponent>;
//
//     let bedListServiceStub: {
//         getBeds: () => Observable<Bed[]>
//     };
//
//     beforeEach(() => {
//         // stub UserService for test purposes
//         bedListServiceStub = {
//             getBeds: () => Observable.of([
//                 {
//                     id: "uniqueId1",
//                     location:"Bed1"
//                 },
//                 {
//                     id: "uniqueId2",
//                     location:"Bed2"
//                 },
//                 {
//                     id: "uniqueId3",
//                     location:"Bed3"
//                 }
//                 ])
//         };
//
//         TestBed.configureTestingModule({
//             declarations: [ BedListComponent ],
//             // providers:    [ UserListService ]  // NO! Don't provide the real service!
//             // Provide a test-double instead
//             providers:    [ { provide: BedListService, useValue: bedListServiceStub } ]
//         })
//     });
//
//     beforeEach(async(() => {
//         TestBed.compileComponents().then(() => {
//             fixture = TestBed.createComponent(BedListComponent);
//             bedList = fixture.componentInstance;
//             fixture.detectChanges();
//         });
//     }));
//
//     it("contains all the beds", () => {
//         expect(bedList.beds.id.length).toBe(3);
//     });
//
//     it("contains a bed located at Bed 2", () => {
//         expect(bedList.beds.some((bed: Bed) => bed.location)).toBe("Bed2");
//     });
//
//     it("doesn't contain a Bed located at Bed4", () => {
//         expect(bedList.beds.some((bed: Bed) => bed.location)).toBe("Bed4");
//     });
//
//     it("has a specfic Specific and unique Id ", () => {
//         expect(bedList.beds.filter((bed: Bed) => bed.id === "uniqueID1")).toBe(true);
//     });
//
// });

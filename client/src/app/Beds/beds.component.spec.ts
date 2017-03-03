import { TestBed, async } from "@angular/core/testing";
import {BedsComponent} from "./beds.component";

describe("Beds", () => {

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [BedsComponent]
        });
    });

    beforeEach(async(() => {
        TestBed.compileComponents();
    }));

    it("says a message", async(() => {
        const comp = TestBed.createComponent(BedsComponent);
        expect(comp.componentInstance.text).toBe("This is the beds page!");
    }));
});
import { TestBed, async } from "@angular/core/testing";
import {HomeComponent} from "./home.component";

describe("Digital Display Garden", () => {

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [HomeComponent]
        });
    });

    beforeEach(async(() => {
        TestBed.compileComponents();
    }));

    it("says a message", async(() => {
        const comp = TestBed.createComponent(HomeComponent);
        expect(comp.componentInstance.text).toBe("Welcome to the Digital Display Garden!");
    }));
});
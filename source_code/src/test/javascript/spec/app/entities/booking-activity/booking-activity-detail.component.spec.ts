/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HomespaTestModule } from '../../../test.module';
import { BookingActivityDetailComponent } from 'app/entities/booking-activity/booking-activity-detail.component';
import { BookingActivity } from 'app/shared/model/booking-activity.model';

describe('Component Tests', () => {
    describe('BookingActivity Management Detail Component', () => {
        let comp: BookingActivityDetailComponent;
        let fixture: ComponentFixture<BookingActivityDetailComponent>;
        const route = ({ data: of({ bookingActivity: new BookingActivity(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HomespaTestModule],
                declarations: [BookingActivityDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BookingActivityDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BookingActivityDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.bookingActivity).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

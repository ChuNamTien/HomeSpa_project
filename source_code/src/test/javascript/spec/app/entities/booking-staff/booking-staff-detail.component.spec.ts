/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HomespaTestModule } from '../../../test.module';
import { BookingStaffDetailComponent } from 'app/entities/booking-staff/booking-staff-detail.component';
import { BookingStaff } from 'app/shared/model/booking-staff.model';

describe('Component Tests', () => {
    describe('BookingStaff Management Detail Component', () => {
        let comp: BookingStaffDetailComponent;
        let fixture: ComponentFixture<BookingStaffDetailComponent>;
        const route = ({ data: of({ bookingStaff: new BookingStaff(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HomespaTestModule],
                declarations: [BookingStaffDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BookingStaffDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BookingStaffDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.bookingStaff).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

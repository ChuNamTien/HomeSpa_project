/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { HomespaTestModule } from '../../../test.module';
import { BookingStaffUpdateComponent } from 'app/entities/booking-staff/booking-staff-update.component';
import { BookingStaffService } from 'app/entities/booking-staff/booking-staff.service';
import { BookingStaff } from 'app/shared/model/booking-staff.model';

describe('Component Tests', () => {
    describe('BookingStaff Management Update Component', () => {
        let comp: BookingStaffUpdateComponent;
        let fixture: ComponentFixture<BookingStaffUpdateComponent>;
        let service: BookingStaffService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HomespaTestModule],
                declarations: [BookingStaffUpdateComponent]
            })
                .overrideTemplate(BookingStaffUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BookingStaffUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BookingStaffService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BookingStaff(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.bookingStaff = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BookingStaff();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.bookingStaff = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});

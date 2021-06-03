/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { HomespaTestModule } from '../../../test.module';
import { BookingActivityUpdateComponent } from 'app/entities/booking-activity/booking-activity-update.component';
import { BookingActivityService } from 'app/entities/booking-activity/booking-activity.service';
import { BookingActivity } from 'app/shared/model/booking-activity.model';

describe('Component Tests', () => {
    describe('BookingActivity Management Update Component', () => {
        let comp: BookingActivityUpdateComponent;
        let fixture: ComponentFixture<BookingActivityUpdateComponent>;
        let service: BookingActivityService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HomespaTestModule],
                declarations: [BookingActivityUpdateComponent]
            })
                .overrideTemplate(BookingActivityUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BookingActivityUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BookingActivityService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BookingActivity(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.bookingActivity = entity;
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
                    const entity = new BookingActivity();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.bookingActivity = entity;
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

/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { HomespaTestModule } from '../../../test.module';
import { BookingStaffDeleteDialogComponent } from 'app/entities/booking-staff/booking-staff-delete-dialog.component';
import { BookingStaffService } from 'app/entities/booking-staff/booking-staff.service';

describe('Component Tests', () => {
    describe('BookingStaff Management Delete Component', () => {
        let comp: BookingStaffDeleteDialogComponent;
        let fixture: ComponentFixture<BookingStaffDeleteDialogComponent>;
        let service: BookingStaffService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HomespaTestModule],
                declarations: [BookingStaffDeleteDialogComponent]
            })
                .overrideTemplate(BookingStaffDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BookingStaffDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BookingStaffService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});

/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { HomespaTestModule } from '../../../test.module';
import { ServImgDeleteDialogComponent } from 'app/entities/serv-img/serv-img-delete-dialog.component';
import { ServImgService } from 'app/entities/serv-img/serv-img.service';

describe('Component Tests', () => {
    describe('ServImg Management Delete Component', () => {
        let comp: ServImgDeleteDialogComponent;
        let fixture: ComponentFixture<ServImgDeleteDialogComponent>;
        let service: ServImgService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HomespaTestModule],
                declarations: [ServImgDeleteDialogComponent]
            })
                .overrideTemplate(ServImgDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ServImgDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ServImgService);
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

/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { HomespaTestModule } from '../../../test.module';
import { PartnerImgDeleteDialogComponent } from 'app/entities/partner-img/partner-img-delete-dialog.component';
import { PartnerImgService } from 'app/entities/partner-img/partner-img.service';

describe('Component Tests', () => {
    describe('PartnerImg Management Delete Component', () => {
        let comp: PartnerImgDeleteDialogComponent;
        let fixture: ComponentFixture<PartnerImgDeleteDialogComponent>;
        let service: PartnerImgService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HomespaTestModule],
                declarations: [PartnerImgDeleteDialogComponent]
            })
                .overrideTemplate(PartnerImgDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PartnerImgDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PartnerImgService);
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

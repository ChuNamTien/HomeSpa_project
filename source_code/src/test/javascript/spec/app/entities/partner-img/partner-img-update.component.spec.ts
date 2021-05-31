/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { HomespaTestModule } from '../../../test.module';
import { PartnerImgUpdateComponent } from 'app/entities/partner-img/partner-img-update.component';
import { PartnerImgService } from 'app/entities/partner-img/partner-img.service';
import { PartnerImg } from 'app/shared/model/partner-img.model';

describe('Component Tests', () => {
    describe('PartnerImg Management Update Component', () => {
        let comp: PartnerImgUpdateComponent;
        let fixture: ComponentFixture<PartnerImgUpdateComponent>;
        let service: PartnerImgService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HomespaTestModule],
                declarations: [PartnerImgUpdateComponent]
            })
                .overrideTemplate(PartnerImgUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PartnerImgUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PartnerImgService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PartnerImg(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.partnerImg = entity;
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
                    const entity = new PartnerImg();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.partnerImg = entity;
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

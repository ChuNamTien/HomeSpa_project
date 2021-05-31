/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { HomespaTestModule } from '../../../test.module';
import { ServImgUpdateComponent } from 'app/entities/serv-img/serv-img-update.component';
import { ServImgService } from 'app/entities/serv-img/serv-img.service';
import { ServImg } from 'app/shared/model/serv-img.model';

describe('Component Tests', () => {
    describe('ServImg Management Update Component', () => {
        let comp: ServImgUpdateComponent;
        let fixture: ComponentFixture<ServImgUpdateComponent>;
        let service: ServImgService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HomespaTestModule],
                declarations: [ServImgUpdateComponent]
            })
                .overrideTemplate(ServImgUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ServImgUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ServImgService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ServImg(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.servImg = entity;
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
                    const entity = new ServImg();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.servImg = entity;
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

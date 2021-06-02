/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { HomespaTestModule } from '../../../test.module';
import { ServUpdateComponent } from 'app/entities/serv/serv-update.component';
import { ServService } from 'app/entities/serv/serv.service';
import { Serv } from 'app/shared/model/serv.model';

describe('Component Tests', () => {
    describe('Serv Management Update Component', () => {
        let comp: ServUpdateComponent;
        let fixture: ComponentFixture<ServUpdateComponent>;
        let service: ServService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HomespaTestModule],
                declarations: [ServUpdateComponent]
            })
                .overrideTemplate(ServUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ServUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ServService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Serv(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.serv = entity;
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
                    const entity = new Serv();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.serv = entity;
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

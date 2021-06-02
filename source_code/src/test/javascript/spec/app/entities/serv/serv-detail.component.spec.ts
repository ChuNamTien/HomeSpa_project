/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HomespaTestModule } from '../../../test.module';
import { ServDetailComponent } from 'app/entities/serv/serv-detail.component';
import { Serv } from 'app/shared/model/serv.model';

describe('Component Tests', () => {
    describe('Serv Management Detail Component', () => {
        let comp: ServDetailComponent;
        let fixture: ComponentFixture<ServDetailComponent>;
        const route = ({ data: of({ serv: new Serv(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HomespaTestModule],
                declarations: [ServDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ServDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ServDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.serv).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HomespaTestModule } from '../../../test.module';
import { ServImgDetailComponent } from 'app/entities/serv-img/serv-img-detail.component';
import { ServImg } from 'app/shared/model/serv-img.model';

describe('Component Tests', () => {
    describe('ServImg Management Detail Component', () => {
        let comp: ServImgDetailComponent;
        let fixture: ComponentFixture<ServImgDetailComponent>;
        const route = ({ data: of({ servImg: new ServImg(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HomespaTestModule],
                declarations: [ServImgDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ServImgDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ServImgDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.servImg).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

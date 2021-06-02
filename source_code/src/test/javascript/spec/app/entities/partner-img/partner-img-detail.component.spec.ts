/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HomespaTestModule } from '../../../test.module';
import { PartnerImgDetailComponent } from 'app/entities/partner-img/partner-img-detail.component';
import { PartnerImg } from 'app/shared/model/partner-img.model';

describe('Component Tests', () => {
    describe('PartnerImg Management Detail Component', () => {
        let comp: PartnerImgDetailComponent;
        let fixture: ComponentFixture<PartnerImgDetailComponent>;
        const route = ({ data: of({ partnerImg: new PartnerImg(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HomespaTestModule],
                declarations: [PartnerImgDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PartnerImgDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PartnerImgDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.partnerImg).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

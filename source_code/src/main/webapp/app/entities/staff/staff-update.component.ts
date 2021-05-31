import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IStaff } from 'app/shared/model/staff.model';
import { StaffService } from './staff.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category';
import { ITreatment } from 'app/shared/model/treatment.model';
import { TreatmentService } from 'app/entities/treatment';
import { IPartner } from 'app/shared/model/partner.model';
import { PartnerService } from 'app/entities/partner';

@Component({
    selector: 'jhi-staff-update',
    templateUrl: './staff-update.component.html'
})
export class StaffUpdateComponent implements OnInit {
    staff: IStaff;
    isSaving: boolean;

    categories: ICategory[];

    treatments: ITreatment[];

    partners: IPartner[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private staffService: StaffService,
        private categoryService: CategoryService,
        private treatmentService: TreatmentService,
        private partnerService: PartnerService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ staff }) => {
            this.staff = staff;
        });
        this.categoryService.query().subscribe(
            (res: HttpResponse<ICategory[]>) => {
                this.categories = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.treatmentService.query().subscribe(
            (res: HttpResponse<ITreatment[]>) => {
                this.treatments = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.partnerService.query().subscribe(
            (res: HttpResponse<IPartner[]>) => {
                this.partners = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.staff.id !== undefined) {
            this.subscribeToSaveResponse(this.staffService.update(this.staff));
        } else {
            this.subscribeToSaveResponse(this.staffService.create(this.staff));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IStaff>>) {
        result.subscribe((res: HttpResponse<IStaff>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCategoryById(index: number, item: ICategory) {
        return item.id;
    }

    trackTreatmentById(index: number, item: ITreatment) {
        return item.id;
    }

    trackPartnerById(index: number, item: IPartner) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITreatment } from 'app/shared/model/treatment.model';
import { TreatmentService } from './treatment.service';

@Component({
    selector: 'jhi-treatment-update',
    templateUrl: './treatment-update.component.html'
})
export class TreatmentUpdateComponent implements OnInit {
    treatment: ITreatment;
    isSaving: boolean;
    createdDate: string;
    lastModifiedBy: string;
    lastModifiedDate: string;

    constructor(private treatmentService: TreatmentService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ treatment }) => {
            this.treatment = treatment;
            this.createdDate = this.treatment.createdDate != null ? this.treatment.createdDate.format(DATE_TIME_FORMAT) : null;
            this.lastModifiedBy = this.treatment.lastModifiedBy != null ? this.treatment.lastModifiedBy.format(DATE_TIME_FORMAT) : null;
            this.lastModifiedDate =
                this.treatment.lastModifiedDate != null ? this.treatment.lastModifiedDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.treatment.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
        this.treatment.lastModifiedBy = this.lastModifiedBy != null ? moment(this.lastModifiedBy, DATE_TIME_FORMAT) : null;
        this.treatment.lastModifiedDate = this.lastModifiedDate != null ? moment(this.lastModifiedDate, DATE_TIME_FORMAT) : null;
        if (this.treatment.id !== undefined) {
            this.subscribeToSaveResponse(this.treatmentService.update(this.treatment));
        } else {
            this.subscribeToSaveResponse(this.treatmentService.create(this.treatment));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITreatment>>) {
        result.subscribe((res: HttpResponse<ITreatment>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

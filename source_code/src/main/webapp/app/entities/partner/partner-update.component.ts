import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IPartner } from 'app/shared/model/partner.model';
import { PartnerService } from './partner.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category';

@Component({
    selector: 'jhi-partner-update',
    templateUrl: './partner-update.component.html'
})
export class PartnerUpdateComponent implements OnInit {
    partner: IPartner;
    isSaving: boolean;

    categories: ICategory[];
    openTime: string;
    closeTime: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private partnerService: PartnerService,
        private categoryService: CategoryService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ partner }) => {
            this.partner = partner;
            this.openTime = this.partner.openTime != null ? this.partner.openTime.format(DATE_TIME_FORMAT) : null;
            this.closeTime = this.partner.closeTime != null ? this.partner.closeTime.format(DATE_TIME_FORMAT) : null;
        });
        this.categoryService.query().subscribe(
            (res: HttpResponse<ICategory[]>) => {
                this.categories = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.partner.openTime = this.openTime != null ? moment(this.openTime, DATE_TIME_FORMAT) : null;
        this.partner.closeTime = this.closeTime != null ? moment(this.closeTime, DATE_TIME_FORMAT) : null;
        if (this.partner.id !== undefined) {
            this.subscribeToSaveResponse(this.partnerService.update(this.partner));
        } else {
            this.subscribeToSaveResponse(this.partnerService.create(this.partner));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPartner>>) {
        result.subscribe((res: HttpResponse<IPartner>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

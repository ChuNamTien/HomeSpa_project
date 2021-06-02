import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IVoucher } from 'app/shared/model/voucher.model';
import { VoucherService } from './voucher.service';

@Component({
    selector: 'jhi-voucher-update',
    templateUrl: './voucher-update.component.html'
})
export class VoucherUpdateComponent implements OnInit {
    voucher: IVoucher;
    isSaving: boolean;
    createdDate: string;
    lastModifiedBy: string;
    lastModifiedDate: string;

    constructor(private voucherService: VoucherService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ voucher }) => {
            this.voucher = voucher;
            this.createdDate = this.voucher.createdDate != null ? this.voucher.createdDate.format(DATE_TIME_FORMAT) : null;
            this.lastModifiedBy = this.voucher.lastModifiedBy != null ? this.voucher.lastModifiedBy.format(DATE_TIME_FORMAT) : null;
            this.lastModifiedDate = this.voucher.lastModifiedDate != null ? this.voucher.lastModifiedDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.voucher.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
        this.voucher.lastModifiedBy = this.lastModifiedBy != null ? moment(this.lastModifiedBy, DATE_TIME_FORMAT) : null;
        this.voucher.lastModifiedDate = this.lastModifiedDate != null ? moment(this.lastModifiedDate, DATE_TIME_FORMAT) : null;
        if (this.voucher.id !== undefined) {
            this.subscribeToSaveResponse(this.voucherService.update(this.voucher));
        } else {
            this.subscribeToSaveResponse(this.voucherService.create(this.voucher));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IVoucher>>) {
        result.subscribe((res: HttpResponse<IVoucher>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

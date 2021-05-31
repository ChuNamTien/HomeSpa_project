import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IBooking } from 'app/shared/model/booking.model';
import { BookingService } from './booking.service';
import { ITreatment } from 'app/shared/model/treatment.model';
import { TreatmentService } from 'app/entities/treatment';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer';
import { IVoucher } from 'app/shared/model/voucher.model';
import { VoucherService } from 'app/entities/voucher';

@Component({
    selector: 'jhi-booking-update',
    templateUrl: './booking-update.component.html'
})
export class BookingUpdateComponent implements OnInit {
    booking: IBooking;
    isSaving: boolean;

    treatments: ITreatment[];

    customers: ICustomer[];

    vouchers: IVoucher[];
    startTime: string;
    finishTime: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private bookingService: BookingService,
        private treatmentService: TreatmentService,
        private customerService: CustomerService,
        private voucherService: VoucherService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ booking }) => {
            this.booking = booking;
            this.startTime = this.booking.startTime != null ? this.booking.startTime.format(DATE_TIME_FORMAT) : null;
            this.finishTime = this.booking.finishTime != null ? this.booking.finishTime.format(DATE_TIME_FORMAT) : null;
        });
        this.treatmentService.query().subscribe(
            (res: HttpResponse<ITreatment[]>) => {
                this.treatments = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.customerService.query().subscribe(
            (res: HttpResponse<ICustomer[]>) => {
                this.customers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.voucherService.query().subscribe(
            (res: HttpResponse<IVoucher[]>) => {
                this.vouchers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.booking.startTime = this.startTime != null ? moment(this.startTime, DATE_TIME_FORMAT) : null;
        this.booking.finishTime = this.finishTime != null ? moment(this.finishTime, DATE_TIME_FORMAT) : null;
        if (this.booking.id !== undefined) {
            this.subscribeToSaveResponse(this.bookingService.update(this.booking));
        } else {
            this.subscribeToSaveResponse(this.bookingService.create(this.booking));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBooking>>) {
        result.subscribe((res: HttpResponse<IBooking>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTreatmentById(index: number, item: ITreatment) {
        return item.id;
    }

    trackCustomerById(index: number, item: ICustomer) {
        return item.id;
    }

    trackVoucherById(index: number, item: IVoucher) {
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

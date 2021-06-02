import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IVoucher } from 'app/shared/model/voucher.model';
import { VoucherService } from './voucher.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer';
import { IServ } from 'app/shared/model/serv.model';
import { ServService } from 'app/entities/serv';
import { IBooking } from 'app/shared/model/booking.model';
import { BookingService } from 'app/entities/booking';
import { IPartner } from 'app/shared/model/partner.model';
import { PartnerService } from 'app/entities/partner';

@Component({
    selector: 'jhi-voucher-update',
    templateUrl: './voucher-update.component.html'
})
export class VoucherUpdateComponent implements OnInit {
    voucher: IVoucher;
    isSaving: boolean;

    customers: ICustomer[];

    servs: IServ[];

    bookings: IBooking[];

    partners: IPartner[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private voucherService: VoucherService,
        private customerService: CustomerService,
        private servService: ServService,
        private bookingService: BookingService,
        private partnerService: PartnerService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ voucher }) => {
            this.voucher = voucher;
        });
        this.customerService.query().subscribe(
            (res: HttpResponse<ICustomer[]>) => {
                this.customers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.servService.query().subscribe(
            (res: HttpResponse<IServ[]>) => {
                this.servs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.bookingService.query().subscribe(
            (res: HttpResponse<IBooking[]>) => {
                this.bookings = res.body;
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

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCustomerById(index: number, item: ICustomer) {
        return item.id;
    }

    trackServById(index: number, item: IServ) {
        return item.id;
    }

    trackBookingById(index: number, item: IBooking) {
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

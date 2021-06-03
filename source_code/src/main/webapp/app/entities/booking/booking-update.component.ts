import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBooking } from 'app/shared/model/booking.model';
import { BookingService } from './booking.service';

@Component({
    selector: 'jhi-booking-update',
    templateUrl: './booking-update.component.html'
})
export class BookingUpdateComponent implements OnInit {
    booking: IBooking;
    isSaving: boolean;
    startTime: string;
    finishTime: string;
    confirmTime: string;

    constructor(private bookingService: BookingService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ booking }) => {
            this.booking = booking;
            this.startTime = this.booking.startTime != null ? this.booking.startTime.format(DATE_TIME_FORMAT) : null;
            this.finishTime = this.booking.finishTime != null ? this.booking.finishTime.format(DATE_TIME_FORMAT) : null;
            this.confirmTime = this.booking.confirmTime != null ? this.booking.confirmTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.booking.startTime = this.startTime != null ? moment(this.startTime, DATE_TIME_FORMAT) : null;
        this.booking.finishTime = this.finishTime != null ? moment(this.finishTime, DATE_TIME_FORMAT) : null;
        this.booking.confirmTime = this.confirmTime != null ? moment(this.confirmTime, DATE_TIME_FORMAT) : null;
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
}

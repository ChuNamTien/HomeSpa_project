import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBookingActivity } from 'app/shared/model/booking-activity.model';
import { BookingActivityService } from './booking-activity.service';

@Component({
    selector: 'jhi-booking-activity-update',
    templateUrl: './booking-activity-update.component.html'
})
export class BookingActivityUpdateComponent implements OnInit {
    bookingActivity: IBookingActivity;
    isSaving: boolean;
    startDate: string;
    finishDate: string;

    constructor(private bookingActivityService: BookingActivityService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ bookingActivity }) => {
            this.bookingActivity = bookingActivity;
            this.startDate = this.bookingActivity.startDate != null ? this.bookingActivity.startDate.format(DATE_TIME_FORMAT) : null;
            this.finishDate = this.bookingActivity.finishDate != null ? this.bookingActivity.finishDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.bookingActivity.startDate = this.startDate != null ? moment(this.startDate, DATE_TIME_FORMAT) : null;
        this.bookingActivity.finishDate = this.finishDate != null ? moment(this.finishDate, DATE_TIME_FORMAT) : null;
        if (this.bookingActivity.id !== undefined) {
            this.subscribeToSaveResponse(this.bookingActivityService.update(this.bookingActivity));
        } else {
            this.subscribeToSaveResponse(this.bookingActivityService.create(this.bookingActivity));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBookingActivity>>) {
        result.subscribe((res: HttpResponse<IBookingActivity>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

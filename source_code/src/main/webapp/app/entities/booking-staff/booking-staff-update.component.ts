import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IBookingStaff } from 'app/shared/model/booking-staff.model';
import { BookingStaffService } from './booking-staff.service';

@Component({
    selector: 'jhi-booking-staff-update',
    templateUrl: './booking-staff-update.component.html'
})
export class BookingStaffUpdateComponent implements OnInit {
    bookingStaff: IBookingStaff;
    isSaving: boolean;

    constructor(private bookingStaffService: BookingStaffService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ bookingStaff }) => {
            this.bookingStaff = bookingStaff;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.bookingStaff.id !== undefined) {
            this.subscribeToSaveResponse(this.bookingStaffService.update(this.bookingStaff));
        } else {
            this.subscribeToSaveResponse(this.bookingStaffService.create(this.bookingStaff));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBookingStaff>>) {
        result.subscribe((res: HttpResponse<IBookingStaff>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

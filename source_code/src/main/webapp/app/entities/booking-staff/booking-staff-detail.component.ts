import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBookingStaff } from 'app/shared/model/booking-staff.model';

@Component({
    selector: 'jhi-booking-staff-detail',
    templateUrl: './booking-staff-detail.component.html'
})
export class BookingStaffDetailComponent implements OnInit {
    bookingStaff: IBookingStaff;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bookingStaff }) => {
            this.bookingStaff = bookingStaff;
        });
    }

    previousState() {
        window.history.back();
    }
}

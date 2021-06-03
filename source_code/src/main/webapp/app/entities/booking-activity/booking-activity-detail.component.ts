import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBookingActivity } from 'app/shared/model/booking-activity.model';

@Component({
    selector: 'jhi-booking-activity-detail',
    templateUrl: './booking-activity-detail.component.html'
})
export class BookingActivityDetailComponent implements OnInit {
    bookingActivity: IBookingActivity;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bookingActivity }) => {
            this.bookingActivity = bookingActivity;
        });
    }

    previousState() {
        window.history.back();
    }
}

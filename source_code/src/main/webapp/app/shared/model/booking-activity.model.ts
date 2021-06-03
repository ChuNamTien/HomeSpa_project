import { Moment } from 'moment';

export interface IBookingActivity {
    id?: number;
    bookingId?: number;
    staffId?: number;
    treatmentId?: number;
    startDate?: Moment;
    finishDate?: Moment;
    status?: number;
}

export class BookingActivity implements IBookingActivity {
    constructor(
        public id?: number,
        public bookingId?: number,
        public staffId?: number,
        public treatmentId?: number,
        public startDate?: Moment,
        public finishDate?: Moment,
        public status?: number
    ) {}
}

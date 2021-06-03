import { Moment } from 'moment';

export interface IBooking {
    id?: number;
    customerId?: number;
    partnerId?: number;
    startTime?: Moment;
    finishTime?: Moment;
    isFinished?: boolean;
    isConfirmed?: boolean;
    duration?: number;
    paymentMethod?: string;
    confirmTime?: Moment;
}

export class Booking implements IBooking {
    constructor(
        public id?: number,
        public customerId?: number,
        public partnerId?: number,
        public startTime?: Moment,
        public finishTime?: Moment,
        public isFinished?: boolean,
        public isConfirmed?: boolean,
        public duration?: number,
        public paymentMethod?: string,
        public confirmTime?: Moment
    ) {
        this.isFinished = this.isFinished || false;
        this.isConfirmed = this.isConfirmed || false;
    }
}

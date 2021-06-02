import { Moment } from 'moment';

export interface IBooking {
    id?: number;
    customerId?: number;
    partnerId?: number;
    startTime?: Moment;
    finishTime?: string;
    isFinished?: boolean;
    isConfirmed?: boolean;
    paymentMethod?: string;
    confirmTime?: string;
    createdBy?: string;
    createdDate?: Moment;
    lastModifiedBy?: Moment;
    lastModifiedDate?: Moment;
}

export class Booking implements IBooking {
    constructor(
        public id?: number,
        public customerId?: number,
        public partnerId?: number,
        public startTime?: Moment,
        public finishTime?: string,
        public isFinished?: boolean,
        public isConfirmed?: boolean,
        public paymentMethod?: string,
        public confirmTime?: string,
        public createdBy?: string,
        public createdDate?: Moment,
        public lastModifiedBy?: Moment,
        public lastModifiedDate?: Moment
    ) {
        this.isFinished = this.isFinished || false;
        this.isConfirmed = this.isConfirmed || false;
    }
}

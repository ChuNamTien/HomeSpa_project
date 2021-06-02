import { Moment } from 'moment';
import { ITreatment } from 'app/shared/model//treatment.model';
import { ICustomer } from 'app/shared/model//customer.model';
import { IVoucher } from 'app/shared/model//voucher.model';

export interface IBooking {
    id?: number;
    startTime?: Moment;
    finishTime?: Moment;
    isFinished?: boolean;
    isConfirmed?: boolean;
    paymentMethod?: string;
    treatments?: ITreatment[];
    customer?: ICustomer;
    vouchers?: IVoucher[];
}

export class Booking implements IBooking {
    constructor(
        public id?: number,
        public startTime?: Moment,
        public finishTime?: Moment,
        public isFinished?: boolean,
        public isConfirmed?: boolean,
        public paymentMethod?: string,
        public treatments?: ITreatment[],
        public customer?: ICustomer,
        public vouchers?: IVoucher[]
    ) {
        this.isFinished = this.isFinished || false;
        this.isConfirmed = this.isConfirmed || false;
    }
}

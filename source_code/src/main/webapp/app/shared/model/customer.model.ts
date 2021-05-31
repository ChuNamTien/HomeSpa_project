import { Moment } from 'moment';
import { IBooking } from 'app/shared/model//booking.model';
import { IVoucher } from 'app/shared/model//voucher.model';

export interface ICustomer {
    id?: number;
    dob?: Moment;
    phone?: string;
    address?: string;
    city?: string;
    zipcode?: string;
    status?: boolean;
    bookings?: IBooking[];
    vouchers?: IVoucher[];
}

export class Customer implements ICustomer {
    constructor(
        public id?: number,
        public dob?: Moment,
        public phone?: string,
        public address?: string,
        public city?: string,
        public zipcode?: string,
        public status?: boolean,
        public bookings?: IBooking[],
        public vouchers?: IVoucher[]
    ) {
        this.status = this.status || false;
    }
}

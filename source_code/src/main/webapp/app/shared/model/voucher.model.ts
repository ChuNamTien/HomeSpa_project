import { ICustomer } from 'app/shared/model//customer.model';
import { IServ } from 'app/shared/model//serv.model';
import { IBooking } from 'app/shared/model//booking.model';
import { IPartner } from 'app/shared/model//partner.model';

export interface IVoucher {
    id?: number;
    name?: string;
    type?: string;
    discription?: string;
    discount?: number;
    customers?: ICustomer[];
    servs?: IServ[];
    bookings?: IBooking[];
    partner?: IPartner;
}

export class Voucher implements IVoucher {
    constructor(
        public id?: number,
        public name?: string,
        public type?: string,
        public discription?: string,
        public discount?: number,
        public customers?: ICustomer[],
        public servs?: IServ[],
        public bookings?: IBooking[],
        public partner?: IPartner
    ) {}
}

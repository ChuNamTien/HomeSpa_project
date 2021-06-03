import { Moment } from 'moment';

export interface IVoucher {
    id?: number;
    partnerId?: number;
    name?: string;
    type?: string;
    discription?: string;
    discount?: number;
    priceAbove?: number;
    maxDiscount?: number;
    status?: string;
    createdBy?: string;
    createdDate?: Moment;
    lastModifiedBy?: string;
    lastModifiedDate?: Moment;
}

export class Voucher implements IVoucher {
    constructor(
        public id?: number,
        public partnerId?: number,
        public name?: string,
        public type?: string,
        public discription?: string,
        public discount?: number,
        public priceAbove?: number,
        public maxDiscount?: number,
        public status?: string,
        public createdBy?: string,
        public createdDate?: Moment,
        public lastModifiedBy?: string,
        public lastModifiedDate?: Moment
    ) {}
}

import { Moment } from 'moment';

export interface ICustomer {
    id?: number;
    userId?: number;
    dob?: Moment;
    phone?: string;
    address?: string;
    city?: string;
    zipcode?: string;
    status?: boolean;
}

export class Customer implements ICustomer {
    constructor(
        public id?: number,
        public userId?: number,
        public dob?: Moment,
        public phone?: string,
        public address?: string,
        public city?: string,
        public zipcode?: string,
        public status?: boolean
    ) {
        this.status = this.status || false;
    }
}

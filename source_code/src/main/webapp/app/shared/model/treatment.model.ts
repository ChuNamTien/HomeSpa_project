import { Moment } from 'moment';

export interface ITreatment {
    id?: number;
    serviceId?: number;
    name?: string;
    status?: string;
    duration?: number;
    price?: number;
    discount?: number;
    description?: string;
    createdBy?: string;
    createdDate?: Moment;
    lastModifiedBy?: Moment;
    lastModifiedDate?: Moment;
}

export class Treatment implements ITreatment {
    constructor(
        public id?: number,
        public serviceId?: number,
        public name?: string,
        public status?: string,
        public duration?: number,
        public price?: number,
        public discount?: number,
        public description?: string,
        public createdBy?: string,
        public createdDate?: Moment,
        public lastModifiedBy?: Moment,
        public lastModifiedDate?: Moment
    ) {}
}

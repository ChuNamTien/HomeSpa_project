import { Moment } from 'moment';

export interface IServ {
    id?: number;
    categoryId?: number;
    name?: string;
    customerType?: string;
    description?: string;
    createdBy?: string;
    createdDate?: Moment;
    lastModifiedBy?: Moment;
    lastModifiedDate?: Moment;
}

export class Serv implements IServ {
    constructor(
        public id?: number,
        public categoryId?: number,
        public name?: string,
        public customerType?: string,
        public description?: string,
        public createdBy?: string,
        public createdDate?: Moment,
        public lastModifiedBy?: Moment,
        public lastModifiedDate?: Moment
    ) {}
}

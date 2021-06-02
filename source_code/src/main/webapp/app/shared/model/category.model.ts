import { Moment } from 'moment';

export interface ICategory {
    id?: number;
    name?: string;
    imgUrl?: string;
    status?: string;
    createdBy?: string;
    createdDate?: Moment;
    lastModifiedBy?: Moment;
    lastModifiedDate?: Moment;
}

export class Category implements ICategory {
    constructor(
        public id?: number,
        public name?: string,
        public imgUrl?: string,
        public status?: string,
        public createdBy?: string,
        public createdDate?: Moment,
        public lastModifiedBy?: Moment,
        public lastModifiedDate?: Moment
    ) {}
}

import { Moment } from 'moment';

export interface ITimeline {
    id?: number;
    timeStart?: Moment;
    content?: string;
    createdBy?: number;
    createdDate?: Moment;
}

export class Timeline implements ITimeline {
    constructor(
        public id?: number,
        public timeStart?: Moment,
        public content?: string,
        public createdBy?: number,
        public createdDate?: Moment
    ) {}
}

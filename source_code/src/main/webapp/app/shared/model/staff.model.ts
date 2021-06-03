export interface IStaff {
    id?: number;
    userId?: number;
    partnerId?: number;
    type?: string;
    status?: string;
    startTime?: number;
    endTime?: number;
}

export class Staff implements IStaff {
    constructor(
        public id?: number,
        public userId?: number,
        public partnerId?: number,
        public type?: string,
        public status?: string,
        public startTime?: number,
        public endTime?: number
    ) {}
}

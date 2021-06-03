export interface IRating {
    id?: number;
    bookingId?: number;
    star?: number;
    comment?: string;
}

export class Rating implements IRating {
    constructor(public id?: number, public bookingId?: number, public star?: number, public comment?: string) {}
}

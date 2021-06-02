export interface IBookingStaff {
    id?: number;
    bookingId?: number;
    treatmentId?: number;
    status?: number;
}

export class BookingStaff implements IBookingStaff {
    constructor(public id?: number, public bookingId?: number, public treatmentId?: number, public status?: number) {}
}

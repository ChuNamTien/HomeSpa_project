import { IServ } from 'app/shared/model//serv.model';
import { IStaff } from 'app/shared/model//staff.model';
import { IBooking } from 'app/shared/model//booking.model';

export interface ITreatment {
    id?: number;
    name?: string;
    status?: string;
    duration?: number;
    price?: number;
    discount?: number;
    description?: string;
    serv?: IServ;
    staff?: IStaff[];
    bookings?: IBooking[];
}

export class Treatment implements ITreatment {
    constructor(
        public id?: number,
        public name?: string,
        public status?: string,
        public duration?: number,
        public price?: number,
        public discount?: number,
        public description?: string,
        public serv?: IServ,
        public staff?: IStaff[],
        public bookings?: IBooking[]
    ) {}
}

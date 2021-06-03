export interface ITransaction {
    id?: number;
    bookingId?: number;
    code?: string;
    amount?: number;
    ipAddress?: string;
    message?: string;
    status?: string;
}

export class Transaction implements ITransaction {
    constructor(
        public id?: number,
        public bookingId?: number,
        public code?: string,
        public amount?: number,
        public ipAddress?: string,
        public message?: string,
        public status?: string
    ) {}
}

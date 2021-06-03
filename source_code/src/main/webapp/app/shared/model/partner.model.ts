export interface IPartner {
    id?: number;
    userId?: number;
    name?: string;
    partnerType?: string;
    customerType?: string;
    description?: string;
    city?: string;
    address?: string;
    phone?: string;
    longCoord?: number;
    latCoord?: number;
    openTime?: number;
    closeTime?: number;
    isWeekendOpen?: boolean;
    status?: boolean;
    confirmAfter?: number;
    bussinessLicenseUrl?: string;
}

export class Partner implements IPartner {
    constructor(
        public id?: number,
        public userId?: number,
        public name?: string,
        public partnerType?: string,
        public customerType?: string,
        public description?: string,
        public city?: string,
        public address?: string,
        public phone?: string,
        public longCoord?: number,
        public latCoord?: number,
        public openTime?: number,
        public closeTime?: number,
        public isWeekendOpen?: boolean,
        public status?: boolean,
        public confirmAfter?: number,
        public bussinessLicenseUrl?: string
    ) {
        this.isWeekendOpen = this.isWeekendOpen || false;
        this.status = this.status || false;
    }
}

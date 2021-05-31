import { Moment } from 'moment';
import { IPartnerImg } from 'app/shared/model//partner-img.model';
import { IStaff } from 'app/shared/model//staff.model';
import { IServ } from 'app/shared/model//serv.model';
import { IVoucher } from 'app/shared/model//voucher.model';
import { ICategory } from 'app/shared/model//category.model';

export interface IPartner {
    id?: number;
    name?: string;
    partnerType?: string;
    customerType?: string;
    description?: string;
    city?: string;
    address?: string;
    phone?: string;
    longCoord?: number;
    latCoord?: number;
    openTime?: Moment;
    closeTime?: Moment;
    isWeekendOpen?: boolean;
    status?: boolean;
    timeConfirm?: string;
    bussinessLicenseUrl?: string;
    partnerImgs?: IPartnerImg[];
    staff?: IStaff[];
    servs?: IServ[];
    vouchers?: IVoucher[];
    categories?: ICategory[];
}

export class Partner implements IPartner {
    constructor(
        public id?: number,
        public name?: string,
        public partnerType?: string,
        public customerType?: string,
        public description?: string,
        public city?: string,
        public address?: string,
        public phone?: string,
        public longCoord?: number,
        public latCoord?: number,
        public openTime?: Moment,
        public closeTime?: Moment,
        public isWeekendOpen?: boolean,
        public status?: boolean,
        public timeConfirm?: string,
        public bussinessLicenseUrl?: string,
        public partnerImgs?: IPartnerImg[],
        public staff?: IStaff[],
        public servs?: IServ[],
        public vouchers?: IVoucher[],
        public categories?: ICategory[]
    ) {
        this.isWeekendOpen = this.isWeekendOpen || false;
        this.status = this.status || false;
    }
}

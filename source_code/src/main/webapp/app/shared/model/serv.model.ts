import { IServImg } from 'app/shared/model//serv-img.model';
import { ITreatment } from 'app/shared/model//treatment.model';
import { IPartner } from 'app/shared/model//partner.model';
import { IVoucher } from 'app/shared/model//voucher.model';

export interface IServ {
    id?: number;
    name?: string;
    customerType?: string;
    description?: string;
    servImgs?: IServImg[];
    treatments?: ITreatment[];
    partner?: IPartner;
    vouchers?: IVoucher[];
}

export class Serv implements IServ {
    constructor(
        public id?: number,
        public name?: string,
        public customerType?: string,
        public description?: string,
        public servImgs?: IServImg[],
        public treatments?: ITreatment[],
        public partner?: IPartner,
        public vouchers?: IVoucher[]
    ) {}
}

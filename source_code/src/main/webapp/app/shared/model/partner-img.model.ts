import { IPartner } from 'app/shared/model//partner.model';

export interface IPartnerImg {
    id?: number;
    imgUrl?: string;
    isHiddent?: boolean;
    index?: number;
    partner?: IPartner;
}

export class PartnerImg implements IPartnerImg {
    constructor(public id?: number, public imgUrl?: string, public isHiddent?: boolean, public index?: number, public partner?: IPartner) {
        this.isHiddent = this.isHiddent || false;
    }
}

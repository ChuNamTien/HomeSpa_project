export interface IPartnerImg {
    id?: number;
    partnerId?: number;
    imgUrl?: string;
    isHiddent?: boolean;
    index?: number;
}

export class PartnerImg implements IPartnerImg {
    constructor(public id?: number, public partnerId?: number, public imgUrl?: string, public isHiddent?: boolean, public index?: number) {
        this.isHiddent = this.isHiddent || false;
    }
}

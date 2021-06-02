export interface IServImg {
    id?: number;
    serviceId?: number;
    imgUrl?: string;
    status?: string;
    index?: number;
}

export class ServImg implements IServImg {
    constructor(public id?: number, public serviceId?: number, public imgUrl?: string, public status?: string, public index?: number) {}
}

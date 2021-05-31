import { IServ } from 'app/shared/model//serv.model';

export interface IServImg {
    id?: number;
    imgUrl?: string;
    status?: string;
    index?: number;
    serv?: IServ;
}

export class ServImg implements IServImg {
    constructor(public id?: number, public imgUrl?: string, public status?: string, public index?: number, public serv?: IServ) {}
}

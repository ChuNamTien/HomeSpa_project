import { IPartner } from 'app/shared/model//partner.model';
import { IStaff } from 'app/shared/model//staff.model';

export interface ICategory {
    id?: number;
    name?: string;
    imgUrl?: string;
    status?: string;
    partners?: IPartner[];
    staff?: IStaff[];
}

export class Category implements ICategory {
    constructor(
        public id?: number,
        public name?: string,
        public imgUrl?: string,
        public status?: string,
        public partners?: IPartner[],
        public staff?: IStaff[]
    ) {}
}

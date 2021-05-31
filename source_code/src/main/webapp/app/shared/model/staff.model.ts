import { ICategory } from 'app/shared/model//category.model';
import { ITreatment } from 'app/shared/model//treatment.model';
import { IPartner } from 'app/shared/model//partner.model';

export interface IStaff {
    id?: number;
    type?: string;
    status?: string;
    startTime?: boolean;
    endTime?: boolean;
    categories?: ICategory[];
    treatments?: ITreatment[];
    partner?: IPartner;
}

export class Staff implements IStaff {
    constructor(
        public id?: number,
        public type?: string,
        public status?: string,
        public startTime?: boolean,
        public endTime?: boolean,
        public categories?: ICategory[],
        public treatments?: ITreatment[],
        public partner?: IPartner
    ) {
        this.startTime = this.startTime || false;
        this.endTime = this.endTime || false;
    }
}

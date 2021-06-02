import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPartner } from 'app/shared/model/partner.model';

type EntityResponseType = HttpResponse<IPartner>;
type EntityArrayResponseType = HttpResponse<IPartner[]>;

@Injectable({ providedIn: 'root' })
export class PartnerService {
    public resourceUrl = SERVER_API_URL + 'api/partners';

    constructor(private http: HttpClient) {}

    create(partner: IPartner): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(partner);
        return this.http
            .post<IPartner>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(partner: IPartner): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(partner);
        return this.http
            .put<IPartner>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPartner>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPartner[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(partner: IPartner): IPartner {
        const copy: IPartner = Object.assign({}, partner, {
            openTime: partner.openTime != null && partner.openTime.isValid() ? partner.openTime.toJSON() : null,
            closeTime: partner.closeTime != null && partner.closeTime.isValid() ? partner.closeTime.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.openTime = res.body.openTime != null ? moment(res.body.openTime) : null;
        res.body.closeTime = res.body.closeTime != null ? moment(res.body.closeTime) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((partner: IPartner) => {
            partner.openTime = partner.openTime != null ? moment(partner.openTime) : null;
            partner.closeTime = partner.closeTime != null ? moment(partner.closeTime) : null;
        });
        return res;
    }
}

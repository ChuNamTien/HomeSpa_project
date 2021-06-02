import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IServ } from 'app/shared/model/serv.model';

type EntityResponseType = HttpResponse<IServ>;
type EntityArrayResponseType = HttpResponse<IServ[]>;

@Injectable({ providedIn: 'root' })
export class ServService {
    public resourceUrl = SERVER_API_URL + 'api/servs';

    constructor(private http: HttpClient) {}

    create(serv: IServ): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(serv);
        return this.http
            .post<IServ>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(serv: IServ): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(serv);
        return this.http
            .put<IServ>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IServ>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IServ[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(serv: IServ): IServ {
        const copy: IServ = Object.assign({}, serv, {
            createdDate: serv.createdDate != null && serv.createdDate.isValid() ? serv.createdDate.toJSON() : null,
            lastModifiedBy: serv.lastModifiedBy != null && serv.lastModifiedBy.isValid() ? serv.lastModifiedBy.toJSON() : null,
            lastModifiedDate: serv.lastModifiedDate != null && serv.lastModifiedDate.isValid() ? serv.lastModifiedDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
        res.body.lastModifiedBy = res.body.lastModifiedBy != null ? moment(res.body.lastModifiedBy) : null;
        res.body.lastModifiedDate = res.body.lastModifiedDate != null ? moment(res.body.lastModifiedDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((serv: IServ) => {
            serv.createdDate = serv.createdDate != null ? moment(serv.createdDate) : null;
            serv.lastModifiedBy = serv.lastModifiedBy != null ? moment(serv.lastModifiedBy) : null;
            serv.lastModifiedDate = serv.lastModifiedDate != null ? moment(serv.lastModifiedDate) : null;
        });
        return res;
    }
}

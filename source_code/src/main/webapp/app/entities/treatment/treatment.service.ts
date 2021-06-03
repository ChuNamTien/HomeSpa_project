import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITreatment } from 'app/shared/model/treatment.model';

type EntityResponseType = HttpResponse<ITreatment>;
type EntityArrayResponseType = HttpResponse<ITreatment[]>;

@Injectable({ providedIn: 'root' })
export class TreatmentService {
    public resourceUrl = SERVER_API_URL + 'api/treatments';

    constructor(private http: HttpClient) {}

    create(treatment: ITreatment): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(treatment);
        return this.http
            .post<ITreatment>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(treatment: ITreatment): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(treatment);
        return this.http
            .put<ITreatment>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITreatment>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITreatment[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(treatment: ITreatment): ITreatment {
        const copy: ITreatment = Object.assign({}, treatment, {
            createdDate: treatment.createdDate != null && treatment.createdDate.isValid() ? treatment.createdDate.toJSON() : null,
            lastModifiedBy:
                treatment.lastModifiedBy != null && treatment.lastModifiedBy.isValid() ? treatment.lastModifiedBy.toJSON() : null,
            lastModifiedDate:
                treatment.lastModifiedDate != null && treatment.lastModifiedDate.isValid() ? treatment.lastModifiedDate.toJSON() : null
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
        res.body.forEach((treatment: ITreatment) => {
            treatment.createdDate = treatment.createdDate != null ? moment(treatment.createdDate) : null;
            treatment.lastModifiedBy = treatment.lastModifiedBy != null ? moment(treatment.lastModifiedBy) : null;
            treatment.lastModifiedDate = treatment.lastModifiedDate != null ? moment(treatment.lastModifiedDate) : null;
        });
        return res;
    }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICategory } from 'app/shared/model/category.model';

type EntityResponseType = HttpResponse<ICategory>;
type EntityArrayResponseType = HttpResponse<ICategory[]>;

@Injectable({ providedIn: 'root' })
export class CategoryService {
    public resourceUrl = SERVER_API_URL + 'api/categories';

    constructor(private http: HttpClient) {}

    create(category: ICategory): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(category);
        return this.http
            .post<ICategory>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(category: ICategory): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(category);
        return this.http
            .put<ICategory>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICategory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICategory[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(category: ICategory): ICategory {
        const copy: ICategory = Object.assign({}, category, {
            createdDate: category.createdDate != null && category.createdDate.isValid() ? category.createdDate.toJSON() : null,
            lastModifiedBy: category.lastModifiedBy != null && category.lastModifiedBy.isValid() ? category.lastModifiedBy.toJSON() : null,
            lastModifiedDate:
                category.lastModifiedDate != null && category.lastModifiedDate.isValid() ? category.lastModifiedDate.toJSON() : null
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
        res.body.forEach((category: ICategory) => {
            category.createdDate = category.createdDate != null ? moment(category.createdDate) : null;
            category.lastModifiedBy = category.lastModifiedBy != null ? moment(category.lastModifiedBy) : null;
            category.lastModifiedDate = category.lastModifiedDate != null ? moment(category.lastModifiedDate) : null;
        });
        return res;
    }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVoucher } from 'app/shared/model/voucher.model';

type EntityResponseType = HttpResponse<IVoucher>;
type EntityArrayResponseType = HttpResponse<IVoucher[]>;

@Injectable({ providedIn: 'root' })
export class VoucherService {
    public resourceUrl = SERVER_API_URL + 'api/vouchers';

    constructor(private http: HttpClient) {}

    create(voucher: IVoucher): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(voucher);
        return this.http
            .post<IVoucher>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(voucher: IVoucher): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(voucher);
        return this.http
            .put<IVoucher>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IVoucher>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IVoucher[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(voucher: IVoucher): IVoucher {
        const copy: IVoucher = Object.assign({}, voucher, {
            createdDate: voucher.createdDate != null && voucher.createdDate.isValid() ? voucher.createdDate.toJSON() : null,
            lastModifiedDate:
                voucher.lastModifiedDate != null && voucher.lastModifiedDate.isValid() ? voucher.lastModifiedDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
        res.body.lastModifiedDate = res.body.lastModifiedDate != null ? moment(res.body.lastModifiedDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((voucher: IVoucher) => {
            voucher.createdDate = voucher.createdDate != null ? moment(voucher.createdDate) : null;
            voucher.lastModifiedDate = voucher.lastModifiedDate != null ? moment(voucher.lastModifiedDate) : null;
        });
        return res;
    }
}

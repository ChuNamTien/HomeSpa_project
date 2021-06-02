import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBooking } from 'app/shared/model/booking.model';

type EntityResponseType = HttpResponse<IBooking>;
type EntityArrayResponseType = HttpResponse<IBooking[]>;

@Injectable({ providedIn: 'root' })
export class BookingService {
    public resourceUrl = SERVER_API_URL + 'api/bookings';

    constructor(private http: HttpClient) {}

    create(booking: IBooking): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(booking);
        return this.http
            .post<IBooking>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(booking: IBooking): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(booking);
        return this.http
            .put<IBooking>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IBooking>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IBooking[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(booking: IBooking): IBooking {
        const copy: IBooking = Object.assign({}, booking, {
            startTime: booking.startTime != null && booking.startTime.isValid() ? booking.startTime.toJSON() : null,
            createdDate: booking.createdDate != null && booking.createdDate.isValid() ? booking.createdDate.toJSON() : null,
            lastModifiedBy: booking.lastModifiedBy != null && booking.lastModifiedBy.isValid() ? booking.lastModifiedBy.toJSON() : null,
            lastModifiedDate:
                booking.lastModifiedDate != null && booking.lastModifiedDate.isValid() ? booking.lastModifiedDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.startTime = res.body.startTime != null ? moment(res.body.startTime) : null;
        res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
        res.body.lastModifiedBy = res.body.lastModifiedBy != null ? moment(res.body.lastModifiedBy) : null;
        res.body.lastModifiedDate = res.body.lastModifiedDate != null ? moment(res.body.lastModifiedDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((booking: IBooking) => {
            booking.startTime = booking.startTime != null ? moment(booking.startTime) : null;
            booking.createdDate = booking.createdDate != null ? moment(booking.createdDate) : null;
            booking.lastModifiedBy = booking.lastModifiedBy != null ? moment(booking.lastModifiedBy) : null;
            booking.lastModifiedDate = booking.lastModifiedDate != null ? moment(booking.lastModifiedDate) : null;
        });
        return res;
    }
}

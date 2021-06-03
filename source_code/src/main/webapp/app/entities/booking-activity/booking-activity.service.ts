import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBookingActivity } from 'app/shared/model/booking-activity.model';

type EntityResponseType = HttpResponse<IBookingActivity>;
type EntityArrayResponseType = HttpResponse<IBookingActivity[]>;

@Injectable({ providedIn: 'root' })
export class BookingActivityService {
    public resourceUrl = SERVER_API_URL + 'api/booking-activities';

    constructor(private http: HttpClient) {}

    create(bookingActivity: IBookingActivity): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(bookingActivity);
        return this.http
            .post<IBookingActivity>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(bookingActivity: IBookingActivity): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(bookingActivity);
        return this.http
            .put<IBookingActivity>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IBookingActivity>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IBookingActivity[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(bookingActivity: IBookingActivity): IBookingActivity {
        const copy: IBookingActivity = Object.assign({}, bookingActivity, {
            startDate: bookingActivity.startDate != null && bookingActivity.startDate.isValid() ? bookingActivity.startDate.toJSON() : null,
            finishDate:
                bookingActivity.finishDate != null && bookingActivity.finishDate.isValid() ? bookingActivity.finishDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.startDate = res.body.startDate != null ? moment(res.body.startDate) : null;
        res.body.finishDate = res.body.finishDate != null ? moment(res.body.finishDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((bookingActivity: IBookingActivity) => {
            bookingActivity.startDate = bookingActivity.startDate != null ? moment(bookingActivity.startDate) : null;
            bookingActivity.finishDate = bookingActivity.finishDate != null ? moment(bookingActivity.finishDate) : null;
        });
        return res;
    }
}

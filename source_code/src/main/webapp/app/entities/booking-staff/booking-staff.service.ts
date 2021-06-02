import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBookingStaff } from 'app/shared/model/booking-staff.model';

type EntityResponseType = HttpResponse<IBookingStaff>;
type EntityArrayResponseType = HttpResponse<IBookingStaff[]>;

@Injectable({ providedIn: 'root' })
export class BookingStaffService {
    public resourceUrl = SERVER_API_URL + 'api/booking-staffs';

    constructor(private http: HttpClient) {}

    create(bookingStaff: IBookingStaff): Observable<EntityResponseType> {
        return this.http.post<IBookingStaff>(this.resourceUrl, bookingStaff, { observe: 'response' });
    }

    update(bookingStaff: IBookingStaff): Observable<EntityResponseType> {
        return this.http.put<IBookingStaff>(this.resourceUrl, bookingStaff, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IBookingStaff>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBookingStaff[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

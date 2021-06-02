import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

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
        return this.http.post<IServ>(this.resourceUrl, serv, { observe: 'response' });
    }

    update(serv: IServ): Observable<EntityResponseType> {
        return this.http.put<IServ>(this.resourceUrl, serv, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IServ>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IServ[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

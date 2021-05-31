import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IServImg } from 'app/shared/model/serv-img.model';

type EntityResponseType = HttpResponse<IServImg>;
type EntityArrayResponseType = HttpResponse<IServImg[]>;

@Injectable({ providedIn: 'root' })
export class ServImgService {
    public resourceUrl = SERVER_API_URL + 'api/serv-imgs';

    constructor(private http: HttpClient) {}

    create(servImg: IServImg): Observable<EntityResponseType> {
        return this.http.post<IServImg>(this.resourceUrl, servImg, { observe: 'response' });
    }

    update(servImg: IServImg): Observable<EntityResponseType> {
        return this.http.put<IServImg>(this.resourceUrl, servImg, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IServImg>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IServImg[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IServ } from 'app/shared/model/serv.model';
import { ServService } from './serv.service';

@Component({
    selector: 'jhi-serv-update',
    templateUrl: './serv-update.component.html'
})
export class ServUpdateComponent implements OnInit {
    serv: IServ;
    isSaving: boolean;
    createdDate: string;
    lastModifiedBy: string;
    lastModifiedDate: string;

    constructor(private servService: ServService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ serv }) => {
            this.serv = serv;
            this.createdDate = this.serv.createdDate != null ? this.serv.createdDate.format(DATE_TIME_FORMAT) : null;
            this.lastModifiedBy = this.serv.lastModifiedBy != null ? this.serv.lastModifiedBy.format(DATE_TIME_FORMAT) : null;
            this.lastModifiedDate = this.serv.lastModifiedDate != null ? this.serv.lastModifiedDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.serv.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
        this.serv.lastModifiedBy = this.lastModifiedBy != null ? moment(this.lastModifiedBy, DATE_TIME_FORMAT) : null;
        this.serv.lastModifiedDate = this.lastModifiedDate != null ? moment(this.lastModifiedDate, DATE_TIME_FORMAT) : null;
        if (this.serv.id !== undefined) {
            this.subscribeToSaveResponse(this.servService.update(this.serv));
        } else {
            this.subscribeToSaveResponse(this.servService.create(this.serv));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IServ>>) {
        result.subscribe((res: HttpResponse<IServ>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

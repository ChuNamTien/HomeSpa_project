import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IServImg } from 'app/shared/model/serv-img.model';
import { ServImgService } from './serv-img.service';
import { IServ } from 'app/shared/model/serv.model';
import { ServService } from 'app/entities/serv';

@Component({
    selector: 'jhi-serv-img-update',
    templateUrl: './serv-img-update.component.html'
})
export class ServImgUpdateComponent implements OnInit {
    servImg: IServImg;
    isSaving: boolean;

    servs: IServ[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private servImgService: ServImgService,
        private servService: ServService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ servImg }) => {
            this.servImg = servImg;
        });
        this.servService.query().subscribe(
            (res: HttpResponse<IServ[]>) => {
                this.servs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.servImg.id !== undefined) {
            this.subscribeToSaveResponse(this.servImgService.update(this.servImg));
        } else {
            this.subscribeToSaveResponse(this.servImgService.create(this.servImg));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IServImg>>) {
        result.subscribe((res: HttpResponse<IServImg>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackServById(index: number, item: IServ) {
        return item.id;
    }
}

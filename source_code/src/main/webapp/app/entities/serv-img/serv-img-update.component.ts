import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IServImg } from 'app/shared/model/serv-img.model';
import { ServImgService } from './serv-img.service';

@Component({
    selector: 'jhi-serv-img-update',
    templateUrl: './serv-img-update.component.html'
})
export class ServImgUpdateComponent implements OnInit {
    servImg: IServImg;
    isSaving: boolean;

    constructor(private servImgService: ServImgService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ servImg }) => {
            this.servImg = servImg;
        });
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
}

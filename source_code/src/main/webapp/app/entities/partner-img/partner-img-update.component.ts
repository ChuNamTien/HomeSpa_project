import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPartnerImg } from 'app/shared/model/partner-img.model';
import { PartnerImgService } from './partner-img.service';

@Component({
    selector: 'jhi-partner-img-update',
    templateUrl: './partner-img-update.component.html'
})
export class PartnerImgUpdateComponent implements OnInit {
    partnerImg: IPartnerImg;
    isSaving: boolean;

    constructor(private partnerImgService: PartnerImgService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ partnerImg }) => {
            this.partnerImg = partnerImg;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.partnerImg.id !== undefined) {
            this.subscribeToSaveResponse(this.partnerImgService.update(this.partnerImg));
        } else {
            this.subscribeToSaveResponse(this.partnerImgService.create(this.partnerImg));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPartnerImg>>) {
        result.subscribe((res: HttpResponse<IPartnerImg>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

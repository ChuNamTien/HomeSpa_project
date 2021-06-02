import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPartnerImg } from 'app/shared/model/partner-img.model';

@Component({
    selector: 'jhi-partner-img-detail',
    templateUrl: './partner-img-detail.component.html'
})
export class PartnerImgDetailComponent implements OnInit {
    partnerImg: IPartnerImg;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ partnerImg }) => {
            this.partnerImg = partnerImg;
        });
    }

    previousState() {
        window.history.back();
    }
}

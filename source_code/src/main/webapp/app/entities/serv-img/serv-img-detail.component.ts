import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IServImg } from 'app/shared/model/serv-img.model';

@Component({
    selector: 'jhi-serv-img-detail',
    templateUrl: './serv-img-detail.component.html'
})
export class ServImgDetailComponent implements OnInit {
    servImg: IServImg;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ servImg }) => {
            this.servImg = servImg;
        });
    }

    previousState() {
        window.history.back();
    }
}

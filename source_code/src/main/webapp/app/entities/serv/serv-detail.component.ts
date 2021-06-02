import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IServ } from 'app/shared/model/serv.model';

@Component({
    selector: 'jhi-serv-detail',
    templateUrl: './serv-detail.component.html'
})
export class ServDetailComponent implements OnInit {
    serv: IServ;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ serv }) => {
            this.serv = serv;
        });
    }

    previousState() {
        window.history.back();
    }
}

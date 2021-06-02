import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IServ } from 'app/shared/model/serv.model';
import { ServService } from './serv.service';
import { IPartner } from 'app/shared/model/partner.model';
import { PartnerService } from 'app/entities/partner';
import { IVoucher } from 'app/shared/model/voucher.model';
import { VoucherService } from 'app/entities/voucher';

@Component({
    selector: 'jhi-serv-update',
    templateUrl: './serv-update.component.html'
})
export class ServUpdateComponent implements OnInit {
    serv: IServ;
    isSaving: boolean;

    partners: IPartner[];

    vouchers: IVoucher[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private servService: ServService,
        private partnerService: PartnerService,
        private voucherService: VoucherService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ serv }) => {
            this.serv = serv;
        });
        this.partnerService.query().subscribe(
            (res: HttpResponse<IPartner[]>) => {
                this.partners = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.voucherService.query().subscribe(
            (res: HttpResponse<IVoucher[]>) => {
                this.vouchers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
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

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackPartnerById(index: number, item: IPartner) {
        return item.id;
    }

    trackVoucherById(index: number, item: IVoucher) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

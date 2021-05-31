import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPartnerImg } from 'app/shared/model/partner-img.model';
import { PartnerImgService } from './partner-img.service';

@Component({
    selector: 'jhi-partner-img-delete-dialog',
    templateUrl: './partner-img-delete-dialog.component.html'
})
export class PartnerImgDeleteDialogComponent {
    partnerImg: IPartnerImg;

    constructor(private partnerImgService: PartnerImgService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.partnerImgService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'partnerImgListModification',
                content: 'Deleted an partnerImg'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-partner-img-delete-popup',
    template: ''
})
export class PartnerImgDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ partnerImg }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PartnerImgDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.partnerImg = partnerImg;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}

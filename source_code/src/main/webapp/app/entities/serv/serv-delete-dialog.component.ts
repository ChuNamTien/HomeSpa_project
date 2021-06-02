import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IServ } from 'app/shared/model/serv.model';
import { ServService } from './serv.service';

@Component({
    selector: 'jhi-serv-delete-dialog',
    templateUrl: './serv-delete-dialog.component.html'
})
export class ServDeleteDialogComponent {
    serv: IServ;

    constructor(private servService: ServService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.servService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'servListModification',
                content: 'Deleted an serv'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-serv-delete-popup',
    template: ''
})
export class ServDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ serv }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ServDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.serv = serv;
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

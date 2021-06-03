import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBookingActivity } from 'app/shared/model/booking-activity.model';
import { BookingActivityService } from './booking-activity.service';

@Component({
    selector: 'jhi-booking-activity-delete-dialog',
    templateUrl: './booking-activity-delete-dialog.component.html'
})
export class BookingActivityDeleteDialogComponent {
    bookingActivity: IBookingActivity;

    constructor(
        private bookingActivityService: BookingActivityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.bookingActivityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'bookingActivityListModification',
                content: 'Deleted an bookingActivity'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-booking-activity-delete-popup',
    template: ''
})
export class BookingActivityDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bookingActivity }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BookingActivityDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.bookingActivity = bookingActivity;
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

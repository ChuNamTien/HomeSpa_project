import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBookingStaff } from 'app/shared/model/booking-staff.model';
import { BookingStaffService } from './booking-staff.service';

@Component({
    selector: 'jhi-booking-staff-delete-dialog',
    templateUrl: './booking-staff-delete-dialog.component.html'
})
export class BookingStaffDeleteDialogComponent {
    bookingStaff: IBookingStaff;

    constructor(
        private bookingStaffService: BookingStaffService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.bookingStaffService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'bookingStaffListModification',
                content: 'Deleted an bookingStaff'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-booking-staff-delete-popup',
    template: ''
})
export class BookingStaffDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bookingStaff }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BookingStaffDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.bookingStaff = bookingStaff;
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

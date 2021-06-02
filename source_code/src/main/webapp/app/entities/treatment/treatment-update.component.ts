import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITreatment } from 'app/shared/model/treatment.model';
import { TreatmentService } from './treatment.service';
import { IServ } from 'app/shared/model/serv.model';
import { ServService } from 'app/entities/serv';
import { IStaff } from 'app/shared/model/staff.model';
import { StaffService } from 'app/entities/staff';
import { IBooking } from 'app/shared/model/booking.model';
import { BookingService } from 'app/entities/booking';

@Component({
    selector: 'jhi-treatment-update',
    templateUrl: './treatment-update.component.html'
})
export class TreatmentUpdateComponent implements OnInit {
    treatment: ITreatment;
    isSaving: boolean;

    servs: IServ[];

    staff: IStaff[];

    bookings: IBooking[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private treatmentService: TreatmentService,
        private servService: ServService,
        private staffService: StaffService,
        private bookingService: BookingService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ treatment }) => {
            this.treatment = treatment;
        });
        this.servService.query().subscribe(
            (res: HttpResponse<IServ[]>) => {
                this.servs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.staffService.query().subscribe(
            (res: HttpResponse<IStaff[]>) => {
                this.staff = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.bookingService.query().subscribe(
            (res: HttpResponse<IBooking[]>) => {
                this.bookings = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.treatment.id !== undefined) {
            this.subscribeToSaveResponse(this.treatmentService.update(this.treatment));
        } else {
            this.subscribeToSaveResponse(this.treatmentService.create(this.treatment));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITreatment>>) {
        result.subscribe((res: HttpResponse<ITreatment>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackServById(index: number, item: IServ) {
        return item.id;
    }

    trackStaffById(index: number, item: IStaff) {
        return item.id;
    }

    trackBookingById(index: number, item: IBooking) {
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

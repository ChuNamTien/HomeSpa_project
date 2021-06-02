import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IStaff } from 'app/shared/model/staff.model';
import { StaffService } from './staff.service';

@Component({
    selector: 'jhi-staff-update',
    templateUrl: './staff-update.component.html'
})
export class StaffUpdateComponent implements OnInit {
    staff: IStaff;
    isSaving: boolean;

    constructor(private staffService: StaffService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ staff }) => {
            this.staff = staff;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.staff.id !== undefined) {
            this.subscribeToSaveResponse(this.staffService.update(this.staff));
        } else {
            this.subscribeToSaveResponse(this.staffService.create(this.staff));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IStaff>>) {
        result.subscribe((res: HttpResponse<IStaff>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

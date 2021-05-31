import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITimeline } from 'app/shared/model/timeline.model';
import { TimelineService } from './timeline.service';

@Component({
    selector: 'jhi-timeline-update',
    templateUrl: './timeline-update.component.html'
})
export class TimelineUpdateComponent implements OnInit {
    timeline: ITimeline;
    isSaving: boolean;
    timeStart: string;
    createdDate: string;

    constructor(private timelineService: TimelineService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ timeline }) => {
            this.timeline = timeline;
            this.timeStart = this.timeline.timeStart != null ? this.timeline.timeStart.format(DATE_TIME_FORMAT) : null;
            this.createdDate = this.timeline.createdDate != null ? this.timeline.createdDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.timeline.timeStart = this.timeStart != null ? moment(this.timeStart, DATE_TIME_FORMAT) : null;
        this.timeline.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
        if (this.timeline.id !== undefined) {
            this.subscribeToSaveResponse(this.timelineService.update(this.timeline));
        } else {
            this.subscribeToSaveResponse(this.timelineService.create(this.timeline));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITimeline>>) {
        result.subscribe((res: HttpResponse<ITimeline>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

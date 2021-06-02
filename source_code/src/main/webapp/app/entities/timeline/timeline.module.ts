import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HomespaSharedModule } from 'app/shared';
import {
    TimelineComponent,
    TimelineDetailComponent,
    TimelineUpdateComponent,
    TimelineDeletePopupComponent,
    TimelineDeleteDialogComponent,
    timelineRoute,
    timelinePopupRoute
} from './';

const ENTITY_STATES = [...timelineRoute, ...timelinePopupRoute];

@NgModule({
    imports: [HomespaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TimelineComponent,
        TimelineDetailComponent,
        TimelineUpdateComponent,
        TimelineDeleteDialogComponent,
        TimelineDeletePopupComponent
    ],
    entryComponents: [TimelineComponent, TimelineUpdateComponent, TimelineDeleteDialogComponent, TimelineDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HomespaTimelineModule {}

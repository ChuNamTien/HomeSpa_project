import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HomespaSharedModule } from 'app/shared';
import {
    BookingActivityComponent,
    BookingActivityDetailComponent,
    BookingActivityUpdateComponent,
    BookingActivityDeletePopupComponent,
    BookingActivityDeleteDialogComponent,
    bookingActivityRoute,
    bookingActivityPopupRoute
} from './';

const ENTITY_STATES = [...bookingActivityRoute, ...bookingActivityPopupRoute];

@NgModule({
    imports: [HomespaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BookingActivityComponent,
        BookingActivityDetailComponent,
        BookingActivityUpdateComponent,
        BookingActivityDeleteDialogComponent,
        BookingActivityDeletePopupComponent
    ],
    entryComponents: [
        BookingActivityComponent,
        BookingActivityUpdateComponent,
        BookingActivityDeleteDialogComponent,
        BookingActivityDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HomespaBookingActivityModule {}

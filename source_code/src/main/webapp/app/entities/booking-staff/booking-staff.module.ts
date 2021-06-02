import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HomespaSharedModule } from 'app/shared';
import {
    BookingStaffComponent,
    BookingStaffDetailComponent,
    BookingStaffUpdateComponent,
    BookingStaffDeletePopupComponent,
    BookingStaffDeleteDialogComponent,
    bookingStaffRoute,
    bookingStaffPopupRoute
} from './';

const ENTITY_STATES = [...bookingStaffRoute, ...bookingStaffPopupRoute];

@NgModule({
    imports: [HomespaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BookingStaffComponent,
        BookingStaffDetailComponent,
        BookingStaffUpdateComponent,
        BookingStaffDeleteDialogComponent,
        BookingStaffDeletePopupComponent
    ],
    entryComponents: [
        BookingStaffComponent,
        BookingStaffUpdateComponent,
        BookingStaffDeleteDialogComponent,
        BookingStaffDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HomespaBookingStaffModule {}

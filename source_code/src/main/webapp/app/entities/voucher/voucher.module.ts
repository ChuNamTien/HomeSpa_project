import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HomespaSharedModule } from 'app/shared';
import {
    VoucherComponent,
    VoucherDetailComponent,
    VoucherUpdateComponent,
    VoucherDeletePopupComponent,
    VoucherDeleteDialogComponent,
    voucherRoute,
    voucherPopupRoute
} from './';

const ENTITY_STATES = [...voucherRoute, ...voucherPopupRoute];

@NgModule({
    imports: [HomespaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VoucherComponent,
        VoucherDetailComponent,
        VoucherUpdateComponent,
        VoucherDeleteDialogComponent,
        VoucherDeletePopupComponent
    ],
    entryComponents: [VoucherComponent, VoucherUpdateComponent, VoucherDeleteDialogComponent, VoucherDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HomespaVoucherModule {}

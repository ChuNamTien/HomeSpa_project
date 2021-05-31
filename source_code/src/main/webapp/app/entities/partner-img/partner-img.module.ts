import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HomespaSharedModule } from 'app/shared';
import {
    PartnerImgComponent,
    PartnerImgDetailComponent,
    PartnerImgUpdateComponent,
    PartnerImgDeletePopupComponent,
    PartnerImgDeleteDialogComponent,
    partnerImgRoute,
    partnerImgPopupRoute
} from './';

const ENTITY_STATES = [...partnerImgRoute, ...partnerImgPopupRoute];

@NgModule({
    imports: [HomespaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PartnerImgComponent,
        PartnerImgDetailComponent,
        PartnerImgUpdateComponent,
        PartnerImgDeleteDialogComponent,
        PartnerImgDeletePopupComponent
    ],
    entryComponents: [PartnerImgComponent, PartnerImgUpdateComponent, PartnerImgDeleteDialogComponent, PartnerImgDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HomespaPartnerImgModule {}

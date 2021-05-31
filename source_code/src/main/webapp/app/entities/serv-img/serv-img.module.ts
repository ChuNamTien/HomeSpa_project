import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HomespaSharedModule } from 'app/shared';
import {
    ServImgComponent,
    ServImgDetailComponent,
    ServImgUpdateComponent,
    ServImgDeletePopupComponent,
    ServImgDeleteDialogComponent,
    servImgRoute,
    servImgPopupRoute
} from './';

const ENTITY_STATES = [...servImgRoute, ...servImgPopupRoute];

@NgModule({
    imports: [HomespaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ServImgComponent,
        ServImgDetailComponent,
        ServImgUpdateComponent,
        ServImgDeleteDialogComponent,
        ServImgDeletePopupComponent
    ],
    entryComponents: [ServImgComponent, ServImgUpdateComponent, ServImgDeleteDialogComponent, ServImgDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HomespaServImgModule {}

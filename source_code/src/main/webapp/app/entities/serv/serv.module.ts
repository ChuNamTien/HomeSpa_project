import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HomespaSharedModule } from 'app/shared';
import {
    ServComponent,
    ServDetailComponent,
    ServUpdateComponent,
    ServDeletePopupComponent,
    ServDeleteDialogComponent,
    servRoute,
    servPopupRoute
} from './';

const ENTITY_STATES = [...servRoute, ...servPopupRoute];

@NgModule({
    imports: [HomespaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ServComponent, ServDetailComponent, ServUpdateComponent, ServDeleteDialogComponent, ServDeletePopupComponent],
    entryComponents: [ServComponent, ServUpdateComponent, ServDeleteDialogComponent, ServDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HomespaServModule {}

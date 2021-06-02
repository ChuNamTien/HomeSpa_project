import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HomespaSharedModule } from 'app/shared';
import { PARTNER_INDEX_ROUTE, PartnerIndexComponent } from '.';

@NgModule({
    imports: [HomespaSharedModule, RouterModule.forChild([PARTNER_INDEX_ROUTE])],
    declarations: [PartnerIndexComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PartnerIndexModule {}

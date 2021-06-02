import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HomespaSharedModule } from 'app/shared';
import { AdminIndexComponent, ADMIN_INDEX_ROUTE } from '.';

@NgModule({
    imports: [HomespaSharedModule, RouterModule.forChild([ADMIN_INDEX_ROUTE])],
    declarations: [AdminIndexComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AdminIndexModule {}

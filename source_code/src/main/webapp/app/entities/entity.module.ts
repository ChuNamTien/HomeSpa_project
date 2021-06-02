import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { HomespaCustomerModule } from './customer/customer.module';
import { HomespaPartnerModule } from './partner/partner.module';
import { HomespaPartnerImgModule } from './partner-img/partner-img.module';
import { HomespaStaffModule } from './staff/staff.module';
import { HomespaCategoryModule } from './category/category.module';
import { HomespaServModule } from './serv/serv.module';
import { HomespaServImgModule } from './serv-img/serv-img.module';
import { HomespaTreatmentModule } from './treatment/treatment.module';
import { HomespaBookingModule } from './booking/booking.module';
import { HomespaVoucherModule } from './voucher/voucher.module';
import { HomespaTimelineModule } from './timeline/timeline.module';
import { HomespaBookingStaffModule } from './booking-staff/booking-staff.module';
import { HomespaRatingModule } from './rating/rating.module';
import { HomespaTransactionModule } from './transaction/transaction.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        HomespaCustomerModule,
        HomespaPartnerModule,
        HomespaPartnerImgModule,
        HomespaStaffModule,
        HomespaCategoryModule,
        HomespaServModule,
        HomespaServImgModule,
        HomespaTreatmentModule,
        HomespaBookingModule,
        HomespaVoucherModule,
        HomespaTimelineModule,
        HomespaBookingStaffModule,
        HomespaRatingModule,
        HomespaTransactionModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HomespaEntityModule {}

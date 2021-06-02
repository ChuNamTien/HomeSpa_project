import { Route } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { PartnerIndexComponent } from './partner-index.component';

export const PARTNER_INDEX_ROUTE: Route = {
    path: 'partner/index',
    component: PartnerIndexComponent,
    data: {
        authorities: ['ROLE_PARTNER'],
        pageTitle: 'home.partner.title'
    },
    canActivate: [UserRouteAccessService]
};

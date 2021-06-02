import { Route } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { AdminIndexComponent } from './admin-index.component';

export const ADMIN_INDEX_ROUTE: Route = {
    path: 'admin/index',
    component: AdminIndexComponent,
    data: {
        authorities: ['ROLE_ADMIN'],
        pageTitle: 'home.admin.title'
    },
    canActivate: [UserRouteAccessService]
};

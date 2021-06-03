import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { BookingActivity } from 'app/shared/model/booking-activity.model';
import { BookingActivityService } from './booking-activity.service';
import { BookingActivityComponent } from './booking-activity.component';
import { BookingActivityDetailComponent } from './booking-activity-detail.component';
import { BookingActivityUpdateComponent } from './booking-activity-update.component';
import { BookingActivityDeletePopupComponent } from './booking-activity-delete-dialog.component';
import { IBookingActivity } from 'app/shared/model/booking-activity.model';

@Injectable({ providedIn: 'root' })
export class BookingActivityResolve implements Resolve<IBookingActivity> {
    constructor(private service: BookingActivityService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((bookingActivity: HttpResponse<BookingActivity>) => bookingActivity.body));
        }
        return of(new BookingActivity());
    }
}

export const bookingActivityRoute: Routes = [
    {
        path: 'booking-activity',
        component: BookingActivityComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'homespaApp.bookingActivity.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'booking-activity/:id/view',
        component: BookingActivityDetailComponent,
        resolve: {
            bookingActivity: BookingActivityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.bookingActivity.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'booking-activity/new',
        component: BookingActivityUpdateComponent,
        resolve: {
            bookingActivity: BookingActivityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.bookingActivity.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'booking-activity/:id/edit',
        component: BookingActivityUpdateComponent,
        resolve: {
            bookingActivity: BookingActivityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.bookingActivity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bookingActivityPopupRoute: Routes = [
    {
        path: 'booking-activity/:id/delete',
        component: BookingActivityDeletePopupComponent,
        resolve: {
            bookingActivity: BookingActivityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.bookingActivity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

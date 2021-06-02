import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { BookingStaff } from 'app/shared/model/booking-staff.model';
import { BookingStaffService } from './booking-staff.service';
import { BookingStaffComponent } from './booking-staff.component';
import { BookingStaffDetailComponent } from './booking-staff-detail.component';
import { BookingStaffUpdateComponent } from './booking-staff-update.component';
import { BookingStaffDeletePopupComponent } from './booking-staff-delete-dialog.component';
import { IBookingStaff } from 'app/shared/model/booking-staff.model';

@Injectable({ providedIn: 'root' })
export class BookingStaffResolve implements Resolve<IBookingStaff> {
    constructor(private service: BookingStaffService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((bookingStaff: HttpResponse<BookingStaff>) => bookingStaff.body));
        }
        return of(new BookingStaff());
    }
}

export const bookingStaffRoute: Routes = [
    {
        path: 'booking-staff',
        component: BookingStaffComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'homespaApp.bookingStaff.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'booking-staff/:id/view',
        component: BookingStaffDetailComponent,
        resolve: {
            bookingStaff: BookingStaffResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.bookingStaff.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'booking-staff/new',
        component: BookingStaffUpdateComponent,
        resolve: {
            bookingStaff: BookingStaffResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.bookingStaff.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'booking-staff/:id/edit',
        component: BookingStaffUpdateComponent,
        resolve: {
            bookingStaff: BookingStaffResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.bookingStaff.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bookingStaffPopupRoute: Routes = [
    {
        path: 'booking-staff/:id/delete',
        component: BookingStaffDeletePopupComponent,
        resolve: {
            bookingStaff: BookingStaffResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.bookingStaff.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

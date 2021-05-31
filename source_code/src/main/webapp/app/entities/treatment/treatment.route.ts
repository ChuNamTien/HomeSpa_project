import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Treatment } from 'app/shared/model/treatment.model';
import { TreatmentService } from './treatment.service';
import { TreatmentComponent } from './treatment.component';
import { TreatmentDetailComponent } from './treatment-detail.component';
import { TreatmentUpdateComponent } from './treatment-update.component';
import { TreatmentDeletePopupComponent } from './treatment-delete-dialog.component';
import { ITreatment } from 'app/shared/model/treatment.model';

@Injectable({ providedIn: 'root' })
export class TreatmentResolve implements Resolve<ITreatment> {
    constructor(private service: TreatmentService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((treatment: HttpResponse<Treatment>) => treatment.body));
        }
        return of(new Treatment());
    }
}

export const treatmentRoute: Routes = [
    {
        path: 'treatment',
        component: TreatmentComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'homespaApp.treatment.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'treatment/:id/view',
        component: TreatmentDetailComponent,
        resolve: {
            treatment: TreatmentResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.treatment.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'treatment/new',
        component: TreatmentUpdateComponent,
        resolve: {
            treatment: TreatmentResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.treatment.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'treatment/:id/edit',
        component: TreatmentUpdateComponent,
        resolve: {
            treatment: TreatmentResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.treatment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const treatmentPopupRoute: Routes = [
    {
        path: 'treatment/:id/delete',
        component: TreatmentDeletePopupComponent,
        resolve: {
            treatment: TreatmentResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.treatment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

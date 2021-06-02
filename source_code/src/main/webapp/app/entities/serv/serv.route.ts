import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Serv } from 'app/shared/model/serv.model';
import { ServService } from './serv.service';
import { ServComponent } from './serv.component';
import { ServDetailComponent } from './serv-detail.component';
import { ServUpdateComponent } from './serv-update.component';
import { ServDeletePopupComponent } from './serv-delete-dialog.component';
import { IServ } from 'app/shared/model/serv.model';

@Injectable({ providedIn: 'root' })
export class ServResolve implements Resolve<IServ> {
    constructor(private service: ServService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((serv: HttpResponse<Serv>) => serv.body));
        }
        return of(new Serv());
    }
}

export const servRoute: Routes = [
    {
        path: 'serv',
        component: ServComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'homespaApp.serv.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'serv/:id/view',
        component: ServDetailComponent,
        resolve: {
            serv: ServResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.serv.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'serv/new',
        component: ServUpdateComponent,
        resolve: {
            serv: ServResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.serv.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'serv/:id/edit',
        component: ServUpdateComponent,
        resolve: {
            serv: ServResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.serv.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const servPopupRoute: Routes = [
    {
        path: 'serv/:id/delete',
        component: ServDeletePopupComponent,
        resolve: {
            serv: ServResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.serv.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ServImg } from 'app/shared/model/serv-img.model';
import { ServImgService } from './serv-img.service';
import { ServImgComponent } from './serv-img.component';
import { ServImgDetailComponent } from './serv-img-detail.component';
import { ServImgUpdateComponent } from './serv-img-update.component';
import { ServImgDeletePopupComponent } from './serv-img-delete-dialog.component';
import { IServImg } from 'app/shared/model/serv-img.model';

@Injectable({ providedIn: 'root' })
export class ServImgResolve implements Resolve<IServImg> {
    constructor(private service: ServImgService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((servImg: HttpResponse<ServImg>) => servImg.body));
        }
        return of(new ServImg());
    }
}

export const servImgRoute: Routes = [
    {
        path: 'serv-img',
        component: ServImgComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'homespaApp.servImg.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'serv-img/:id/view',
        component: ServImgDetailComponent,
        resolve: {
            servImg: ServImgResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.servImg.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'serv-img/new',
        component: ServImgUpdateComponent,
        resolve: {
            servImg: ServImgResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.servImg.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'serv-img/:id/edit',
        component: ServImgUpdateComponent,
        resolve: {
            servImg: ServImgResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.servImg.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const servImgPopupRoute: Routes = [
    {
        path: 'serv-img/:id/delete',
        component: ServImgDeletePopupComponent,
        resolve: {
            servImg: ServImgResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.servImg.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

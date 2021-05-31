import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PartnerImg } from 'app/shared/model/partner-img.model';
import { PartnerImgService } from './partner-img.service';
import { PartnerImgComponent } from './partner-img.component';
import { PartnerImgDetailComponent } from './partner-img-detail.component';
import { PartnerImgUpdateComponent } from './partner-img-update.component';
import { PartnerImgDeletePopupComponent } from './partner-img-delete-dialog.component';
import { IPartnerImg } from 'app/shared/model/partner-img.model';

@Injectable({ providedIn: 'root' })
export class PartnerImgResolve implements Resolve<IPartnerImg> {
    constructor(private service: PartnerImgService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((partnerImg: HttpResponse<PartnerImg>) => partnerImg.body));
        }
        return of(new PartnerImg());
    }
}

export const partnerImgRoute: Routes = [
    {
        path: 'partner-img',
        component: PartnerImgComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'homespaApp.partnerImg.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'partner-img/:id/view',
        component: PartnerImgDetailComponent,
        resolve: {
            partnerImg: PartnerImgResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.partnerImg.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'partner-img/new',
        component: PartnerImgUpdateComponent,
        resolve: {
            partnerImg: PartnerImgResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.partnerImg.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'partner-img/:id/edit',
        component: PartnerImgUpdateComponent,
        resolve: {
            partnerImg: PartnerImgResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.partnerImg.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const partnerImgPopupRoute: Routes = [
    {
        path: 'partner-img/:id/delete',
        component: PartnerImgDeletePopupComponent,
        resolve: {
            partnerImg: PartnerImgResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'homespaApp.partnerImg.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

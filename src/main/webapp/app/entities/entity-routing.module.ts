import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'user-profile',
        data: { pageTitle: 'dashboardTestApp.userProfile.home.title' },
        loadChildren: () => import('./user-profile/user-profile.module').then(m => m.UserProfileModule),
      },
      {
        path: 'dashboard',
        data: { pageTitle: 'dashboardTestApp.dashboard.home.title' },
        loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule),
      },
      {
        path: 'dashboard-layout',
        data: { pageTitle: 'dashboardTestApp.dashboardLayout.home.title' },
        loadChildren: () => import('./dashboard-layout/dashboard-layout.module').then(m => m.DashboardLayoutModule),
      },
      {
        path: 'dashboard-item',
        data: { pageTitle: 'dashboardTestApp.dashboardItem.home.title' },
        loadChildren: () => import('./dashboard-item/dashboard-item.module').then(m => m.DashboardItemModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}

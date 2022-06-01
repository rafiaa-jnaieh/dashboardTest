import { IDashboardItem } from 'app/entities/dashboard-item/dashboard-item.model';
import { IDashboardLayout } from 'app/entities/dashboard-layout/dashboard-layout.model';
import { IUserProfile } from 'app/entities/user-profile/user-profile.model';

export interface IDashboard {
  id?: string;
  dashboardItems?: IDashboardItem[] | null;
  dashboardLayout?: IDashboardLayout | null;
  userProfile?: IUserProfile | null;
}

export class Dashboard implements IDashboard {
  constructor(
    public id?: string,
    public dashboardItems?: IDashboardItem[] | null,
    public dashboardLayout?: IDashboardLayout | null,
    public userProfile?: IUserProfile | null
  ) {}
}

export function getDashboardIdentifier(dashboard: IDashboard): string | undefined {
  return dashboard.id;
}

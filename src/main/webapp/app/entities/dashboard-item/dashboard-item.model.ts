import { IDashboard } from 'app/entities/dashboard/dashboard.model';

export interface IDashboardItem {
  id?: string;
  cols?: number | null;
  rows?: number | null;
  x?: number | null;
  y?: number | null;
  content?: string | null;
  dashboard?: IDashboard | null;
}

export class DashboardItem implements IDashboardItem {
  constructor(
    public id?: string,
    public cols?: number | null,
    public rows?: number | null,
    public x?: number | null,
    public y?: number | null,
    public content?: string | null,
    public dashboard?: IDashboard | null
  ) {}
}

export function getDashboardItemIdentifier(dashboardItem: IDashboardItem): string | undefined {
  return dashboardItem.id;
}

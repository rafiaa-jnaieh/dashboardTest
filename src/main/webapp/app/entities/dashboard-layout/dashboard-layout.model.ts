import { IDashboard } from 'app/entities/dashboard/dashboard.model';

export interface IDashboardLayout {
  id?: string;
  margin?: number | null;
  outerMargin?: boolean | null;
  outerMarginTop?: number | null;
  useTransformPositioning?: boolean | null;
  mobileBreakpoint?: number | null;
  useBodyForBreakpoint?: boolean | null;
  minCols?: number | null;
  maxCols?: number | null;
  minRows?: number | null;
  maxRows?: number | null;
  maxItemCols?: number | null;
  minItemCols?: number | null;
  maxItemRows?: number | null;
  minItemRows?: number | null;
  maxItemArea?: number | null;
  minItemArea?: number | null;
  defaultItemCols?: number | null;
  defaultItemRows?: number | null;
  ignoreMarginInRow?: boolean | null;
  draggable?: boolean | null;
  resizable?: boolean | null;
  dashboards?: IDashboard[] | null;
}

export class DashboardLayout implements IDashboardLayout {
  constructor(
    public id?: string,
    public margin?: number | null,
    public outerMargin?: boolean | null,
    public outerMarginTop?: number | null,
    public useTransformPositioning?: boolean | null,
    public mobileBreakpoint?: number | null,
    public useBodyForBreakpoint?: boolean | null,
    public minCols?: number | null,
    public maxCols?: number | null,
    public minRows?: number | null,
    public maxRows?: number | null,
    public maxItemCols?: number | null,
    public minItemCols?: number | null,
    public maxItemRows?: number | null,
    public minItemRows?: number | null,
    public maxItemArea?: number | null,
    public minItemArea?: number | null,
    public defaultItemCols?: number | null,
    public defaultItemRows?: number | null,
    public ignoreMarginInRow?: boolean | null,
    public draggable?: boolean | null,
    public resizable?: boolean | null,
    public dashboards?: IDashboard[] | null
  ) {
    this.outerMargin = this.outerMargin ?? false;
    this.useTransformPositioning = this.useTransformPositioning ?? false;
    this.useBodyForBreakpoint = this.useBodyForBreakpoint ?? false;
    this.ignoreMarginInRow = this.ignoreMarginInRow ?? false;
    this.draggable = this.draggable ?? false;
    this.resizable = this.resizable ?? false;
  }
}

export function getDashboardLayoutIdentifier(dashboardLayout: IDashboardLayout): string | undefined {
  return dashboardLayout.id;
}

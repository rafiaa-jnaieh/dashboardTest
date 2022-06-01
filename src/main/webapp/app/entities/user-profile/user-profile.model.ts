import { IDashboard } from 'app/entities/dashboard/dashboard.model';

export interface IUserProfile {
  id?: string;
  name?: string | null;
  priority?: number | null;
  dashboards?: IDashboard[] | null;
}

export class UserProfile implements IUserProfile {
  constructor(public id?: string, public name?: string | null, public priority?: number | null, public dashboards?: IDashboard[] | null) {}
}

export function getUserProfileIdentifier(userProfile: IUserProfile): string | undefined {
  return userProfile.id;
}

import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IDashboard, Dashboard } from '../dashboard.model';
import { DashboardService } from '../service/dashboard.service';
import { IDashboardLayout } from 'app/entities/dashboard-layout/dashboard-layout.model';
import { DashboardLayoutService } from 'app/entities/dashboard-layout/service/dashboard-layout.service';
import { IUserProfile } from 'app/entities/user-profile/user-profile.model';
import { UserProfileService } from 'app/entities/user-profile/service/user-profile.service';

@Component({
  selector: 'jhi-dashboard-update',
  templateUrl: './dashboard-update.component.html',
})
export class DashboardUpdateComponent implements OnInit {
  isSaving = false;

  dashboardLayoutsSharedCollection: IDashboardLayout[] = [];
  userProfilesSharedCollection: IUserProfile[] = [];

  editForm = this.fb.group({
    id: [],
    dashboardLayout: [],
    userProfile: [],
  });

  constructor(
    protected dashboardService: DashboardService,
    protected dashboardLayoutService: DashboardLayoutService,
    protected userProfileService: UserProfileService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dashboard }) => {
      this.updateForm(dashboard);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dashboard = this.createFromForm();
    if (dashboard.id !== undefined) {
      this.subscribeToSaveResponse(this.dashboardService.update(dashboard));
    } else {
      this.subscribeToSaveResponse(this.dashboardService.create(dashboard));
    }
  }

  trackDashboardLayoutById(_index: number, item: IDashboardLayout): string {
    return item.id!;
  }

  trackUserProfileById(_index: number, item: IUserProfile): string {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDashboard>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(dashboard: IDashboard): void {
    this.editForm.patchValue({
      id: dashboard.id,
      dashboardLayout: dashboard.dashboardLayout,
      userProfile: dashboard.userProfile,
    });

    this.dashboardLayoutsSharedCollection = this.dashboardLayoutService.addDashboardLayoutToCollectionIfMissing(
      this.dashboardLayoutsSharedCollection,
      dashboard.dashboardLayout
    );
    this.userProfilesSharedCollection = this.userProfileService.addUserProfileToCollectionIfMissing(
      this.userProfilesSharedCollection,
      dashboard.userProfile
    );
  }

  protected loadRelationshipsOptions(): void {
    this.dashboardLayoutService
      .query()
      .pipe(map((res: HttpResponse<IDashboardLayout[]>) => res.body ?? []))
      .pipe(
        map((dashboardLayouts: IDashboardLayout[]) =>
          this.dashboardLayoutService.addDashboardLayoutToCollectionIfMissing(dashboardLayouts, this.editForm.get('dashboardLayout')!.value)
        )
      )
      .subscribe((dashboardLayouts: IDashboardLayout[]) => (this.dashboardLayoutsSharedCollection = dashboardLayouts));

    this.userProfileService
      .query()
      .pipe(map((res: HttpResponse<IUserProfile[]>) => res.body ?? []))
      .pipe(
        map((userProfiles: IUserProfile[]) =>
          this.userProfileService.addUserProfileToCollectionIfMissing(userProfiles, this.editForm.get('userProfile')!.value)
        )
      )
      .subscribe((userProfiles: IUserProfile[]) => (this.userProfilesSharedCollection = userProfiles));
  }

  protected createFromForm(): IDashboard {
    return {
      ...new Dashboard(),
      id: this.editForm.get(['id'])!.value,
      dashboardLayout: this.editForm.get(['dashboardLayout'])!.value,
      userProfile: this.editForm.get(['userProfile'])!.value,
    };
  }
}

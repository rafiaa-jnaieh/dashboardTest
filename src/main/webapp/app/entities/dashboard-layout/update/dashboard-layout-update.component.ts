import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IDashboardLayout, DashboardLayout } from '../dashboard-layout.model';
import { DashboardLayoutService } from '../service/dashboard-layout.service';

@Component({
  selector: 'jhi-dashboard-layout-update',
  templateUrl: './dashboard-layout-update.component.html',
})
export class DashboardLayoutUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    margin: [],
    outerMargin: [],
    outerMarginTop: [],
    useTransformPositioning: [],
    mobileBreakpoint: [],
    useBodyForBreakpoint: [],
    minCols: [],
    maxCols: [],
    minRows: [],
    maxRows: [],
    maxItemCols: [],
    minItemCols: [],
    maxItemRows: [],
    minItemRows: [],
    maxItemArea: [],
    minItemArea: [],
    defaultItemCols: [],
    defaultItemRows: [],
    ignoreMarginInRow: [],
    draggable: [],
    resizable: [],
  });

  constructor(
    protected dashboardLayoutService: DashboardLayoutService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dashboardLayout }) => {
      this.updateForm(dashboardLayout);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dashboardLayout = this.createFromForm();
    if (dashboardLayout.id !== undefined) {
      this.subscribeToSaveResponse(this.dashboardLayoutService.update(dashboardLayout));
    } else {
      this.subscribeToSaveResponse(this.dashboardLayoutService.create(dashboardLayout));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDashboardLayout>>): void {
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

  protected updateForm(dashboardLayout: IDashboardLayout): void {
    this.editForm.patchValue({
      id: dashboardLayout.id,
      margin: dashboardLayout.margin,
      outerMargin: dashboardLayout.outerMargin,
      outerMarginTop: dashboardLayout.outerMarginTop,
      useTransformPositioning: dashboardLayout.useTransformPositioning,
      mobileBreakpoint: dashboardLayout.mobileBreakpoint,
      useBodyForBreakpoint: dashboardLayout.useBodyForBreakpoint,
      minCols: dashboardLayout.minCols,
      maxCols: dashboardLayout.maxCols,
      minRows: dashboardLayout.minRows,
      maxRows: dashboardLayout.maxRows,
      maxItemCols: dashboardLayout.maxItemCols,
      minItemCols: dashboardLayout.minItemCols,
      maxItemRows: dashboardLayout.maxItemRows,
      minItemRows: dashboardLayout.minItemRows,
      maxItemArea: dashboardLayout.maxItemArea,
      minItemArea: dashboardLayout.minItemArea,
      defaultItemCols: dashboardLayout.defaultItemCols,
      defaultItemRows: dashboardLayout.defaultItemRows,
      ignoreMarginInRow: dashboardLayout.ignoreMarginInRow,
      draggable: dashboardLayout.draggable,
      resizable: dashboardLayout.resizable,
    });
  }

  protected createFromForm(): IDashboardLayout {
    return {
      ...new DashboardLayout(),
      id: this.editForm.get(['id'])!.value,
      margin: this.editForm.get(['margin'])!.value,
      outerMargin: this.editForm.get(['outerMargin'])!.value,
      outerMarginTop: this.editForm.get(['outerMarginTop'])!.value,
      useTransformPositioning: this.editForm.get(['useTransformPositioning'])!.value,
      mobileBreakpoint: this.editForm.get(['mobileBreakpoint'])!.value,
      useBodyForBreakpoint: this.editForm.get(['useBodyForBreakpoint'])!.value,
      minCols: this.editForm.get(['minCols'])!.value,
      maxCols: this.editForm.get(['maxCols'])!.value,
      minRows: this.editForm.get(['minRows'])!.value,
      maxRows: this.editForm.get(['maxRows'])!.value,
      maxItemCols: this.editForm.get(['maxItemCols'])!.value,
      minItemCols: this.editForm.get(['minItemCols'])!.value,
      maxItemRows: this.editForm.get(['maxItemRows'])!.value,
      minItemRows: this.editForm.get(['minItemRows'])!.value,
      maxItemArea: this.editForm.get(['maxItemArea'])!.value,
      minItemArea: this.editForm.get(['minItemArea'])!.value,
      defaultItemCols: this.editForm.get(['defaultItemCols'])!.value,
      defaultItemRows: this.editForm.get(['defaultItemRows'])!.value,
      ignoreMarginInRow: this.editForm.get(['ignoreMarginInRow'])!.value,
      draggable: this.editForm.get(['draggable'])!.value,
      resizable: this.editForm.get(['resizable'])!.value,
    };
  }
}

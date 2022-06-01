import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DashboardItemService } from '../service/dashboard-item.service';
import { IDashboardItem, DashboardItem } from '../dashboard-item.model';
import { IDashboard } from 'app/entities/dashboard/dashboard.model';
import { DashboardService } from 'app/entities/dashboard/service/dashboard.service';

import { DashboardItemUpdateComponent } from './dashboard-item-update.component';

describe('DashboardItem Management Update Component', () => {
  let comp: DashboardItemUpdateComponent;
  let fixture: ComponentFixture<DashboardItemUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let dashboardItemService: DashboardItemService;
  let dashboardService: DashboardService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DashboardItemUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(DashboardItemUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DashboardItemUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    dashboardItemService = TestBed.inject(DashboardItemService);
    dashboardService = TestBed.inject(DashboardService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Dashboard query and add missing value', () => {
      const dashboardItem: IDashboardItem = { id: 'CBA' };
      const dashboard: IDashboard = { id: '6e890eb4-c04f-4852-840b-ce048b937037' };
      dashboardItem.dashboard = dashboard;

      const dashboardCollection: IDashboard[] = [{ id: 'f205b632-3513-4565-af03-259226e456b1' }];
      jest.spyOn(dashboardService, 'query').mockReturnValue(of(new HttpResponse({ body: dashboardCollection })));
      const additionalDashboards = [dashboard];
      const expectedCollection: IDashboard[] = [...additionalDashboards, ...dashboardCollection];
      jest.spyOn(dashboardService, 'addDashboardToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ dashboardItem });
      comp.ngOnInit();

      expect(dashboardService.query).toHaveBeenCalled();
      expect(dashboardService.addDashboardToCollectionIfMissing).toHaveBeenCalledWith(dashboardCollection, ...additionalDashboards);
      expect(comp.dashboardsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const dashboardItem: IDashboardItem = { id: 'CBA' };
      const dashboard: IDashboard = { id: 'd973be26-fbcc-48c8-8b62-bc0a463c41e8' };
      dashboardItem.dashboard = dashboard;

      activatedRoute.data = of({ dashboardItem });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(dashboardItem));
      expect(comp.dashboardsSharedCollection).toContain(dashboard);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DashboardItem>>();
      const dashboardItem = { id: 'ABC' };
      jest.spyOn(dashboardItemService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dashboardItem });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: dashboardItem }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(dashboardItemService.update).toHaveBeenCalledWith(dashboardItem);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DashboardItem>>();
      const dashboardItem = new DashboardItem();
      jest.spyOn(dashboardItemService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dashboardItem });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: dashboardItem }));
      saveSubject.complete();

      // THEN
      expect(dashboardItemService.create).toHaveBeenCalledWith(dashboardItem);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DashboardItem>>();
      const dashboardItem = { id: 'ABC' };
      jest.spyOn(dashboardItemService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dashboardItem });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(dashboardItemService.update).toHaveBeenCalledWith(dashboardItem);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackDashboardById', () => {
      it('Should return tracked Dashboard primary key', () => {
        const entity = { id: 'ABC' };
        const trackResult = comp.trackDashboardById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DashboardService } from '../service/dashboard.service';
import { IDashboard, Dashboard } from '../dashboard.model';
import { IDashboardLayout } from 'app/entities/dashboard-layout/dashboard-layout.model';
import { DashboardLayoutService } from 'app/entities/dashboard-layout/service/dashboard-layout.service';
import { IUserProfile } from 'app/entities/user-profile/user-profile.model';
import { UserProfileService } from 'app/entities/user-profile/service/user-profile.service';

import { DashboardUpdateComponent } from './dashboard-update.component';

describe('Dashboard Management Update Component', () => {
  let comp: DashboardUpdateComponent;
  let fixture: ComponentFixture<DashboardUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let dashboardService: DashboardService;
  let dashboardLayoutService: DashboardLayoutService;
  let userProfileService: UserProfileService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DashboardUpdateComponent],
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
      .overrideTemplate(DashboardUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DashboardUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    dashboardService = TestBed.inject(DashboardService);
    dashboardLayoutService = TestBed.inject(DashboardLayoutService);
    userProfileService = TestBed.inject(UserProfileService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call DashboardLayout query and add missing value', () => {
      const dashboard: IDashboard = { id: 'CBA' };
      const dashboardLayout: IDashboardLayout = { id: '2b43c040-8621-4aa0-ad74-bd853713ce77' };
      dashboard.dashboardLayout = dashboardLayout;

      const dashboardLayoutCollection: IDashboardLayout[] = [{ id: 'd40e57be-9d86-4950-b5b5-ff843d301fd9' }];
      jest.spyOn(dashboardLayoutService, 'query').mockReturnValue(of(new HttpResponse({ body: dashboardLayoutCollection })));
      const additionalDashboardLayouts = [dashboardLayout];
      const expectedCollection: IDashboardLayout[] = [...additionalDashboardLayouts, ...dashboardLayoutCollection];
      jest.spyOn(dashboardLayoutService, 'addDashboardLayoutToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ dashboard });
      comp.ngOnInit();

      expect(dashboardLayoutService.query).toHaveBeenCalled();
      expect(dashboardLayoutService.addDashboardLayoutToCollectionIfMissing).toHaveBeenCalledWith(
        dashboardLayoutCollection,
        ...additionalDashboardLayouts
      );
      expect(comp.dashboardLayoutsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call UserProfile query and add missing value', () => {
      const dashboard: IDashboard = { id: 'CBA' };
      const userProfile: IUserProfile = { id: 'bd3894c0-6a4f-4de8-ad20-0e5ad7cdb4f1' };
      dashboard.userProfile = userProfile;

      const userProfileCollection: IUserProfile[] = [{ id: '065841e6-f7c6-42e5-be93-415090e7538c' }];
      jest.spyOn(userProfileService, 'query').mockReturnValue(of(new HttpResponse({ body: userProfileCollection })));
      const additionalUserProfiles = [userProfile];
      const expectedCollection: IUserProfile[] = [...additionalUserProfiles, ...userProfileCollection];
      jest.spyOn(userProfileService, 'addUserProfileToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ dashboard });
      comp.ngOnInit();

      expect(userProfileService.query).toHaveBeenCalled();
      expect(userProfileService.addUserProfileToCollectionIfMissing).toHaveBeenCalledWith(userProfileCollection, ...additionalUserProfiles);
      expect(comp.userProfilesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const dashboard: IDashboard = { id: 'CBA' };
      const dashboardLayout: IDashboardLayout = { id: '8b679ca3-ec6c-4047-805d-d51b186b2055' };
      dashboard.dashboardLayout = dashboardLayout;
      const userProfile: IUserProfile = { id: 'd33b50be-e222-432b-b0ba-d50df02ba8cd' };
      dashboard.userProfile = userProfile;

      activatedRoute.data = of({ dashboard });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(dashboard));
      expect(comp.dashboardLayoutsSharedCollection).toContain(dashboardLayout);
      expect(comp.userProfilesSharedCollection).toContain(userProfile);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Dashboard>>();
      const dashboard = { id: 'ABC' };
      jest.spyOn(dashboardService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dashboard });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: dashboard }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(dashboardService.update).toHaveBeenCalledWith(dashboard);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Dashboard>>();
      const dashboard = new Dashboard();
      jest.spyOn(dashboardService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dashboard });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: dashboard }));
      saveSubject.complete();

      // THEN
      expect(dashboardService.create).toHaveBeenCalledWith(dashboard);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Dashboard>>();
      const dashboard = { id: 'ABC' };
      jest.spyOn(dashboardService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dashboard });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(dashboardService.update).toHaveBeenCalledWith(dashboard);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackDashboardLayoutById', () => {
      it('Should return tracked DashboardLayout primary key', () => {
        const entity = { id: 'ABC' };
        const trackResult = comp.trackDashboardLayoutById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackUserProfileById', () => {
      it('Should return tracked UserProfile primary key', () => {
        const entity = { id: 'ABC' };
        const trackResult = comp.trackUserProfileById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});

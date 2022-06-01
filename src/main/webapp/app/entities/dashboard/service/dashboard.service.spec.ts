import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IDashboard, Dashboard } from '../dashboard.model';

import { DashboardService } from './dashboard.service';

describe('Dashboard Service', () => {
  let service: DashboardService;
  let httpMock: HttpTestingController;
  let elemDefault: IDashboard;
  let expectedResult: IDashboard | IDashboard[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DashboardService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find('ABC').subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Dashboard', () => {
      const returnedFromService = Object.assign(
        {
          id: 'ID',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Dashboard()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Dashboard', () => {
      const returnedFromService = Object.assign(
        {
          id: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Dashboard', () => {
      const patchObject = Object.assign({}, new Dashboard());

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Dashboard', () => {
      const returnedFromService = Object.assign(
        {
          id: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Dashboard', () => {
      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addDashboardToCollectionIfMissing', () => {
      it('should add a Dashboard to an empty array', () => {
        const dashboard: IDashboard = { id: 'ABC' };
        expectedResult = service.addDashboardToCollectionIfMissing([], dashboard);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(dashboard);
      });

      it('should not add a Dashboard to an array that contains it', () => {
        const dashboard: IDashboard = { id: 'ABC' };
        const dashboardCollection: IDashboard[] = [
          {
            ...dashboard,
          },
          { id: 'CBA' },
        ];
        expectedResult = service.addDashboardToCollectionIfMissing(dashboardCollection, dashboard);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Dashboard to an array that doesn't contain it", () => {
        const dashboard: IDashboard = { id: 'ABC' };
        const dashboardCollection: IDashboard[] = [{ id: 'CBA' }];
        expectedResult = service.addDashboardToCollectionIfMissing(dashboardCollection, dashboard);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(dashboard);
      });

      it('should add only unique Dashboard to an array', () => {
        const dashboardArray: IDashboard[] = [{ id: 'ABC' }, { id: 'CBA' }, { id: '7ffbe89c-8568-408d-849b-defc84933895' }];
        const dashboardCollection: IDashboard[] = [{ id: 'ABC' }];
        expectedResult = service.addDashboardToCollectionIfMissing(dashboardCollection, ...dashboardArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const dashboard: IDashboard = { id: 'ABC' };
        const dashboard2: IDashboard = { id: 'CBA' };
        expectedResult = service.addDashboardToCollectionIfMissing([], dashboard, dashboard2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(dashboard);
        expect(expectedResult).toContain(dashboard2);
      });

      it('should accept null and undefined values', () => {
        const dashboard: IDashboard = { id: 'ABC' };
        expectedResult = service.addDashboardToCollectionIfMissing([], null, dashboard, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(dashboard);
      });

      it('should return initial array if no Dashboard is added', () => {
        const dashboardCollection: IDashboard[] = [{ id: 'ABC' }];
        expectedResult = service.addDashboardToCollectionIfMissing(dashboardCollection, undefined, null);
        expect(expectedResult).toEqual(dashboardCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});

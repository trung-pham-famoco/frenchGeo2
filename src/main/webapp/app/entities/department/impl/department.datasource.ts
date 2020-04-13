import { DataSource } from '@angular/cdk/table';
import { IDepartment } from 'app/shared/model/department.model';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { DepartmentService, DepartmentPagination } from './department.service';
import { finalize, catchError, delay } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { CollectionViewer } from '@angular/cdk/collections';
import { DepartmentFilter } from './department.filter';
import { HttpResponse } from '@angular/common/http';

export class DepartmentDataSource implements DataSource<IDepartment> {
  /**
   * Internal subject of departments
   */
  private departmentsSubject = new BehaviorSubject<IDepartment[]>([]);

  /**
   * Used by loadingSubject.next(true)
   */
  private loadingSubject = new BehaviorSubject<boolean>(false);

  /**
   * Can be used elsewhere to know when data are loading
   */
  public loading$ = this.loadingSubject.asObservable();

  /**
   * Total number of elements of the source
   */
  public totalElements?: number;

  /**
   * @param departmentService to get data from the API
   */
  constructor(private departmentService: DepartmentService) {}

  /**
   * Get or refresh data depending on params on the method which will be emit by citiesSubject
   * @param filter possible filter to use
   * @param sortField column to filter on
   * @param sortDirection  direction to filter on
   * @param pageIndex page number (use 0 by default)
   * @param pageSize the page size to get
   */
  loadDepartments(filter: DepartmentFilter, sortField: string, sortDirection: string, pageIndex: number, pageSize: number): void {
    this.loadingSubject.next(true);

    const query: DepartmentPagination = { page: pageIndex, size: pageSize, sort: ['id,asc'] };
    if (sortField) {
      query.sort = this.sort(sortField, sortDirection);
    }

    /**
     * If you want to add new filter you can add them here
     */
    if (filter.populationMin) {
      query.populationMin = filter.populationMin;
    }

    if (filter.regionName) {
      query.regionName = filter.regionName;
    }

    this.departmentService
      .query(query)
      .pipe(
        delay(800), // An ergo delay time that could be in constant
        catchError((err, caught) => caught),
        finalize(() => this.loadingSubject.next(false))
      )
      .subscribe((response: HttpResponse<IDepartment[]>) => {
        this.totalElements = Number(response.headers.get('X-total-count'));
        if (response.body !== null) {
          this.departmentsSubject.next(response.body);
        }
      });
  }

  /**
   * Helper to create a request a sort if required
   * @param sortField the field to sort on
   * @param sortDirection 'desc' or 'asc'
   */
  sort(sortField?: string, sortDirection?: string): string[] {
    const result = [sortField + ',' + sortDirection];
    return result;
  }

  /**
   * return an observable of citiesSubject
   * @param collectionViewer not used here, see DataSource documentation
   */
  connect(collectionViewer: CollectionViewer): Observable<IDepartment[]> {
    return this.departmentsSubject.asObservable();
  }

  /**
   * Complete subjects in order to avoid memory leak
   * @param collectionViewer not used here, see DataSource documentation
   */
  disconnect(collectionViewer: CollectionViewer): void {
    this.departmentsSubject.complete();
    this.loadingSubject.complete();
  }
}

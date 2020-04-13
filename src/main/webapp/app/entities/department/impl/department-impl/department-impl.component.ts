import { Component, OnInit, ViewChild, AfterViewInit, OnDestroy } from '@angular/core';

import { DepartmentService } from '../department.service';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { DepartmentDataSource } from '../department.datasource';
import { DepartmentFilter } from '../department.filter';
import { FormBuilder } from '@angular/forms';
import { merge, Subject } from 'rxjs';
import { tap, distinctUntilChanged, filter, takeUntil, debounceTime } from 'rxjs/operators';

type DepartmentHttpParams = {
  page: number;
  size: number;
  sort: string[];
  threshold?: string;
  regionName?: string;
};
@Component({
  selector: 'jhi-department-impl',
  templateUrl: './department-impl.component.html',
  styleUrls: ['department-impl.component.scss']
})
export class DepartmentImplComponent implements OnInit, AfterViewInit, OnDestroy {
  private readonly PAGE_SIZE_BY_DEFAULT = 10;
  private readonly PAGE_INDEX_BY_DEFAULT = 0;
  // properties for paginator
  displayedColumns: string[] = ['id', 'name', 'population', 'departmentNumber', 'region.name'];
  totalNbOfDepartments?: number;
  // properties for table
  departments!: DepartmentDataSource;
  // properties and selector
  minNbsOfPeople: string[] = ['500000', '600000', '700000', '800000'];
  public filter: DepartmentFilter = {};
  public filterForm = this.formBuilder.group({
    filterPopulationMin: 0,
    filterRegionName: ''
  });
  private destroy$ = new Subject();

  @ViewChild(MatSort, { static: true }) private _sort!: MatSort;
  @ViewChild(MatPaginator, { static: true }) private _paginator!: MatPaginator;

  constructor(protected departmentService: DepartmentService, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.departments = new DepartmentDataSource(this.departmentService);
    this.departments.loadDepartments(this.filter, 'id', 'asc', this.PAGE_INDEX_BY_DEFAULT, this.PAGE_SIZE_BY_DEFAULT);
    this.subscribeToReactiveForm();
  }

  private subscribeToReactiveForm(): void {
    this._filterFormField('filterPopulationMin', 'populationMin', (val: number) => val);
    this._filterFormField('filterRegionName', 'regionName', (val: string) => val);
  }

  private _filterFormField(formControlName: string, filterObjectField: string, fcChangeValue: (val: any) => any): void {
    this.filterForm
      .get(formControlName)!
      .valueChanges.pipe(
        debounceTime(300),
        distinctUntilChanged(),
        filter(value => value !== null),
        takeUntil(this.destroy$)
      )
      .subscribe(value => {
        // eslint-disable-next-line no-console
        console.log('value = ', value);
        this._paginator.pageIndex = 0;
        this.filter[filterObjectField] = fcChangeValue(value);
        this.loadDepartmentsPage();
      });
  }

  private loadDepartmentsPage(): void {
    this.departments.loadDepartments(
      this.filter,
      this._sort.active,
      this._sort.direction,
      this._paginator.pageIndex,
      this._paginator.pageSize
    );
  }

  ngAfterViewInit(): void {
    this._sort.sortChange.subscribe(() => (this._paginator.pageIndex = 0));
    merge(this._sort.sortChange, this._paginator.page)
      .pipe(tap(() => this.loadDepartmentsPage()))
      .subscribe();
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}

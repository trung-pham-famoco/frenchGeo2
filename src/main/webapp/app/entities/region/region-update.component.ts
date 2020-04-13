import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IRegion, Region } from 'app/shared/model/region.model';
import { RegionService } from './region.service';
import { ICity } from 'app/shared/model/city.model';
import { CityService } from 'app/entities/city/city.service';

@Component({
  selector: 'jhi-region-update',
  templateUrl: './region-update.component.html'
})
export class RegionUpdateComponent implements OnInit {
  isSaving = false;
  prefectures: ICity[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    prefectureId: []
  });

  constructor(
    protected regionService: RegionService,
    protected cityService: CityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ region }) => {
      this.updateForm(region);

      this.cityService
        .query({ filter: 'region-is-null' })
        .pipe(
          map((res: HttpResponse<ICity[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICity[]) => {
          if (!region.prefectureId) {
            this.prefectures = resBody;
          } else {
            this.cityService
              .find(region.prefectureId)
              .pipe(
                map((subRes: HttpResponse<ICity>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICity[]) => (this.prefectures = concatRes));
          }
        });
    });
  }

  updateForm(region: IRegion): void {
    this.editForm.patchValue({
      id: region.id,
      name: region.name,
      prefectureId: region.prefectureId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const region = this.createFromForm();
    if (region.id !== undefined) {
      this.subscribeToSaveResponse(this.regionService.update(region));
    } else {
      this.subscribeToSaveResponse(this.regionService.create(region));
    }
  }

  private createFromForm(): IRegion {
    return {
      ...new Region(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      prefectureId: this.editForm.get(['prefectureId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRegion>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ICity): any {
    return item.id;
  }
}

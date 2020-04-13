import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'region',
        loadChildren: () => import('./region/region.module').then(m => m.FrenchGeo2RegionModule)
      },
      {
        path: 'department',
        loadChildren: () => import('./department/department.module').then(m => m.FrenchGeo2DepartmentModule)
      },
      {
        path: 'city',
        loadChildren: () => import('./city/city.module').then(m => m.FrenchGeo2CityModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class FrenchGeo2EntityModule {}

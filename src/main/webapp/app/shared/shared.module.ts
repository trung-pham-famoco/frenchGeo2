import { NgModule } from '@angular/core';
import { FrenchGeo2SharedLibsModule } from './shared-libs.module';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { CustomMaterialModule } from './custom-material.module';

@NgModule({
  imports: [FrenchGeo2SharedLibsModule, CustomMaterialModule],
  declarations: [AlertComponent, AlertErrorComponent, HasAnyAuthorityDirective],
  exports: [FrenchGeo2SharedLibsModule, AlertComponent, AlertErrorComponent, HasAnyAuthorityDirective, CustomMaterialModule]
})
export class FrenchGeo2SharedModule {}

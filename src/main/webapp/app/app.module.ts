import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { FrenchGeo2SharedModule } from 'app/shared/shared.module';
import { FrenchGeo2CoreModule } from 'app/core/core.module';
import { FrenchGeo2AppRoutingModule } from './app-routing.module';
import { FrenchGeo2HomeModule } from './home/home.module';
import { FrenchGeo2EntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CommonModule } from '@angular/common';

@NgModule({
  imports: [
    BrowserModule,
    FrenchGeo2SharedModule,
    FrenchGeo2CoreModule,
    FrenchGeo2HomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    FrenchGeo2EntityModule,
    FrenchGeo2AppRoutingModule,
    BrowserAnimationsModule,
    CommonModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent]
})
export class FrenchGeo2AppModule {}

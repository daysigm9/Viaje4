import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule} from '@angular/common/http'

import { ButtonModule } from 'primeng/button';
import {CardModule} from 'primeng/card';
import {InputTextModule} from 'primeng/inputtext';
import {InputMaskModule} from 'primeng/inputmask';
import { FormsModule } from '@angular/forms';
import { MenuModule } from 'primeng/menu';
import { HomeComponent } from './components/home/home.component';
import { ReservaComponent } from './components/reserva/reserva.component';
import { ReservaIntermediaComponent } from './components/reserva-intermedia/reserva-intermedia.component';
import { ReporteComponent } from './components/reporte/reporte.component';
import { GraficaComponent } from './components/grafica/grafica.component';
import { GeneraPdfComponent } from './components/genera-pdf/genera-pdf.component';
import { ChartModule } from 'primeng/chart';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ReservaComponent,
    ReservaIntermediaComponent,
    ReporteComponent,
    GraficaComponent,
    GeneraPdfComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ButtonModule,
    CardModule,
    InputTextModule,
    InputMaskModule,
    HttpClientModule,
    MenuModule,
    ChartModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

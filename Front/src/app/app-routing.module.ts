import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ReporteComponent } from './components/reporte/reporte.component';
import { ReservaComponent } from './components/reserva/reserva.component';
import { ReservaIntermediaComponent } from './components/reserva-intermedia/reserva-intermedia.component';
import { GraficaComponent } from './components/grafica/grafica.component';
import { GeneraPdfComponent } from './components/genera-pdf/genera-pdf.component';

const routes: Routes = [
  {path:'home',component:HomeComponent},
  {path:'reserva',component:ReservaComponent},
  {path:'reserva-intermedia',component:ReservaIntermediaComponent},
  {path:'grafica',component:GraficaComponent},
  {path:'genera-pdf',component:GeneraPdfComponent},
  {path:'reporte',component:ReporteComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

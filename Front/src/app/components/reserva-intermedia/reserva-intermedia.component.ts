import { Component, OnInit } from '@angular/core';
import { FechaUtils } from 'src/app/Utils/fecha-utils';
import { SelectFecha } from 'src/app/models/select-fecha';

@Component({
  selector: 'app-reserva-intermedia',
  templateUrl: './reserva-intermedia.component.html',
  styleUrls: ['./reserva-intermedia.component.css']
})



export class ReservaIntermediaComponent implements OnInit {
  fechasIniciales:string[]=[];
  fechasSelect:SelectFecha[]=[];
  fechaSeleccionada:string="";
  origenSeleccionado:string="";
  destinoSeleccionado:string="";

  ngOnInit(): void {
    this.fechasIniciales=FechaUtils.getDatesArray();
    this.fechasSelect = this.fechasIniciales.map(m=>new SelectFecha(m,FechaUtils.formatStrToStr2(m)));
  }


}

import { Component, OnInit } from '@angular/core';
import { FechaUtils } from 'src/app/Utils/fecha-utils';
import { OrigenDestino } from 'src/app/models/origen-destino';
import { SelectFecha } from 'src/app/models/select-fecha';
import { ViajesService } from 'src/app/services/viajes.service';

@Component({
  selector: 'app-reserva-intermedia',
  templateUrl: './reserva-intermedia.component.html',
  styleUrls: ['./reserva-intermedia.component.css']
})



export class ReservaIntermediaComponent implements OnInit {
  fechasIniciales:string[]=[];
  fechasSelect:SelectFecha[]=[];

  origenes:string[]=[];
  destinos:string[]=[];

  origenDestino:OrigenDestino[]=[];

  fechaSeleccionada:string="";
  origenSeleccionado:string="";
  destinoSeleccionado:string="";

  constructor(private viajesService:ViajesService) {

  }

  ngOnInit(): void {
    this.fechasIniciales=FechaUtils.getDatesArray();
    this.fechasSelect = this.fechasIniciales.map(m=>new SelectFecha(m,FechaUtils.formatStrToStr2(m)));

    this.viajesService.obtenerDestinoIntermedios().subscribe(resp=>{
      if(resp.status==1){
        this.origenDestino=resp.result;
        this.origenes=this.origenDestino.map(m=>m.origen);
        if(this.origenes.length){
          this.origenSeleccionado=this.origenes[0];
          this.destinos=this.origenDestino.filter(f=>f.origen==this.origenSeleccionado).map(m=>m.destino);
        }else{
          this.destinos=[];
          this.origenSeleccionado="";
          this.destinoSeleccionado="";
        }
      }
    });
  }

  cambio(event:any){
    this.origenSeleccionado=event.value;
    this.destinos=this.origenDestino.filter(f=>f.origen==this.origenSeleccionado).map(m=>m.destino);

  }

  cambioDestino(event:any){
    this.destinoSeleccionado=event.value;
  }


}

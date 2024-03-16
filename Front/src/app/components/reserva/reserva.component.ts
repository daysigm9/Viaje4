import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { FechaUtils } from 'src/app/Utils/fecha-utils';
import { DatoViaje } from 'src/app/models/dato-viaje';
import { OrigenDestino } from 'src/app/models/origen-destino';
import { SelectFecha } from 'src/app/models/select-fecha';
import { ViajesService } from 'src/app/services/viajes.service';


@Component({
  selector: 'app-reserva',
  templateUrl: './reserva.component.html',
  styleUrls: ['./reserva.component.css']
})

export class ReservaComponent implements OnInit {
  fechasIniciales:string[]=[];
  fechasSelect:SelectFecha[]=[];

  origenes:string[]=[];
  destinos:string[]=[];

  origenDestino:OrigenDestino[]=[];

  fechaSeleccionada:string="";
  origenSeleccionado:string="";
  destinoSeleccionado:string="";

  datosViaje:DatoViaje[]=[];
  datoViajeSeleccionado:DatoViaje=new DatoViaje();


  activaTabla:boolean=false;
  activaDetalle:boolean=false;

  fechaDesabilitada:boolean=false;
  origenDesabilitada:boolean=false;
  destinoDesabilitada:boolean=false;

  botonDesabilitado:boolean=false;



  constructor(
    private viajesService:ViajesService,
    private messageService:MessageService
    ) {

  }


  ngOnInit(): void {
    this.fechasIniciales=FechaUtils.getDatesArray();
    this.fechasSelect = this.fechasIniciales.map(m=>new SelectFecha(m,FechaUtils.formatStrToStr2(m)));

    this.viajesService.obtenerDestinos().subscribe(resp=>{
   // this.viajesService.obtenerDestinoIntermedios().subscribe(resp=>{
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

  cargarDatosViajes(){

    if(this.fechaSeleccionada.trim().length==0){
      this.messageService.add({ severity: 'error', summary: 'Valores incorrecto', detail: 'la fecha es un valor obligatorio' });
      return;
    }



    this.viajesService.obtenerDatosViajeIntermedio(this.origenSeleccionado,this.destinoSeleccionado,this.fechaSeleccionada).subscribe(resp=>{
      if(resp.status==1){
        this.datosViaje=resp.result;
        this.origenDesabilitada=true;
        this.destinoDesabilitada=true;
        this.fechaDesabilitada=true;
        this.botonDesabilitado=true;
        this.activaTabla=true;
      }
    });
  }



}

import { Component, OnInit } from '@angular/core';
import { FechaUtils } from 'src/app/Utils/fecha-utils';
import { DatoViaje } from 'src/app/models/dato-viaje';
import { OrigenDestino } from 'src/app/models/origen-destino';
import { SelectFecha } from 'src/app/models/select-fecha';
import { ViajesService } from 'src/app/services/viajes.service';
import { MessageService } from 'primeng/api';
import { Reserva } from 'src/app/models/reserva';
import { Usuario } from 'src/app/models/usuario';
import { ReservaService } from 'src/app/services/reserva.service';
import { ReservaAsiento } from 'src/app/models/reserva-asiento';

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

  datosViaje:DatoViaje[]=[];
  datoViajeSeleccionado:DatoViaje=new DatoViaje();

  cantidades:number[]=[1];
  asientosOcupados:number[]=[];
  asientosSeleccionados:number[]=[];
  asientos:number[]=[];

  activaTabla:boolean=false;
  activaDetalle:boolean=false;
  activaSeleccion:boolean=false;

  fechaDesabilitada:boolean=false;
  origenDesabilitada:boolean=false;
  destinoDesabilitada:boolean=false;
  botonDesabilitado:boolean=false;
  cantidadDesabilitado:boolean=false;
  precioDesabilitado:boolean=false;
  totalDesabilitado:boolean=true;

  precioSeleccionado:number=0;
  cantidadSeleccionada:number=1;
  total:number=0;

  catidadAsientos:number=0;




  constructor(
    private viajesService:ViajesService,
    private messageService:MessageService,
    private reservaService:ReservaService
    ) {

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
          this.destinoSeleccionado=this.destinos[0];
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

  viajeSeleccionado(datoViaje:DatoViaje){
    this.datoViajeSeleccionado=datoViaje;
     this.activaTabla=false;
     this.activaDetalle=true;
     this.cantidades=[];
     for (let index = 1; index <= this.datoViajeSeleccionado.asientosLibre; index++) {
      this.cantidades.push(index);
     }

     for (let index = 1; index <= this.datoViajeSeleccionado.cantidadAsientos; index++) {
      this.asientos.push(index);
     }
  }

  cambioCanPre(){
    this.total=this.cantidadSeleccionada*this.precioSeleccionado;
  }

  seleccionarAsientos(){

    if(this.precioSeleccionado==0){
      this.messageService.add({ severity: 'error', summary: 'Valor incorrecto', detail: 'el precio es un valor obligatorio' });
      return;
    }

    this.cantidadDesabilitado=true;
    this.precioDesabilitado=true;
    this.activarSeleccionAsientos();
  }

  activarSeleccionAsientos(){
    this.viajesService.obtenerAsientos(this.datoViajeSeleccionado.viajeId).subscribe(resp=>{
        if(resp.status==1){
          this.asientosOcupados=resp.result;
          this.activaSeleccion=true;

        }
    });
  }


  cambioAsiento(event:any,asiento:number){
    const checkbox = event as HTMLInputElement;

    if(checkbox.checked){
      if(this.cantidadSeleccionada==this.asientosSeleccionados.length){
        checkbox.checked = !checkbox.checked;
        return;
      }
        this.asientosSeleccionados.push(asiento);

    }else{
      const indice = this.asientosSeleccionados.indexOf(asiento);
      this.asientosSeleccionados.splice(indice,1);
    }
    this.catidadAsientos=this.asientosSeleccionados.length;
  }

  guardarReserva(){
    if(this.cantidadSeleccionada!=this.asientosSeleccionados.length){
      this.messageService.add({ severity: 'error', summary: 'Valores incorrecto', detail: 'debes seleccionar todos los asientos' });
      return;
    }

    let reserva:Reserva=new Reserva();
    reserva.reservaId=0;

    let dataUsuario:string|null= localStorage.getItem("usuario");
    if(dataUsuario!=null){
      let usuario:Usuario=JSON.parse(dataUsuario);
      reserva.usuarioId=usuario.usuarioId;
    }

    if(this.origenSeleccionado=="Toluca"){
      reserva.subEscalaOrigen=1;
      reserva.subEscalaDestino=1;
    }
    else{
      reserva.subEscalaOrigen=2;
      reserva.subEscalaDestino=2;
    }

    reserva.cantidadAsientos=this.asientosSeleccionados.length;
    reserva.precio = this.precioSeleccionado;
    reserva.viajeId=this.datoViajeSeleccionado.viajeId;

    this.reservaService.guardarReserva(reserva).subscribe(resp=>{
      if(resp.status==1){
        this.asientosSeleccionados.forEach(f=>{
            let reservasAsiento:ReservaAsiento= new ReservaAsiento(resp.result.reservaId,f);
            this.reservaService.guardarAsiento(reservasAsiento).subscribe(s=>{});
        });

       this.messageService.add({ severity: 'success', summary: 'Guardo', detail: 'la reserva se guardo correctamente'});
       this.ngOnInit();
      }
    });


  }



}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConsultaReporte } from '../models/consulta-reporte'
import { MessageResponse } from '../models/message-response';
import { Observable } from 'rxjs';
import { ConsultaReserva } from '../models/consulta-reserva';
import { OrigenDestino } from '../models/origen-destino';
import { DatoViaje } from '../models/dato-viaje';

@Injectable({
  providedIn: 'root'
})
export class ViajesService {

  url:string="http://localhost:8091/api/";
  servicio:string="viajes";

  constructor(private httpClient:HttpClient ) {

  }

  obtenerReporte():Observable<MessageResponse<ConsultaReporte[]>>{
    return this.httpClient.get<MessageResponse<ConsultaReporte[]>>(this.url+this.servicio+'/reporte');
  }

  obtenerReserva():Observable<MessageResponse<ConsultaReserva[]>>{
    return this.httpClient.get<MessageResponse<ConsultaReserva[]>>(this.url+this.servicio+'/reserva');
  }

  obtenerDestinoIntermedios():Observable<MessageResponse<OrigenDestino[]>>{
    return this.httpClient.get<MessageResponse<OrigenDestino[]>>(this.url+this.servicio+'/obtenerOrigenDestiInt');
  }

  obtenerDestinos():Observable<MessageResponse<OrigenDestino[]>>{
    return this.httpClient.get<MessageResponse<OrigenDestino[]>>(this.url+this.servicio+'/obtenerOrigenDesti');
  }

  obtenerDatosViaje(origen:string,destino:string,fecha:string):Observable<MessageResponse<DatoViaje[]>>{
    return this.httpClient.get<MessageResponse<DatoViaje[]>>(this.url+this.servicio+`/obtenerDatosViajes/${origen}/${destino}/${fecha}`);
  }

  obtenerDatosViajeIntermedio(origen:string,destino:string,fecha:string):Observable<MessageResponse<DatoViaje[]>>{
    return this.httpClient.get<MessageResponse<DatoViaje[]>>(this.url+this.servicio+`/obtenerDatosViajesInt/${origen}/${destino}/${fecha}`);
  }



}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConsultaReserva } from '../models/consulta-reserva'
import { MessageResponse } from '../models/message-response';
import { Observable } from 'rxjs';
import { Reserva } from '../models/reserva';
import { ReservaAsiento } from '../models/reserva-asiento';

@Injectable({
  providedIn: 'root'

})

export class ReservaService {

  url:string="http://localhost:8091/api/";

  servicio:string="reserva";

  constructor(private httpClient:HttpClient ) {

  }
  obtenerReporte():Observable<MessageResponse<ConsultaReserva[]>>{
    return this.httpClient.get<MessageResponse<ConsultaReserva[]>>(this.url+this.servicio+'/reporte');
  }

  guardarReserva(reserva:Reserva):Observable<MessageResponse<Reserva>>{
    return this.httpClient.post<MessageResponse<Reserva>>(this.url+this.servicio,reserva);
  }

  guardarAsiento(reservaAsiento:ReservaAsiento):Observable<MessageResponse<any>>{
    return this.httpClient.post<MessageResponse<Reserva>>(this.url+"reservasasientos",reservaAsiento);
  }

}

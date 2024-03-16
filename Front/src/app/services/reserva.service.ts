import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConsultaReserva } from '../models/consulta-reserva'
import { MessageResponse } from '../models/message-response';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ViajesService {

  url:string="http://localhost:8091/api/";
  servicio:string="reserva";

  constructor(private httpClient:HttpClient ) { 
    
  }

  obtenerReporte():Observable<MessageResponse<ConsultaReserva[]>>{
    return this.httpClient.get<MessageResponse<ConsultaReserva[]>>(this.url+this.servicio+'/reporte');
  }


}

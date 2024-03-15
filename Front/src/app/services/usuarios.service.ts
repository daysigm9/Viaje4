import { Injectable, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { MessageResponse } from '../models/message-response';
import { Usuario } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService implements OnInit {

  url:string="http://localhost:8091/api/";
  servicio:string="usuarios";
  usuario!:Usuario;

  constructor(private httpClient:HttpClient ) { 
    
  }
  ngOnInit(): void {
  }

  autenticar(usuario:string,password:string):Observable<MessageResponse<Usuario>>{
    return this.httpClient.get<MessageResponse<Usuario>>(this.url+this.servicio+'/autenticar/'+usuario+'/'+password);
  }


}

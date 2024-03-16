import { Component, OnInit } from '@angular/core';
import { ConsultaReserva } from 'src/app/models/consulta-reserva';
import { ViajesService } from 'src/app/services/viajes.service';

@Component({
  selector: 'app-reserva',
  templateUrl: './reserva.component.html',
  styleUrls: ['./reserva.component.css']
})
export class ReservaComponent implements OnInit {


  consultasReserva:ConsultaReserva[]=[];

  constructor(private viajesService:ViajesService) {
    
  }

  ngOnInit(): void {
     this.viajesService.obtenerReserva().subscribe(resp=>{
    
       if(resp.status==1){
        this.consultasReserva=resp.result;
         
       }
       else{
         alert(resp.message);
       }
     });
  }
}

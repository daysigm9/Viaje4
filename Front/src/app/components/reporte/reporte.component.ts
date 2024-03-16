import { Component, OnInit } from '@angular/core';
import { ConsultaReporte } from 'src/app/models/consulta-reporte';
import { ViajesService } from 'src/app/services/viajes.service';

@Component({
  selector: 'app-reporte',
  templateUrl: './reporte.component.html',
  styleUrls: ['./reporte.component.css']
})
export class ReporteComponent implements OnInit {


  consultasReporte:ConsultaReporte[]=[];

  constructor(private viajesService:ViajesService) {
    
  }
  ngOnInit(): void {
    this.viajesService.obtenerReporte().subscribe(resp=>{
      if(resp.status==1){
        this.consultasReporte=resp.result;
      }
      else{
        alert(resp.message);
      }
    });
  }


}

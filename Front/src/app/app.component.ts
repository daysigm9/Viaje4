import { Component, OnInit } from '@angular/core';
import { UsuariosService } from './services/usuarios.service';
import { Usuario } from './models/usuario';
import { MenuItem } from 'primeng/api/menuitem';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'ADViajes';
  usuario:string="";
  password:string="";
  localUsuario:Usuario|undefined;
  inicioSesion:boolean=false;
  items: MenuItem[] | undefined;

  constructor(private usuarioService:UsuariosService) {

  }

  ngOnInit(): void {
    this.items = [
      {
        label: 'Reservar',
        icon: 'pi pi-fw pi-plus',
        routerLink:'reserva'
    },
    {
      label: 'Reservar Intermedio',
      icon: 'pi pi-fw pi-plus',
      routerLink:'reserva-intermedia'
  },
    {
      label: 'Importar Pasajeros',
      icon: 'pi pi-fw pi-trash',
      routerLink:''
  },
  {
          label: 'Reporte de Viajes',
          icon: 'pi pi-fw pi-trash',
          routerLink:'reporte'
        }
      ,
      {
              label: 'Grafica de Destinos',
              icon: 'pi pi-fw pi-trash',
              routerLink:'grafica'

          }
          ,
          {
                  label: 'Exportar datos pasajeros',
                  icon: 'pi pi-fw pi-trash',
                  routerLink:'genera-pdf'
          }
        ];
    let item:string|null=localStorage.getItem("usuario");
    if(item!=null){
      this.localUsuario=JSON.parse(item);
      this.inicioSesion=true;
    }else{
      this.inicioSesion=false;
    }
  }

  validaLogin(){
    if(this.usuario.trim().length==0){
      alert("El usuario es obligatorio");
      return;
    }

    if(this.password.trim().length==0){
      alert("El usuario es obligatorio");
      return;
    }


    this.usuarioService.autenticar(this.usuario,this.password).subscribe(response=>{
      if(response.status==1){
        this.localUsuario=response.result;
        localStorage.setItem("usuario",JSON.stringify(response.result));
        this.inicioSesion=true;
      }
      else{
        alert(response.message);
      }
    });
  }

  cerrar(){
    this.localUsuario=undefined;
    localStorage.removeItem("usuario");
    this.inicioSesion=false;
  }

}

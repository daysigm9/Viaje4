package com.agencia.viajes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agencia.viajes.common.MessageResponse;
import com.agencia.viajes.dto.DatoGraficaDTO;
import com.agencia.viajes.dto.DatosViajesDTO;
import com.agencia.viajes.dto.OrigenDestinoDTO;
import com.agencia.viajes.dto.ReservaAsientoDTO;
import com.agencia.viajes.dto.ReservaDTO;
import com.agencia.viajes.dto.ViajeConsultaDTO;
import com.agencia.viajes.dto.ViajeDTO;
import com.agencia.viajes.dto.ViajeReporte;
import com.agencia.viajes.service.ReservaAsientoService;
import com.agencia.viajes.service.ReservaService;
import com.agencia.viajes.service.ViajeService;

@CrossOrigin
@RestController
@RequestMapping("/api/viajes")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;
    
    @Autowired
    private ReservaService reservaService;
    
    @Autowired
    private ReservaAsientoService reservaAsientoService;

    @PostMapping
    public MessageResponse<ViajeDTO> crear(@RequestBody ViajeDTO viajeDTO) {
        return viajeService.crear(viajeDTO);
    }

    @GetMapping
    public MessageResponse<List<ViajeDTO>> obtenerTodos() {
        return viajeService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public MessageResponse<ViajeDTO> obtenerPorId(@PathVariable Integer id) {
        return viajeService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public MessageResponse<String> eliminar(@PathVariable Integer id) {
        return viajeService.eliminar(id);
    }

    @PutMapping("/{id}")
    public MessageResponse<ViajeDTO> actualizar(@PathVariable Integer id, @RequestBody ViajeDTO viajeDTO) {
        viajeDTO.setViajeId(id);
        return viajeService.actualizar(viajeDTO);
    }
    
    @GetMapping("/reporte")
    public MessageResponse<List<ViajeConsultaDTO>> findReportViajes() {
        MessageResponse<List<ViajeConsultaDTO>> message = viajeService.findReportViajes();
        return message;
    }

    @GetMapping("/obtenerOrigenDesti")
    public MessageResponse<List<OrigenDestinoDTO>> obtenerOrigenDesti() {
        MessageResponse<List<OrigenDestinoDTO>> message = viajeService.findOrigenDestino();
        return message;
    }

    @GetMapping("/obtenerOrigenDestiInt")
    public MessageResponse<List<OrigenDestinoDTO>> obtenerOrigenDestiInt() {
        MessageResponse<List<OrigenDestinoDTO>> message = viajeService.findOrigenDestinoInt();
        return message;
    }

    @GetMapping("/obtenerDatosViajes/{origen}/{destino}/{fecha}")
    public MessageResponse<List<DatosViajesDTO>> obtenerDatosViajes(@PathVariable String origen, @PathVariable String destino, @PathVariable String fecha) {
        MessageResponse<List<DatosViajesDTO>> message = viajeService.findDatosViajes(origen, destino,fecha);
        return message;
    }
    
    @GetMapping("/obtenerGrafica")
    public MessageResponse<List<DatoGraficaDTO>> obtenerGrafica() {
        MessageResponse<List<DatoGraficaDTO>> message = viajeService.findDatosGrafica();
        return message;
    }

    @GetMapping("/obtenerAsientosOcupados/{viajeId}")
    public MessageResponse<List<Integer>> obtenerAsientosOcupados(@PathVariable Integer viajeId) {
        MessageResponse<List<Integer>> message = viajeService.findAsientosOcupados(viajeId);
        return message;
    }
    
    @GetMapping("/obtenerDatosViajesInt/{origen}/{destino}/{fecha}")
    public MessageResponse<List<DatosViajesDTO>> obtenerDatosViajesInt(@PathVariable String origen, @PathVariable String destino, @PathVariable String fecha) {
        MessageResponse<List<DatosViajesDTO>> message = viajeService.findDatosViajesInt(origen, destino,fecha);
        return message;
    }
    
    @PostMapping("/crearreserva")
    public MessageResponse<ReservaDTO> crear(@RequestBody ReservaDTO reservaDTO) {
        return reservaService.crear(reservaDTO);
    }
    
    @PostMapping("/reservaasiento")
    public MessageResponse<ReservaAsientoDTO> crear(@RequestBody ReservaAsientoDTO reservaAsientoDTO) {
        return reservaAsientoService.crear(reservaAsientoDTO);
    }




    
}


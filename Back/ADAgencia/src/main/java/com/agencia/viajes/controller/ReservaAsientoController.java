package com.agencia.viajes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agencia.viajes.common.MessageResponse;
import com.agencia.viajes.dto.ReservaAsientoDTO;
import com.agencia.viajes.service.ReservaAsientoService;

@RestController
@RequestMapping("/api/reservas-asientos")
public class ReservaAsientoController {

    @Autowired
    private ReservaAsientoService reservaAsientoService;

    @PostMapping
    public MessageResponse<ReservaAsientoDTO> crear(@RequestBody ReservaAsientoDTO reservaAsientoDTO) {
        return reservaAsientoService.crear(reservaAsientoDTO);
    }

    @GetMapping
    public MessageResponse<List<ReservaAsientoDTO>> obtenerTodos() {
        return reservaAsientoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public MessageResponse<ReservaAsientoDTO> obtenerPorId(@PathVariable Integer id) {
        return reservaAsientoService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public MessageResponse<String> eliminar(@PathVariable Integer id) {
        return reservaAsientoService.eliminar(id);
    }

    @PutMapping("/{id}")
    public MessageResponse<ReservaAsientoDTO> actualizar(@PathVariable Integer id, @RequestBody ReservaAsientoDTO reservaAsientoDTO) {
        reservaAsientoDTO.setIdReservaAsientos(id);
        return reservaAsientoService.actualizar(reservaAsientoDTO);
    }

}


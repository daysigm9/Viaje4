package com.agencia.viajes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.agencia.viajes.dto.ReservaDTO;
import com.agencia.viajes.service.ReservaService;

@CrossOrigin
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public MessageResponse<ReservaDTO> crear(@RequestBody ReservaDTO reservaDTO) {
        return reservaService.crear(reservaDTO);
    }

    @GetMapping
    public MessageResponse<List<ReservaDTO>> obtenerTodos() {
        return reservaService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public MessageResponse<ReservaDTO> obtenerPorId(@PathVariable Integer id) {
        return reservaService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public MessageResponse<String> eliminar(@PathVariable Integer id) {
        return reservaService.eliminar(id);
    }

    @PutMapping("/{id}")
    public MessageResponse<ReservaDTO> actualizar(@PathVariable Integer id, @RequestBody ReservaDTO reservaDTO) {
        reservaDTO.setReservaId(id);
        return reservaService.actualizar(reservaDTO);
    }

}

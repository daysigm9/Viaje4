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
import com.agencia.viajes.dto.EscalaDTO;
import com.agencia.viajes.service.EscalaService;

@CrossOrigin
@RestController
@RequestMapping("/api/escalas")
public class EscalaController {

    @Autowired
    private EscalaService escalaService;

    @PostMapping
    public MessageResponse<EscalaDTO> crear(@RequestBody EscalaDTO escalaDTO) {
        return escalaService.crear(escalaDTO);
    }

    @GetMapping
    public MessageResponse<List<EscalaDTO>> obtenerTodos() {
        return escalaService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public MessageResponse<EscalaDTO> obtenerPorId(@PathVariable Integer id) {
        return escalaService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public MessageResponse<String> eliminar(@PathVariable Integer id) {
        return escalaService.eliminar(id);
    }

    @PutMapping("/{id}")
    public MessageResponse<EscalaDTO> actualizar(@PathVariable Integer id, @RequestBody EscalaDTO escalaDTO) {
        escalaDTO.setEscalaId(id);
        return escalaService.actualizar(escalaDTO);
    }
}

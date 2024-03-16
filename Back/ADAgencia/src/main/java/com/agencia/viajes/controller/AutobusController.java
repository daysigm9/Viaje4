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

import com.agencia.viajes.dto.AutobusDTO;
import com.agencia.viajes.service.AutobusService;

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
import com.agencia.viajes.dto.AutobusDTO;
import com.agencia.viajes.service.AutobusService;

@CrossOrigin
@RestController
@RequestMapping("/api/autobuses")
public class AutobusController {

    @Autowired
    private AutobusService autobusService;

    @PostMapping
    public MessageResponse<AutobusDTO> crear(@RequestBody AutobusDTO autobusDTO) {
        return autobusService.crear(autobusDTO);
    }

    @GetMapping
    public MessageResponse<List<AutobusDTO>> obtenerTodos() {
        return autobusService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public MessageResponse<AutobusDTO> obtenerPorId(@PathVariable Integer id) {
        return autobusService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public MessageResponse<String> eliminar(@PathVariable Integer id) {
        return autobusService.eliminar(id);
    }

    @PutMapping("/{id}")
    public MessageResponse<AutobusDTO> actualizar(@PathVariable Integer id, @RequestBody AutobusDTO autobusDTO) {
        autobusDTO.setAutobusId(id);
        return autobusService.actualizar(autobusDTO);
    }
}

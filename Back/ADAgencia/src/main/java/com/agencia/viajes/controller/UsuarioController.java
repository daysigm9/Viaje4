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
import com.agencia.viajes.dto.UsuarioDTO;
import com.agencia.viajes.service.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public MessageResponse<UsuarioDTO> crear(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.crear(usuarioDTO);
    }

    @GetMapping
    public MessageResponse<List<UsuarioDTO>> obtenerTodos() {
    	MessageResponse<List<UsuarioDTO>> usuarios=usuarioService.obtenerTodos();
        return usuarios;
    }

    @GetMapping("/{id}")
    public MessageResponse<UsuarioDTO> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public MessageResponse<String> eliminar(@PathVariable Long id) {
        return usuarioService.eliminar(id);
    }
    
    @PutMapping("/{id}")
    public MessageResponse<UsuarioDTO> actualizar(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO.setUsuarioId(id);
        return usuarioService.actualizar(usuarioDTO);
    }
    
    @GetMapping("/autenticar/{usuario}/{password}")
    public MessageResponse<UsuarioDTO> obtenerPorId(@PathVariable String usuario,@PathVariable String password) {
        return usuarioService.authenticar(usuario, password);
    }
    
    
    
}

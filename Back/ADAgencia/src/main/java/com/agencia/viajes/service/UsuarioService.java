package com.agencia.viajes.service;

import java.util.List;

import com.agencia.viajes.common.MessageResponse;
import com.agencia.viajes.dto.UsuarioDTO;

public interface UsuarioService {
	MessageResponse<UsuarioDTO> crear(UsuarioDTO usuarioDTO);

	MessageResponse<UsuarioDTO> obtenerPorId(Long id);

	MessageResponse<List<UsuarioDTO>> obtenerTodos();

    MessageResponse<String> eliminar(Long id);

    MessageResponse<UsuarioDTO> actualizar(UsuarioDTO usuarioDTO);
    
    MessageResponse<UsuarioDTO> authenticar(String correo,String password);
 }

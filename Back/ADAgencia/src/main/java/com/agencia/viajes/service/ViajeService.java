package com.agencia.viajes.service;

import java.util.List;

import com.agencia.viajes.common.MessageResponse;
import com.agencia.viajes.dto.ViajeDTO;

public interface ViajeService {

    MessageResponse<ViajeDTO> crear(ViajeDTO viajeDTO);

    MessageResponse<ViajeDTO> obtenerPorId(Integer id);

    MessageResponse<List<ViajeDTO>> obtenerTodos();

    MessageResponse<String> eliminar(Integer id);

    MessageResponse<ViajeDTO> actualizar(ViajeDTO viajeDTO);

}
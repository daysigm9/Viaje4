package com.agencia.viajes.service;

import java.util.List;

import com.agencia.viajes.common.MessageResponse;
import com.agencia.viajes.dto.EscalaDTO;

public interface EscalaService {

    MessageResponse<EscalaDTO> crear(EscalaDTO escalaDTO);

    MessageResponse<EscalaDTO> obtenerPorId(Integer id);

    MessageResponse<List<EscalaDTO>> obtenerTodos();

    MessageResponse<String> eliminar(Integer id);

    MessageResponse<EscalaDTO> actualizar(EscalaDTO escalaDTO);

}

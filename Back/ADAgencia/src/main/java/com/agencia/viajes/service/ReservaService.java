package com.agencia.viajes.service;

import java.util.List;

import com.agencia.viajes.common.MessageResponse;
import com.agencia.viajes.dto.ReservaDTO;
import com.agencia.viajes.models.Reserva;

public interface ReservaService {

    MessageResponse<ReservaDTO> crear(ReservaDTO reservaDTO);

    MessageResponse<ReservaDTO> obtenerPorId(Integer id);

    MessageResponse<List<ReservaDTO>> obtenerTodos();

    MessageResponse<String> eliminar(Integer id);

    MessageResponse<ReservaDTO> actualizar(ReservaDTO reservaDTO);

}

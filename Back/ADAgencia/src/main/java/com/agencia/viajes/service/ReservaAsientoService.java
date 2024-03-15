package com.agencia.viajes.service;

import java.util.List;

import com.agencia.viajes.common.MessageResponse;
import com.agencia.viajes.dto.ReservaAsientoDTO;

public interface ReservaAsientoService {

    MessageResponse<ReservaAsientoDTO> crear(ReservaAsientoDTO reservaAsientoDTO);

    MessageResponse<ReservaAsientoDTO> obtenerPorId(Integer id);

    MessageResponse<List<ReservaAsientoDTO>> obtenerTodos();

    MessageResponse<String> eliminar(Integer id);

    MessageResponse<ReservaAsientoDTO> actualizar(ReservaAsientoDTO reservaAsientoDTO);

}

package com.agencia.viajes.service;

import java.util.List;

import com.agencia.viajes.common.MessageResponse;
import com.agencia.viajes.dto.AutobusDTO;

public interface AutobusService {

    MessageResponse<AutobusDTO> crear(AutobusDTO autobusDTO);

    MessageResponse<AutobusDTO> obtenerPorId(Integer id);

    MessageResponse<List<AutobusDTO>> obtenerTodos();

    MessageResponse<String> eliminar(Integer id);

    MessageResponse<AutobusDTO> actualizar(AutobusDTO autobusDTO);

}

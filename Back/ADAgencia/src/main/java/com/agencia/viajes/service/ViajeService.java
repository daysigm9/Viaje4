package com.agencia.viajes.service;

import java.util.List;

import com.agencia.viajes.common.MessageResponse;
import com.agencia.viajes.dto.DatosViajesDTO;
import com.agencia.viajes.dto.OrigenDestinoDTO;
import com.agencia.viajes.dto.ViajeConsultaDTO;
import com.agencia.viajes.dto.ViajeDTO;
import com.agencia.viajes.dto.ViajeReporte;

public interface ViajeService {

    MessageResponse<ViajeDTO> crear(ViajeDTO viajeDTO);

    MessageResponse<ViajeDTO> obtenerPorId(Integer id);

    MessageResponse<List<ViajeDTO>> obtenerTodos();

    MessageResponse<String> eliminar(Integer id);

    MessageResponse<ViajeDTO> actualizar(ViajeDTO viajeDTO);
    
    MessageResponse<List<ViajeConsultaDTO>> findReportViajes();
    MessageResponse<List<OrigenDestinoDTO>> findOrigenDestino();
    MessageResponse<List<DatosViajesDTO>> findDatosViajes(String origen,String destino,String fecha);
}
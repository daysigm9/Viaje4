package com.agencia.viajes.services.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agencia.viajes.common.MessageResponse;
import com.agencia.viajes.dto.OrigenDestinoDTO;
import com.agencia.viajes.dto.ViajeConsultaDTO;
import com.agencia.viajes.dto.ViajeDTO;
import com.agencia.viajes.dto.ViajeReporte;
import com.agencia.viajes.models.Viaje;
import com.agencia.viajes.repositories.ViajeRepository;
import com.agencia.viajes.service.ViajeService;

@Service
public class ViajeServiceImpl implements ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public MessageResponse<ViajeDTO> crear(ViajeDTO viajeDTO) {
        Viaje viaje = modelMapper.map(viajeDTO, Viaje.class);
        MessageResponse<ViajeDTO> message = new MessageResponse<>();
        try {
            viaje = viajeRepository.save(viaje);
            viajeDTO.setViajeId(viaje.getViajeId());
            message.setMessage("");
            message.setStatus(1);
            message.setResult(viajeDTO);
        } catch (Exception e) {
            message.setMessage("No fue posible crear el viaje.");
            message.setStatus(0);
        }
        return message;
    }

    @Override
    public MessageResponse<ViajeDTO> obtenerPorId(Integer id) {
        Viaje viaje = viajeRepository.findById(id).orElse(null);
        MessageResponse<ViajeDTO> message = new MessageResponse<>();
        if (viaje != null) {
            message.setStatus(1);
            message.setResult(modelMapper.map(viaje, ViajeDTO.class));
        } else {
            message.setStatus(0);
            message.setMessage("El viaje que buscas no existe");
        }
        return message;
    }

    @Override
    public MessageResponse<List<ViajeDTO>> obtenerTodos() {
        MessageResponse<List<ViajeDTO>> message = new MessageResponse<>();
        List<Viaje> viajes = viajeRepository.findAll();
        List<ViajeDTO> viajesDTO = new ArrayList<>();
        for (Viaje viaje : viajes) {
            viajesDTO.add(modelMapper.map(viaje, ViajeDTO.class));
        }
        message.setStatus(1);
        message.setResult(viajesDTO);
        return message;
    }

    @Override
    public MessageResponse<String> eliminar(Integer id) {
        MessageResponse<String> message = new MessageResponse<>();
        try {
            viajeRepository.delete(viajeRepository.findById(id).orElse(null));
            message.setStatus(1);
        } catch (Exception e) {
            message.setStatus(0);
            message.setMessage("No fue posible eliminar el viaje");
        }
        return message;
    }

    @Override
    public MessageResponse<ViajeDTO> actualizar(ViajeDTO viajeDTO) {
        MessageResponse<ViajeDTO> message = new MessageResponse<>();
        Viaje viaje = modelMapper.map(viajeDTO, Viaje.class);
        try {
            viaje = viajeRepository.save(viaje);
            viajeDTO.setViajeId(viaje.getViajeId());
            message.setStatus(1);
            message.setMessage("");
            message.setResult(viajeDTO);
        } catch (Exception e) {
            message.setStatus(0);
            message.setMessage("No fue posible modificar el viaje");
        }
        return message;
    }
    
    @Override
    public MessageResponse<List<ViajeConsultaDTO>> findReportViajes() {
        MessageResponse message = new MessageResponse<>();
        List<Map<String, Object>> resultList = viajeRepository.getDataAsMap();
        List<ViajeConsultaDTO> viajeConsulta = new ArrayList<>();
        for (Map<String, Object> result : resultList) {
        	ViajeConsultaDTO viajeReporte = new ViajeConsultaDTO();
            viajeReporte.setIdViaje((Integer) result.get("idViaje"));
            String str=(String)result.get("origen");
            viajeReporte.setOrigen(str);
            str=(String)result.get("destino");
            viajeReporte.setDestino(str);
            viajeReporte.setImporte(((BigDecimal)result.get("Importe")).doubleValue());
            viajeReporte.setFechaSalida(((Timestamp) result.get("FechaSalida")).toLocalDateTime());
            viajeReporte.setPasajeros((Integer) result.get("Pasajeros"));
            viajeConsulta.add(viajeReporte);
        }
        message.setStatus(1);
        message.setResult(viajeConsulta);
        return message;
    }

	@Override
	public MessageResponse<List<OrigenDestinoDTO>> findOrigenDestino() {
        MessageResponse message = new MessageResponse<>();
        List<Map<String, Object>> resultList = viajeRepository.getOrigenDestinoAsMap();
        List<OrigenDestinoDTO> listaOriDest = new ArrayList<>();
        for (Map<String, Object> result : resultList) {
        	OrigenDestinoDTO origenDestino = new OrigenDestinoDTO();
            origenDestino.setOrigen((String) result.get("Origen"));
            origenDestino.setDestino((String) result.get("Destino"));
            listaOriDest.add(origenDestino);
        }
        message.setStatus(1);
        message.setResult(listaOriDest);
        return message;
	}}
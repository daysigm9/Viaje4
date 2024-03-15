package com.agencia.viajes.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agencia.viajes.common.MessageResponse;
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
            viajeReporte.setOrigen((String)result.get("Origen"));
            viajeReporte.setOrigen((String)result.get("Destino"));
            //viajeReporte.setImporte((Double)result.get("Importe"));
            //viajeReporte.setFechaSalida((String) result.get("FechaSalida"));
            viajeReporte.setImporte(100d);
            viajeReporte.setFechaSalida("01-01-2024 10:20");
            viajeReporte.setPasajeros((Integer) result.get("Pasajeros"));
            viajeConsulta.add(viajeReporte);
        }
        message.setResult(viajeConsulta);
        return message;
    }}
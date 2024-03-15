package com.agencia.viajes.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agencia.viajes.common.MessageResponse;
import com.agencia.viajes.dto.AutobusDTO;
import com.agencia.viajes.models.Autobus;
import com.agencia.viajes.repositories.AutobusRepository;
import com.agencia.viajes.service.AutobusService;

@Service
public class AutobusServiceImpl implements AutobusService {

    @Autowired
    private AutobusRepository autobusRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public MessageResponse<AutobusDTO> crear(AutobusDTO autobusDTO) {
        Autobus autobus = modelMapper.map(autobusDTO, Autobus.class);
        MessageResponse<AutobusDTO> message = new MessageResponse<>();
        try {
            autobus = autobusRepository.save(autobus);
            autobusDTO.setAutobusId(autobus.getAutobusId());
            message.setMessage("");
            message.setStatus(1);
            message.setResult(autobusDTO);
        } catch (Exception e) {
            message.setMessage("No fue posible crear el autobús.");
            message.setStatus(0);
        }
        return message;
    }

    @Override
    public MessageResponse<AutobusDTO> obtenerPorId(Integer id) {
        Autobus autobus = autobusRepository.findById(id).orElse(null);
        MessageResponse<AutobusDTO> message = new MessageResponse<>();
        if (autobus != null) {
            message.setStatus(1);
            message.setResult(modelMapper.map(autobus, AutobusDTO.class));
        } else {
            message.setStatus(0);
            message.setMessage("El autobús que buscas no existe");
        }
        return message;
    }

    @Override
    public MessageResponse<List<AutobusDTO>> obtenerTodos() {
        MessageResponse<List<AutobusDTO>> message = new MessageResponse<>();
        List<Autobus> autobuses = autobusRepository.findAll();
        List<AutobusDTO> autobusesDTO = new ArrayList<>();
        for (Autobus autobus : autobuses) {
            autobusesDTO.add(modelMapper.map(autobus, AutobusDTO.class));
        }
        message.setStatus(1);
        message.setResult(autobusesDTO);
        return message;
    }

    @Override
    public MessageResponse<String> eliminar(Integer id) {
        MessageResponse<String> message = new MessageResponse<>();
        try {
            autobusRepository.delete(autobusRepository.findById(id).orElse(null));
            message.setStatus(1);
        } catch (Exception e) {
            message.setStatus(0);
            message.setMessage("No fue posible eliminar el autobús");
        }
        return message;
    }

    @Override
    public MessageResponse<AutobusDTO> actualizar(AutobusDTO autobusDTO) {
        MessageResponse<AutobusDTO> message = new MessageResponse<>();
        Autobus autobus = modelMapper.map(autobusDTO, Autobus.class);
        try {
            autobus = autobusRepository.save(autobus);
            autobusDTO.setAutobusId(autobus.getAutobusId());
            message.setStatus(1);
            message.setMessage("");
            message.setResult(autobusDTO);
        } catch (Exception e) {
            message.setStatus(0);
            message.setMessage("No fue posible modificar el autobús");
        }
        return message;
    }
}

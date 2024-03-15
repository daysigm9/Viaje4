package com.agencia.viajes.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agencia.viajes.common.MessageResponse;
import com.agencia.viajes.dto.EscalaDTO;
import com.agencia.viajes.models.Escala;
import com.agencia.viajes.repositories.EscalaRepository;
import com.agencia.viajes.service.EscalaService;

@Service
public class EscalaServiceImpl implements EscalaService {

    @Autowired
    private EscalaRepository escalaRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public MessageResponse<EscalaDTO> crear(EscalaDTO escalaDTO) {
        Escala escala = modelMapper.map(escalaDTO, Escala.class);
        MessageResponse<EscalaDTO> message = new MessageResponse<>();
        try {
            escala = escalaRepository.save(escala);
            escalaDTO.setEscalaId(escala.getEscalaId());
            message.setMessage("");
            message.setStatus(1);
            message.setResult(escalaDTO);
        } catch (Exception e) {
            message.setMessage("No fue posible crear la escala.");
            message.setStatus(0);
        }
        return message;
    }

    @Override
    public MessageResponse<EscalaDTO> obtenerPorId(Integer id) {
        Escala escala = escalaRepository.findById(id).orElse(null);
        MessageResponse<EscalaDTO> message = new MessageResponse<>();
        if (escala != null) {
            message.setStatus(1);
            message.setResult(modelMapper.map(escala, EscalaDTO.class));
        } else {
            message.setStatus(0);
            message.setMessage("La escala que buscas no existe");
        }
        return message;
    }

    @Override
    public MessageResponse<List<EscalaDTO>> obtenerTodos() {
        MessageResponse<List<EscalaDTO>> message = new MessageResponse<>();
        List<Escala> escalas = escalaRepository.findAll();
        List<EscalaDTO> escalasDTO = new ArrayList<>();
        for (Escala escala : escalas) {
            escalasDTO.add(modelMapper.map(escala, EscalaDTO.class));
        }
        message.setStatus(1);
        message.setResult(escalasDTO);
        return message;
    }

    @Override
    public MessageResponse<String> eliminar(Integer id) {
        MessageResponse<String> message = new MessageResponse<>();
        try {
            escalaRepository.delete(escalaRepository.findById(id).orElse(null));
            message.setStatus(1);
        } catch (Exception e) {
            message.setStatus(0);
            message.setMessage("No fue posible eliminar la escala");
        }
        return message;
    }

    @Override
    public MessageResponse<EscalaDTO> actualizar(EscalaDTO escalaDTO) {
        MessageResponse<EscalaDTO> message = new MessageResponse<>();
        Escala escala = modelMapper.map(escalaDTO, Escala.class);
        try {
            escala = escalaRepository.save(escala);
            escalaDTO.setEscalaId(escala.getEscalaId());
            message.setStatus(1);
            message.setMessage("");
            message.setResult(escalaDTO);
        } catch (Exception e) {
            message.setStatus(0);
            message.setMessage("No fue posible modificar la escala");
        }
        return message;
    }
}
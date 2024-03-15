package com.agencia.viajes.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agencia.viajes.common.MessageResponse;
import com.agencia.viajes.dto.ReservaDTO;
import com.agencia.viajes.models.Reserva;
import com.agencia.viajes.repositories.ReservaRepository;
import com.agencia.viajes.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agencia.viajes.models.Reserva;
import com.agencia.viajes.repositories.ReservaRepository;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public MessageResponse<ReservaDTO> crear(ReservaDTO reservaDTO) {
        Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
        MessageResponse<ReservaDTO> message = new MessageResponse<>();
        try {
            reserva = reservaRepository.save(reserva);
            reservaDTO.setReservaId(reserva.getReservaId());
            message.setMessage("");
            message.setStatus(1);
            message.setResult(reservaDTO);
        } catch (Exception e) {
            message.setMessage("No fue posible crear la reserva.");
            message.setStatus(0);
        }
        return message;
    }

    @Override
    public MessageResponse<ReservaDTO> obtenerPorId(Integer id) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        MessageResponse<ReservaDTO> message = new MessageResponse<>();
        if (reserva != null) {
            message.setStatus(1);
            message.setResult(modelMapper.map(reserva, ReservaDTO.class));
        } else {
            message.setStatus(0);
            message.setMessage("La reserva que buscas no existe");
        }
        return message;
    }

    @Override
    public MessageResponse<List<ReservaDTO>> obtenerTodos() {
        MessageResponse<List<ReservaDTO>> message = new MessageResponse<>();
        List<Reserva> reservas = reservaRepository.findAll();
        List<ReservaDTO> reservasDTO = new ArrayList<>();
        for (Reserva reserva : reservas) {
            reservasDTO.add(modelMapper.map(reserva, ReservaDTO.class));
        }

        message.setStatus(1);
        message.setResult(reservasDTO);
        return message;
    }

    @Override
    public MessageResponse<String> eliminar(Integer id) {

        MessageResponse<String> message = new MessageResponse<>();
        try {
            reservaRepository.delete(reservaRepository.findById(id).orElse(null));
            message.setStatus(1);
        } catch (Exception e) {
            message.setStatus(0);
            message.setMessage("No fue posible eliminar la reserva");
        }
        return message;
    }

    @Override
    public MessageResponse<ReservaDTO> actualizar(ReservaDTO reservaDTO) {
        MessageResponse<ReservaDTO> message = new MessageResponse<>();
        Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
        try {
            reserva = reservaRepository.save(reserva);
            reservaDTO.setReservaId(reserva.getReservaId());
            message.setStatus(1);
            message.setMessage("");
            message.setResult(reservaDTO);
        } catch (Exception e) {
            message.setStatus(0);
            message.setMessage("No fue posible modificar la reserva");
        }
        return message;
    }

}
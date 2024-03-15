package com.agencia.viajes.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agencia.viajes.common.MessageResponse;
import com.agencia.viajes.dto.ReservaAsientoDTO;
import com.agencia.viajes.models.ReservaAsiento;
import com.agencia.viajes.repositories.ReservaAsientoRepository;
import com.agencia.viajes.service.ReservaAsientoService;

@Service
public class ReservaAsientoServiceImpl implements ReservaAsientoService {

    @Autowired
    private ReservaAsientoRepository reservaAsientoRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public MessageResponse<ReservaAsientoDTO> crear(ReservaAsientoDTO reservaAsientoDTO) {
        ReservaAsiento reservaAsiento = modelMapper.map(reservaAsientoDTO, ReservaAsiento.class);
        MessageResponse<ReservaAsientoDTO> message = new MessageResponse<>();
        try {
            // Validar si el asiento está disponible para la reserva
            if (isAsientoDisponible(reservaAsientoDTO.getReservaId(), reservaAsientoDTO.getAsiento())) {
                reservaAsiento = reservaAsientoRepository.save(reservaAsiento);
                reservaAsientoDTO.setIdReservaAsientos(reservaAsiento.getIdReservaAsientos());
                message.setMessage("Asiento reservado correctamente.");
                message.setStatus(1);
                message.setResult(reservaAsientoDTO);
            } else {
                message.setMessage("El asiento no está disponible para la reserva.");
                message.setStatus(0);
            }
        } catch (Exception e) {
            message.setMessage("No fue posible crear la reserva del asiento.");
            message.setStatus(0);
        }
        return message;
    }

    @Override
    public MessageResponse<ReservaAsientoDTO> obtenerPorId(Integer id) {
        ReservaAsiento reservaAsiento = reservaAsientoRepository.findById(id).orElse(null);
        MessageResponse<ReservaAsientoDTO> message = new MessageResponse<>();
        if (reservaAsiento != null) {
            message.setStatus(1);
            message.setResult(modelMapper.map(reservaAsiento, ReservaAsientoDTO.class));
        } else {
            message.setStatus(0);
            message.setMessage("La reserva del asiento que buscas no existe");
        }
        return message;
    }

    @Override
    public MessageResponse<List<ReservaAsientoDTO>> obtenerTodos() {
        MessageResponse<List<ReservaAsientoDTO>> message = new MessageResponse<>();
        List<ReservaAsiento> reservasAsiento = reservaAsientoRepository.findAll();
        List<ReservaAsientoDTO> reservasAsientoDTO = new ArrayList<>();
        for (ReservaAsiento reservaAsiento : reservasAsiento) {
            reservasAsientoDTO.add(modelMapper.map(reservaAsiento, ReservaAsientoDTO.class));
        }

        message.setStatus(1);
        message.setResult(reservasAsientoDTO);
        return message;
    }

    @Override
    public MessageResponse<String> eliminar(Integer id) {

        MessageResponse<String> message = new MessageResponse<>();
        try {
            reservaAsientoRepository.deleteById(id);
            message.setStatus(1);
            message.setMessage("Asiento liberado correctamente.");
        } catch (Exception e) {
            message.setStatus(0);
            message.setMessage("No fue posible liberar el asiento.");
        }
        return message;
    }

    @Override
    public MessageResponse<ReservaAsientoDTO> actualizar(ReservaAsientoDTO reservaAsientoDTO) {
        MessageResponse<ReservaAsientoDTO> message = new MessageResponse<>();
        ReservaAsiento reservaAsiento = modelMapper.map(reservaAsientoDTO, ReservaAsiento.class);
        try {
            // Validar si el asiento está disponible para la reserva
            if (isAsientoDisponible(reservaAsientoDTO.getReservaId(), reservaAsientoDTO.getAsiento())) {
                reservaAsiento = reservaAsientoRepository.save(reservaAsiento);
                reservaAsientoDTO.setIdReservaAsientos(reservaAsiento.getIdReservaAsientos());
                message.setMessage("Asiento actualizado correctamente.");
                message.setStatus(1);
                message.setResult(reservaAsientoDTO);
            } else {
                message.setMessage("El asiento no está disponible para la reserva.");
                message.setStatus(0);
            }
        } catch (Exception e) {
            message.setMessage("No fue posible actualizar la reserva del asiento.");
            message.setStatus(0);
        }
        return message;
    }

    private boolean isAsientoDisponible(Integer reservaId, Integer asiento) {
        // Implementar la lógica para verificar si el asiento está disponible para la reserva
        // Esta lógica puede consultar otras entidades o APIs para obtener la información
        return true;
    }

}

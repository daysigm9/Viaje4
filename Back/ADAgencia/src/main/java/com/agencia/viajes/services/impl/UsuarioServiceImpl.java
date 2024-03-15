package com.agencia.viajes.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agencia.viajes.common.MessageResponse;
import com.agencia.viajes.dto.UsuarioDTO;
import com.agencia.viajes.models.Usuario;
import com.agencia.viajes.repositories.UsuarioRepository;
import com.agencia.viajes.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public MessageResponse<UsuarioDTO> crear(UsuarioDTO usuarioDTO) {
		Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
		MessageResponse<UsuarioDTO> message = new MessageResponse<>();
		try {
			usuario = usuarioRepository.save(usuario);
			usuarioDTO.setUsuarioId(usuario.getUsuarioId());
			message.setMessage("");
			message.setStatus(1);
			message.setResult(usuarioDTO);
		} catch (Exception e) {
			message.setMessage("No fue posible modificar el usuario.");
			message.setStatus(0);
		}
		return message;
	}

	@Override
	public MessageResponse<UsuarioDTO> obtenerPorId(Long id) {
		Usuario usuario = usuarioRepository.findById(id).orElse(null);
		MessageResponse<UsuarioDTO> message = new MessageResponse<>();
		if (usuario != null) {
			message.setStatus(1);
			message.setResult(modelMapper.map(usuario, UsuarioDTO.class));
		} else {
			message.setStatus(0);
			message.setMessage("El usuario que buscas no existe");
		}
		return message;
	}

	@Override
	public MessageResponse<List<UsuarioDTO>> obtenerTodos() {
		MessageResponse<List<UsuarioDTO>> message = new MessageResponse<>();
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			usuariosDTO.add(modelMapper.map(usuario, UsuarioDTO.class));
		}

		message.setStatus(1);
		message.setResult(usuariosDTO);
		return message;
	}

	@Override
	public MessageResponse<String> eliminar(Long id) {

		MessageResponse<String> message = new MessageResponse<>();
		try {
			usuarioRepository.delete(usuarioRepository.findById(id).orElse(null));
			message.setStatus(1);
		} catch (Exception e) {
			message.setStatus(0);
			message.setMessage("No fue posible eliminar el usuario");
		}
		return message;
	}

	@Override
	public MessageResponse<UsuarioDTO> actualizar(UsuarioDTO usuarioDTO) {
		MessageResponse<UsuarioDTO> message = new MessageResponse<>();
		Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
		try {
			usuario = usuarioRepository.save(usuario);
			usuarioDTO.setUsuarioId(usuario.getUsuarioId());
			message.setStatus(1);
			message.setMessage("");
			message.setResult(usuarioDTO);
		} catch (Exception e) {
			message.setStatus(0);
			message.setMessage("No fue posible modificar el usurio");
		}
		return message;
	}

	@Override
	public MessageResponse<UsuarioDTO> authenticar(String correo, String password) {
		MessageResponse<List<UsuarioDTO>> messageUser = this.obtenerTodos();
		MessageResponse<UsuarioDTO> message = new MessageResponse<>();
		if (messageUser.getStatus() == 1) {
			
			List<UsuarioDTO> listaUsuarios = messageUser.getResult().stream().filter(f -> {
				return f.getEmail().equals(correo) && f.getPassword().equals(password);
			}).collect(Collectors.toList());
			
			if (listaUsuarios.size()>0) {
				message.setStatus(1);
				message.setResult(listaUsuarios.get(0));
			} else {
				message.setStatus(0);
				message.setMessage("El usuario o contraseña es incorrecta");
			}
		} else {
			message.setStatus(0);
			message.setMessage("No fue posible cargar usuarios");
		}
		return message;
	}
}

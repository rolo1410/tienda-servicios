package com.todouno.prueba.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.todouno.prueba.models.Usuario;
import com.todouno.prueba.models.UsuarioRol;
import com.todouno.prueba.repository.UsuarioRepository;
import com.todouno.prueba.repository.UsuarioRolRepository;

@ApplicationScoped
public class UsuarioServices {

	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	private UsuarioRolRepository usuarioRolRepository;

	public Usuario crearActualizarUsuario(Usuario usuario) {
		return usuarioRepository.crearActualizarUsuario(usuario);
	}

	public Usuario crear(Usuario usuario) {
		return usuarioRepository.crearActualizarUsuario(usuario);
	}

	public Usuario actualizar(Usuario usuario) {
		return usuarioRepository.crearActualizarUsuario(usuario);
	}

	public Usuario obtenerUsuario(String usuario) {
		return usuarioRepository.obtenerUsuarioPorUsuario(usuario);
	}

	public Boolean exiteUsuarioEnRol(UsuarioRol usuarioRol) {
		return usuarioRolRepository.existeUsuarioEnRol(usuarioRol);
	}

	public UsuarioRol crearActualizarUsuarioRol(UsuarioRol usuarioRol) {
		return usuarioRolRepository.crearActualizar(usuarioRol);
	}

}

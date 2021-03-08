package com.todouno.prueba.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

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

	@Transactional
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

	public List<Usuario> obtenerUsuariosActivos() {
		return usuarioRepository.obtenerUsuariosActivos();
	}

	public Boolean exiteUsuarioEnRol(UsuarioRol usuarioRol) {
		return usuarioRolRepository.existeUsuarioEnRol(usuarioRol);
	}

	public UsuarioRol crearActualizarUsuarioRol(UsuarioRol usuarioRol) {
		return usuarioRolRepository.crearActualizar(usuarioRol);
	}

}

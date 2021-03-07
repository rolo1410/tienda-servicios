package com.todouno.prueba.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.todouno.prueba.models.UsuarioRol;
import com.todouno.prueba.utils.Accion;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UsuarioRolRepository implements PanacheRepository<UsuarioRol> {

	@Inject
	EntityManager em;

	/**
	 * 
	 * @param ur
	 * @return
	 */
	public Boolean existeUsuarioEnRol(UsuarioRol ur) {

		StringBuilder sb = new StringBuilder();

		sb.append("Select ur							");
		sb.append(" from UsuarioRol ur 					");
		sb.append("  where ur.usuario.id =:idUsuario 	");
		sb.append("   and ur.tipoUsuario =:tipoUsuario	");
		sb.append("   and ur.estado =1					");

		TypedQuery<UsuarioRol> q = em.createQuery(sb.toString(), UsuarioRol.class);

		return q.getResultList().size() > 0;

	}

	/**
	 * 
	 * @param usuarioRol
	 * @return
	 */
	public UsuarioRol crearActualizar(UsuarioRol usuarioRol) {
		if (null != usuarioRol.getId()) {
			this.crear(usuarioRol);
		} else {
			this.actualizar(usuarioRol);
		}
		return usuarioRol;
	}

	/**
	 * 
	 * @param usuarioRol
	 * @return
	 */
	private UsuarioRol crear(UsuarioRol usuarioRol) {
		usuarioRol.setAccion(Accion.CREAR);
		em.persist(usuarioRol);
		em.flush();
		return usuarioRol;
	}

	/**
	 * 
	 * @param usuarioRol
	 * @return
	 */
	private UsuarioRol actualizar(UsuarioRol usuarioRol) {
		if (usuarioRol.getEstado()) {
			usuarioRol.setAccion(Accion.ACTUALIZAR);
		} else {
			usuarioRol.setAccion(Accion.ELIMINAR);
		}

		em.persist(usuarioRol);
		em.flush();
		return usuarioRol;
	}

}

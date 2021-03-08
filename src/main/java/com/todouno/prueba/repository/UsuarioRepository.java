package com.todouno.prueba.repository;

import java.util.List;
import java.util.function.Function;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.todouno.prueba.models.Usuario;
import com.todouno.prueba.utils.Accion;
import com.todouno.prueba.utils.UpdatableBCrypt;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

	@Inject
	EntityManager em;

	private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);

	public static String bCryptHash(String password) {
		return bcrypt.hash(password);
	}

	public static boolean verifyAndUpdateHash(String password, String hash, Function<String, Boolean> updateFunc) {
		return bcrypt.verifyAndUpdateHash(password, hash, updateFunc);
	}

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public Usuario obtenerUsuarioPorUsuario(String usuario) {

		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT u FROM Usuario u WHERE u.usuario like :usuario");

		TypedQuery<Usuario> q = em.createQuery(sb.toString(), Usuario.class);
		q.setParameter("usuario", usuario);

		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public List<Usuario> obtenerUsuariosActivos() {

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT u FROM Usuario u WHERE u.estado=1");
		TypedQuery<Usuario> q = em.createQuery(sb.toString(), Usuario.class);
		return q.getResultList();

	}

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public Usuario crearActualizarUsuario(Usuario usuario) {

		usuario.setContrasenia(bCryptHash(usuario.getContrasenia()));
		usuario.setNumeroTarjetaCredito(bCryptHash(usuario.getNumeroTarjetaCredito()));

		if (null != usuario.getId()) {
			this.crear(usuario);
		} else {
			this.actualizar(usuario);
		}
		return usuario;
	}

	private Usuario crear(Usuario usuario) {
		usuario.setAccion(Accion.CREAR);
		//

		em.persist(usuario);
		em.flush();
		return usuario;
	}

	private Usuario actualizar(Usuario usuario) {
		usuario.setAccion(Accion.ACTUALIZAR);
		em.persist(usuario);
		em.flush();
		return usuario;
	}

}

package com.todouno.prueba.rest;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.todouno.prueba.models.Usuario;
import com.todouno.prueba.models.UsuarioRol;
import com.todouno.prueba.services.UsuarioServices;
import com.todouno.prueba.utils.MensajePojo;

@Path("/tienda/api/v1/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UsuarioResource {

	@Inject
	private UsuarioServices srvUsuario;

	/**
	 * TODO:implementar JWT
	 * 
	 * @param usuario
	 * @param contrasenia
	 * @return
	 */
	@GET
	@Path(value = "auth")
	public Response autenticar(String usuario, String contrasenia) {

		Usuario u = srvUsuario.obtenerUsuario(usuario);

		if (null != u) {

			if (StringUtils.equals(u.getContrasenia(), contrasenia)) {
				// regresar el token
				return Response.status(Response.Status.OK).entity(u).build();

			}
		}

		return Response.status(Response.Status.UNAUTHORIZED)
				.entity(MensajePojo.builder().mensaje("Usuario o contrasenia no validos")).build();

	}

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	@POST
	public Response registrarUsuario(Usuario usuario) {
		
		Usuario u = srvUsuario.obtenerUsuario(usuario.getUsuario());

		if (null != u) {
			return Response.status(Response.Status.CONFLICT)
					.entity(MensajePojo.builder().mensaje("Usuario o contrasenia no validos")).build();
		}
		u = srvUsuario.crearActualizarUsuario(usuario);

		return Response.status(Response.Status.OK).entity(u).build();

	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path(value = "todos")
	public Response obtenerUsuarioActivos() {
		List<Usuario> lstUsuarios = srvUsuario.obtenerUsuariosActivos();
		return Response.status(Response.Status.OK).entity(lstUsuarios).build();

	}

	@POST
	@Path(value = "rol")
	public Response registrarRolUsuario(UsuarioRol usuarioRol) {

		if (srvUsuario.exiteUsuarioEnRol(usuarioRol)) {
			return Response.status(Response.Status.CONFLICT).entity(usuarioRol).build();
		}

		usuarioRol = srvUsuario.crearActualizarUsuarioRol(usuarioRol);

		return Response.status(Response.Status.OK).entity(usuarioRol).build();

	}

}

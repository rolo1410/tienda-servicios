package com.todouno.prueba.load;

import java.util.Date;
import java.util.Random;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import com.todouno.prueba.models.Producto;
import com.todouno.prueba.models.Usuario;
import com.todouno.prueba.models.UsuarioRol;
import com.todouno.prueba.repository.ProductoRepository;
import com.todouno.prueba.repository.UsuarioRepository;
import com.todouno.prueba.repository.UsuarioRolRepository;
import com.todouno.prueba.utils.TipoIdentificacion;
import com.todouno.prueba.utils.TipoUsuario;

import io.quarkus.runtime.StartupEvent;

/**
 * Clase para levantar datos de prueba
 * 
 * @author Rolando Casigña
 * @date 7 mar. 2021
 */
@Singleton
public class OnLoadApp {

	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	private UsuarioRolRepository usuarioRolRepository;

	@Inject
	private ProductoRepository productoRepository;

	@Transactional
	public void loadUsers(@Observes StartupEvent evt) {

		usuarioRolRepository.deleteAll();
		usuarioRepository.deleteAll();

		/* usuario administrador */
		Usuario admin = Usuario.builder().contrasenia("a1b2c3").fechaNacimiento(new Date()).nombres("Admin Usuario")
				.numeroTarjetaCredito("11111111111").usuario("admin1").identificaicion("12345334")
				.tipoIdentificacion(TipoIdentificacion.CEDULA).build();

		usuarioRepository.crearActualizarUsuario(admin);

		UsuarioRol rolAdmin = UsuarioRol.builder().estado(Boolean.TRUE).fechaIngreso(new Date())
				.habilitado(Boolean.TRUE).tipoUsuario(TipoUsuario.ADMININSTRADOR).usuario(admin).build();

		usuarioRolRepository.persist(rolAdmin);

		/* usuario cliente */
		Usuario cliente = Usuario.builder().contrasenia("a1b2c3").fechaNacimiento(new Date()).nombres("Cliente Usuario")
				.numeroTarjetaCredito("222222222").usuario("cliente1").identificaicion("0025412544")
				.tipoIdentificacion(TipoIdentificacion.CEDULA).build();

		usuarioRepository.crearActualizarUsuario(cliente);

		UsuarioRol rolCliente = UsuarioRol.builder().estado(Boolean.TRUE).fechaIngreso(new Date())
				.habilitado(Boolean.TRUE).tipoUsuario(TipoUsuario.CLIENTE).usuario(cliente).build();

		usuarioRolRepository.persist(rolCliente);

		/* CLIENTE 2 */
		Usuario cliente2 = Usuario.builder().contrasenia("a1b2c3").fechaNacimiento(new Date())
				.numeroTarjetaCredito("222222222").nombres("Cliente Usuario").usuario("cliente2")
				.identificaicion("0025412544").tipoIdentificacion(TipoIdentificacion.CEDULA).build();

		usuarioRepository.crearActualizarUsuario(cliente2);

		UsuarioRol rolCliente2 = UsuarioRol.builder().estado(Boolean.TRUE).fechaIngreso(new Date())
				.habilitado(Boolean.TRUE).tipoUsuario(TipoUsuario.CLIENTE).usuario(cliente2).build();

		usuarioRolRepository.persist(rolCliente2);

		//
		crearProductosPrueba(52L);
	}

	/**
	 * 
	 * @param numProductos
	 */
	public void crearProductosPrueba(Long numProductos) {
		Random r = new Random();
		int minimo = 10;
		int maximo = 100;
		for (Long i = 0L; i < numProductos; i++) {
			Long stockInicial = Long.valueOf(r.nextInt(maximo - minimo) + minimo);
			Producto p = Producto.builder().descripcion("producto " + i).nombreProducto("nombre producto " + i)
					.urlImagen("http://www.musicapopular.cult.cu/wp-content/uploads/2017/12/imagen-no-disponible.png")
					.numeroUnidades(stockInicial).build();

			productoRepository.persist(p);

		}

	}

}

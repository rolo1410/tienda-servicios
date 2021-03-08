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

	String[] imagenes = new String[] {
			"https://scontent.fuio1-1.fna.fbcdn.net/v/t1.0-0/p526x296/103084749_1454427968073038_4008207180932408227_o.jpg?_nc_cat=104&ccb=1-3&_nc_sid=8bfeb9&_nc_eui2=AeGFpeBuL1pQYZGwgf6VRZHrXO3GcM3987dc7cZwzf3zt7AOtiXKeY1tefAT8M7C4-yQHHV1O7oz7inXLXAEdo1L&_nc_ohc=qdQcZSthFMcAX_sgPnv&_nc_ht=scontent.fuio1-1.fna&tp=6&oh=c6c9747296d8cfea1cd5faff708d4886&oe=606C8D5B",
			"https://www.pngitem.com/pimgs/m/15-157975_cartoon-iron-man-png-iron-man-chibi-png.png",
			"http://assets.stickpng.com/thumbs/5842a4f5a6515b1e0ad75af6.png",
			"https://i.pinimg.com/originals/3a/a6/28/3aa628ab0cdd48736923bea753f20c3f.jpg",
			"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzKJ9SWxnzICfT9-ypU5sjbyY6AdzIGI6qJw&usqp=CAU",
			"https://images-na.ssl-images-amazon.com/images/I/51kEjIy5erL._AC_SY879_.jpg" };

	@Transactional
	public void loadUsers(@Observes StartupEvent evt) {

		usuarioRolRepository.deleteAll();
		usuarioRepository.deleteAll();

		/* usuario administrador */
		Usuario admin = Usuario.builder().contrasenia("a1b2c3").fechaNacimiento(new Date()).nombres("Admin Usuario")
				.numeroTarjetaCredito("11111111111").usuario("admin1").identificacion("12345334")
				.tipoIdentificacion(TipoIdentificacion.CEDULA).build();

		usuarioRepository.crearActualizarUsuario(admin);

		UsuarioRol rolAdmin = UsuarioRol.builder().estado(Boolean.TRUE).fechaIngreso(new Date())
				.habilitado(Boolean.TRUE).tipoUsuario(TipoUsuario.ADMININSTRADOR).usuario(admin).build();

		usuarioRolRepository.persist(rolAdmin);

		/* usuario cliente */
		Usuario cliente = Usuario.builder().contrasenia("a1b2c3").fechaNacimiento(new Date()).nombres("Cliente Usuario")
				.numeroTarjetaCredito("222222222").usuario("cliente1").identificacion("0025412544")
				.tipoIdentificacion(TipoIdentificacion.CEDULA).build();

		usuarioRepository.crearActualizarUsuario(cliente);

		UsuarioRol rolCliente = UsuarioRol.builder().estado(Boolean.TRUE).fechaIngreso(new Date())
				.habilitado(Boolean.TRUE).tipoUsuario(TipoUsuario.CLIENTE).usuario(cliente).build();

		usuarioRolRepository.persist(rolCliente);

		/* CLIENTE 2 */
		Usuario cliente2 = Usuario.builder().contrasenia("a1b2c3").fechaNacimiento(new Date())
				.numeroTarjetaCredito("222222222").nombres("Cliente Usuario").usuario("cliente2")
				.identificacion("0025412544").tipoIdentificacion(TipoIdentificacion.CEDULA).build();

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
			int indexImagen = r.nextInt(4);
			Producto p = Producto.builder().descripcion("producto " + i).nombreProducto("nombre producto " + i)
					.urlImagen(imagenes[indexImagen]).numeroUnidades(stockInicial).build();

			productoRepository.persist(p);

		}

	}

}

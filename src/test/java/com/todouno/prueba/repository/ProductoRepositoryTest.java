package com.todouno.prueba.repository;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.todouno.prueba.models.Producto;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ProductoRepositoryTest {

	@Inject
	ProductoRepository productoRepository;

	@Test
	@Transactional
	void crearProductoTest() {
		Producto p = Producto.builder().descripcion("BORRAR_PRUEBA").nombreProducto("BORRAR_PRUEBA").build();
		productoRepository.crearActualizar(p);
		Assertions.assertTrue(null != p.getId());

	}

	@Test
	@Transactional
	void actualizarProductoTest() {

		/* creo */
		Producto pNuevo = Producto.builder().descripcion("BORRAR_PRUEBA").nombreProducto("BORRAR_PRUEBA").build();
		productoRepository.crearActualizar(pNuevo);

		/* actualizo */
		Producto emuladoNuevo = Producto.builder().descripcion("BORRAR_PRUEBA_UPDATE")
				.nombreProducto("BORRAR_PRUEBA_UPDATE").id(pNuevo.getId()).build();
		emuladoNuevo=	productoRepository.crearActualizar(emuladoNuevo);

		Assertions.assertTrue(null != emuladoNuevo.getId()
				&& StringUtils.equals("BORRAR_PRUEBA_UPDATE", emuladoNuevo.getDescripcion()));
	}

	void buscarProducto() {

	}

	@Test
	@Transactional
	void eliminarProductoTest() {
		/* creo */
		Producto pNuevo = Producto.builder().descripcion("BORRAR_PRUEBA").nombreProducto("BORRAR_PRUEBA").build();
		productoRepository.crearActualizar(pNuevo);
		Producto p = Producto.builder().descripcion("BORRAR_PRUEBA_DELETE").nombreProducto("BORRAR_PRUEBA_DELETE")
				.id(pNuevo.getId()).build();
		productoRepository.eliminar(p.getId());
		Assertions.assertTrue(null != p.getId() && Boolean.FALSE.equals(p.getEstado())
				&& StringUtils.equals("BORRAR_PRUEBA_UPDATE", p.getDescripcion()));
	}

}

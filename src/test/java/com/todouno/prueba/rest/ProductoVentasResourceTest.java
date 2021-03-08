package com.todouno.prueba.rest;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import com.todouno.prueba.models.Producto;
import com.todouno.prueba.repository.ProductoRepository;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@QuarkusTest
public class ProductoVentasResourceTest {

	@RestClient
	private ProductosResource productoVentasTest;

	@Inject
	ProductoRepository productoRepository;

	void obtenerProductosDisponibles_existeEndPoint_disponibles() {
		RestAssured.given().when().get("/disponibles").then().statusCode(200);
	}

	@Test
	void endPoint_crear_producto() {
		Producto p = Producto.builder().nombreProducto("tst").descripcion("descripcion").numeroUnidades(10l).build();
		RestAssured.given().when().post("", p).then().statusCode(200);
	}

	@Test
	void productos_actualizar() {
		List<Producto> lstProductos = productoRepository.obtenerProductos();
		if (null != lstProductos && !lstProductos.isEmpty()) {
			RestAssured.given().when().post("/disponibles", lstProductos.get(0)).then().statusCode(200);
		}
	}

	@Test
	void crearActualizarProducto_actualizar() {
		Producto p = Producto.builder().nombreProducto("tst").descripcion("descripcion").numeroUnidades(10l).build();
		RestAssured.given().when().post("disponibles", p).then().statusCode(200);
	}

	@Test
	void actualizastrok_valido() {
		RestAssured.given().when().post("actualizar/stock/?idProducto=1&&nuevoTotal=10").then().statusCode(200);
	}

}

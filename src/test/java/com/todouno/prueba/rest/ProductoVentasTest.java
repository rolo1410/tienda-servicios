package com.todouno.prueba.rest;

import static org.hamcrest.CoreMatchers.is;

import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.todouno.prueba.utils.CompraDto;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;

@QuarkusTest
public class ProductoVentasTest {

	@InjectMock
	@RestClient
	private ProductosResource productoVentasTest;;

	@Test
	void ventaMalFormada() {
		CompraDto v = null;

//		Mockito.when(ventaResource.venta(v)).thenReturn(Response.status(Response.Status.BAD_REQUEST).build());
//		RestAssured.given().when().post("/hello").then().statusCode(200).body(is("{msg:'error'}"));
	}

}

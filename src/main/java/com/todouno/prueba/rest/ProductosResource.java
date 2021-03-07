package com.todouno.prueba.rest;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.todouno.prueba.models.Producto;
import com.todouno.prueba.repository.ProductoRepository;
import com.todouno.prueba.utils.MensajePojo;

@Path("/tienda/api/v1/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ProductosResource {

	@Inject
	private ProductoRepository productoRepository;

	@GET
	@Path(value = "/disponibles")
	public Response obtenerProductosDisponibles() {

		List<Producto> lstProductosDisponibles = productoRepository.obtenerProductosDisponibles();
		return Response.status(Response.Status.OK).entity(lstProductosDisponibles).build();

	}

	@GET
	public Response obtenerTodosProductos() {
		List<Producto> lstProductosDisponibles = productoRepository.obtenerProductosDisponibles();
		return Response.status(Response.Status.OK).entity(lstProductosDisponibles).build();
	}

	@POST
	public Response crearActualizarProducto(Producto producto) {
		Producto productoGuardado = productoRepository.crearActualizar(producto);
		return Response.status(Response.Status.OK).entity(productoGuardado).build();
	}

	@POST
	@Path(value = "/actualizar/stock/")
	public Response crearActualizarStockProducto(@QueryParam("idProducto") Long idProducto,
			@QueryParam("nuevoTotal") Long nuevoTotal) {
		Producto p = productoRepository.findById(idProducto);
		if (null != p) {
			p.setNumeroUnidades(nuevoTotal);
			productoRepository.crearActualizar(p);
			return Response.status(Response.Status.OK).entity(p).build();

		} else {
			return Response.status(Response.Status.CONFLICT)
					.entity(MensajePojo.builder().mensaje("No se ha encontrado el pruducto indicado")).build();
		}

	}

}

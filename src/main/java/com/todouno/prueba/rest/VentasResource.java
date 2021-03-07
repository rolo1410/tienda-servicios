package com.todouno.prueba.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.todouno.prueba.utils.CompraDto;

@Path("/tienda/api/v1/venta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class VentasResource {

	@POST
	public Response venta(CompraDto compraDto) {
		return Response.status(Response.Status.BAD_REQUEST).entity("{msg:'error'}").build();
	}

}

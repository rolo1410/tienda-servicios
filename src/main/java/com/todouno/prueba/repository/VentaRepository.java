package com.todouno.prueba.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.todouno.prueba.models.Producto;
import com.todouno.prueba.models.Venta;
import com.todouno.prueba.utils.Accion;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class VentaRepository implements PanacheRepository<Producto> {

	@Inject
	EntityManager em;
	
	
	
	public Venta crearActualizar(Venta producto) {
		if (null != producto.getId()) {
			this.crear(producto);
		} else {
			this.actualizar(producto);
		}
		return producto;
	}

	private Venta crear(Venta producto) {
		producto.setAccion(Accion.CREAR);
		em.persist(producto);
		em.flush();
		return producto;
	}

	private Venta actualizar(Venta producto) {
		producto.setAccion(Accion.ACTUALIZAR);
		em.persist(producto);
		em.flush();
		return producto;
	}

}

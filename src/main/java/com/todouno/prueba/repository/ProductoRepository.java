package com.todouno.prueba.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.todouno.prueba.models.Producto;
import com.todouno.prueba.utils.Accion;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ProductoRepository implements PanacheRepository<Producto> {

	@Inject
	EntityManager em;

	public Producto crearActualizar(Producto producto) {
		if (null != producto.getId()) {
			this.crear(producto);
		} else {
			this.actualizar(producto);
		}
		return producto;
	}

	private Producto crear(Producto producto) {
		producto.setAccion(Accion.CREAR);
		em.persist(producto);
		em.flush();
		return producto;
	}

	private Producto actualizar(Producto producto) {
		producto.setAccion(Accion.ACTUALIZAR);
		em.persist(producto);
		em.flush();
		return producto;
	}

	public void eliminar(Long idProducto) {
		Producto p = this.obtenerProductoPorId(idProducto);
		if (null != p) {
			p.setAccion(Accion.ELIMINAR);
			p.setEstado(Boolean.FALSE);
			this.actualizar(p);
		}
	}

	/**
	 * ç
	 * 
	 * @param idProducto
	 * @param nuevoStock
	 * @return
	 */
	public Producto actualizarStock(Long idProducto, Long nuevoStock) {

		Producto p = findById(idProducto);
		p.setNumeroUnidades(nuevoStock);
		persist(p);
		return p;
	}

	/**
	 * Obtiene la lista de Productos disponibles
	 * 
	 * @return
	 */
	public List<Producto> obtenerProductosDisponibles() {
		return em.createNamedQuery("Producto.disponibles", Producto.class).getResultList();
	}

	/**
	 * Obtiene un producto por ID
	 * 
	 * @param id
	 * @return
	 */
	public Producto obtenerProductoPorId(Long id) {
		return em.createNamedQuery("Producto.porId", Producto.class).setParameter("id", id).getSingleResult();
	}

	public List<Producto>

			obtenerProductos() {
		return em.createNamedQuery("Producto.todos", Producto.class).getResultList();
	}

	/**
	 * 
	 * Obtiene una lista de productos cuyo nombre contiene el parametro
	 * 
	 * @param nombreProducto nombre del producto buscado
	 * @return
	 */
	public List<Producto> obtenerProductoPorNombre(String nombreProducto) {
		return em.createNamedQuery("Producto.porId", Producto.class).setParameter("nombreProducto", nombreProducto)
				.getResultList();
	}

}

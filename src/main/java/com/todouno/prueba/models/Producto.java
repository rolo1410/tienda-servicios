package com.todouno.prueba.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Rolando Casigña
 * @date 6 mar. 2021
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "st_producto")
@NamedQueries({ @NamedQuery(name = "Producto.porId", query = "SELECT p FROM Producto p WHERE p.id= :id"),
		@NamedQuery(name = "Producto.disponibles", query = "SELECT p FROM Producto p WHERE p.numeroUnidades>0 ORDER BY p.nombreProducto"),
		@NamedQuery(name = "Producto.porNombre", query = "SELECT p FROM Producto p WHERE p.nombreProducto like :nombreProducto"),
		@NamedQuery(name = "Producto.todos", query = "SELECT p FROM Producto p WHERE p.estado=1") })
public class Producto extends Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "st_pro_id")
	private Long id;

	@Column(name = "st_pro_nmb_producto")
	private String nombreProducto;

	@Column(name = "st_pro_descripcion")
	private String descripcion;

	@Column(name = "st_pro_num_unidades")
	private Long numeroUnidades;

	@Column(name = "st_pro_url_imagen", columnDefinition = "TEXT")
	private String urlImagen;

}

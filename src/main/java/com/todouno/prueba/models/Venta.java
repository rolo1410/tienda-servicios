package com.todouno.prueba.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "st_venta")
public class Venta extends Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "st_ven_id")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "st_pro_id", referencedColumnName = "st_pro_id")
	private Producto producto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "st_usu_id", referencedColumnName = "st_usu_id")
	private Usuario usuario;

	@Column(name = "st_ven_cantidad")
	private Long cantidad;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "st_ven_fch_venta")
	private Date fechaVenta;
}

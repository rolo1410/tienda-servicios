package com.todouno.prueba.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.todouno.prueba.utils.Accion;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Rolando Casigña
 * @date 6 mar. 2021
 */
@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class Auditoria {

	/**
	 * TODO: FALTA AUDITORIA DE MODIFICAION ENTRE CREACION Y ULTIMA MODIFICAICÓN,
	 * ENFOQUE EN TABLA DE HISTORICO
	 */

	@Column(name = "aud_usu_creacion")
	private String usuarioCreacion;

	@Column(name = "aud_usu_ult_modificacion")
	private String usuarioUltimaModificacion;

	@Enumerated(EnumType.STRING)
	@Column(name = "aud_accion")
	private Accion accion = Accion.CREAR;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "aud_fch_creacion")
	private Date fechaCreacion;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "aud_fch_modificacion")
	private Date fechaModificacion;

	@Column(name = "aud_estado")
	private Boolean estado = Boolean.TRUE;

}

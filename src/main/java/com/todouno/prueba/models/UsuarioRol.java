package com.todouno.prueba.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.todouno.prueba.utils.TipoUsuario;

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
@Table(name = "st_usuario_rol")
public class UsuarioRol extends Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Usuario usuario;

	@Enumerated(EnumType.STRING)
	@Column(name = "st_usu_rol_estado")
	private TipoUsuario tipoUsuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "st_usu_rol_fch_ingreso")
	private Date fechaIngreso;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "st_usu_rol_fch_baja")
	private Date fechaBaja;

	@Column(name = "st_usu_rol_hablilitado")
	private Boolean habilitado;

	@Column(name = "st_usu_rol_estado")
	private Boolean estado;
}

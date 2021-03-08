package com.todouno.prueba.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.todouno.prueba.utils.TipoIdentificacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/***
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
@Table(name = "st_usuario")
public class Usuario extends Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "st_usu_id")
	private Long id;

	@Column(name = "st_usu_tpo_identificacion")
	private TipoIdentificacion tipoIdentificacion;

	@Column(name = "st_usu_identificacion")
	private String identificacion;

	@Column(name = "st_usu_tpo_nombres")
	private String nombres;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "st_usu_fch_nacimiento")
	private Date fechaNacimiento;

	@Column(name = "st_usu_usuario")
	private String usuario;

	@Column(name = "st_usu_contrasenia")
	private String contrasenia;

//	@ColumnTransformer(read = "pgp_sym_decrypt(numeroTarjetaCredito, 'mySecretKey')", write = "pgp_sym_encrypt(?, 'mySecretKey')")

	@Column(name = "st_usu_num_tar_credito")
	private String numeroTarjetaCredito;

}

package com.todouno.prueba.utils;

import java.io.Serializable;
import java.util.List;

import com.todouno.prueba.models.Producto;
import com.todouno.prueba.models.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompraDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private List<Producto> lstProductos;
	private Boolean estado;

}

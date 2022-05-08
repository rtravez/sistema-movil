package ec.uisrael.sisreha.com.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public abstract class BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String hostCreacion;

	protected Date fechaCreacion;

	protected String usuarioCreacion;

	protected String hostModificacion;

	protected Date fechaModificacion;

	protected String usuarioModificacion;

}

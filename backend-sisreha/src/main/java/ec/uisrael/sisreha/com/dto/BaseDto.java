package ec.uisrael.sisreha.com.dto;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String hostCreacion;

	protected Date fechaCreacion;

	protected String usuarioCreacion;

	protected String hostModificacion;

	protected Date fechaModificacion;

	protected String usuarioModificacion;

	public String getHostCreacion() {
		return hostCreacion;
	}

	public void setHostCreacion(String hostCreacion) {
		this.hostCreacion = hostCreacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getHostModificacion() {
		return hostModificacion;
	}

	public void setHostModificacion(String hostModificacion) {
		this.hostModificacion = hostModificacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

}

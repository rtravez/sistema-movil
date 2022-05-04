package ec.uisrael.sisreha.com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
// @EntityListeners({ EntityAuditorListenerAS2.class })
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "host_creacion", insertable = true, updatable = false, nullable = true, length = 50)
	protected String hostCreacion;

	@Column(name = "fecha_creacion", insertable = true, updatable = false, nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date fechaCreacion;

	@Column(name = "usuario_creacion", insertable = true, updatable = false, nullable = true, length = 50)
	protected String usuarioCreacion;

	@Column(name = "host_modificacion", insertable = true, updatable = false, nullable = true, length = 50)
	protected String hostModificacion;

	@Column(name = "fecha_modificacion", insertable = true, updatable = false, nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date fechaModificacion;

	@Column(name = "usuario_modificacion", insertable = true, updatable = false, nullable = true, length = 50)
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

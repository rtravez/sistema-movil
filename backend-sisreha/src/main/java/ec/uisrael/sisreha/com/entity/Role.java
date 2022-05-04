package ec.uisrael.sisreha.com.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the roles database table.
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rol_id", unique = true, nullable = false)
	private Long rolId;

	@Column(nullable = false)
	private Boolean estado;

	@Column(nullable = false, length = 20)
	private String nombre;

	// bi-directional many-to-one association to RolesUsuario
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private List<RoleUser> rolesUsuarios;

	public Role() {
	}

	public Long getRolId() {
		return this.rolId;
	}

	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<RoleUser> getRolesUsuarios() {
		return this.rolesUsuarios;
	}

	public void setRolesUsuarios(List<RoleUser> rolesUsuarios) {
		this.rolesUsuarios = rolesUsuarios;
	}

	public RoleUser addRolesUsuario(RoleUser rolesUsuario) {
		getRolesUsuarios().add(rolesUsuario);
		rolesUsuario.setRole(this);

		return rolesUsuario;
	}

	public RoleUser removeRolesUsuario(RoleUser rolesUsuario) {
		getRolesUsuarios().remove(rolesUsuario);
		rolesUsuario.setRole(null);

		return rolesUsuario;
	}

}
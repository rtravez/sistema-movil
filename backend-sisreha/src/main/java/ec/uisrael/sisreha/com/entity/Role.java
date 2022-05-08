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

import lombok.Data;

/**
 * The persistent class for the roles database table.
 */

@Data
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

}
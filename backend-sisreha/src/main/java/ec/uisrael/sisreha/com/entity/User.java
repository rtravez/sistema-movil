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
 * The persistent class for the usuarios database table.
 */

@Data
@Entity
@Table(name = "usuarios")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_id", unique = true, nullable = false)
	private Long usuarioId;

	@Column(nullable = false)
	private Boolean enabled;

	@Column(nullable = false, length = 60)
	private String password;

	@Column(nullable = false, length = 20)
	private String username;

	// bi-directional many-to-one association to RoleUser
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<RoleUser> rolesUsers;

}
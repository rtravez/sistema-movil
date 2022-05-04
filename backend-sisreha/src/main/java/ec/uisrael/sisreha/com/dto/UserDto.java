package ec.uisrael.sisreha.com.dto;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long usuarioId;

	private Boolean enabled;

	private String password;

	private String username;

	private List<RoleUserDto> rolesUserDtos;

	public UserDto() {
		super();
	}

	public Long getUsuarioId() {
		return this.usuarioId;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public List<RoleUserDto> getRolesUserDtos() {
		return rolesUserDtos;
	}

	public void setRolesUserDtos(List<RoleUserDto> rolesUserDtos) {
		this.rolesUserDtos = rolesUserDtos;
	}

}
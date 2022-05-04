package ec.uisrael.sisreha.com.dto;

import java.io.Serializable;

public class RoleUserDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long rolUsuarioId;

	private Boolean estado;

	private RoleDto roleDto;

	private UserDto userDto;

	public RoleUserDto() {
		super();
	}

	public Long getRolUsuarioId() {
		return rolUsuarioId;
	}

	public void setRolUsuarioId(Long rolUsuarioId) {
		this.rolUsuarioId = rolUsuarioId;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public RoleDto getRoleDto() {
		return roleDto;
	}

	public void setRoleDto(RoleDto roleDto) {
		this.roleDto = roleDto;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

}
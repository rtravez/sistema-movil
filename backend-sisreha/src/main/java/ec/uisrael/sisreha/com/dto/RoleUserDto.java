package ec.uisrael.sisreha.com.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class RoleUserDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long rolUsuarioId;

	private Boolean estado;

	private RoleDto roleDto;

	private UserDto userDto;

}
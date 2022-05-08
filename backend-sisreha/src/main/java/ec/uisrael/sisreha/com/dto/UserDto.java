package ec.uisrael.sisreha.com.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long usuarioId;

	private Boolean enabled;

	private String password;

	private String username;

	private List<RoleUserDto> rolesUserDtos;

}
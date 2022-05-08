package ec.uisrael.sisreha.com.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class RoleDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long rolId;

	private Boolean estado;

	private String nombre;

	private List<RoleUserDto> rolesUsuarioDtos;

}
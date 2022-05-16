package ec.uisrael.sisreha.com.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ec.uisrael.sisreha.com.dto.UserDto;
import ec.uisrael.sisreha.com.entity.User;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * 
 * @author renetravez
 * @version $1.0$
 */
@Mapper(implementationName = "UserMapper", implementationPackage = "<PACKAGE_NAME>.impl")
public interface IUserMapper {

	IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

	public UserDto entityToDto(User entity);

	public User dtoToEntity(UserDto dto);

	public List<UserDto> entitiesToDtos(List<User> entities);

	public List<User> dtosToEntities(List<UserDto> dtos);

}

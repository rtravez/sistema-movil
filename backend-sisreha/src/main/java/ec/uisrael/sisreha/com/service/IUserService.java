package ec.uisrael.sisreha.com.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import ec.uisrael.sisreha.com.entity.User;
import ec.uisrael.sisreha.com.exception.ExceptionManager;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * 
 * @author renetravez
 * @version $1.0$
 */
public interface IUserService extends IGenericService<User, Long>, UserDetailsService, Serializable {

	public Optional<User> findByUsername(String username) throws ExceptionManager;	
}

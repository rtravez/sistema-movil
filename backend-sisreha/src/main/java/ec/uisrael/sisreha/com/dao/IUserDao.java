package ec.uisrael.sisreha.com.dao;

import java.io.Serializable;
import java.util.Optional;

import ec.uisrael.sisreha.com.entity.User;
import ec.uisrael.sisreha.com.exception.ExceptionManager;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * 
 * @author renetravez
 * @version $1.0$
 */
public interface IUserDao extends IGenericDao<User, Long>, Serializable {

	// @Query("select u from Usuario u where u.username = ?1")
	public Optional<User> findByUsername(String username) throws ExceptionManager;

}

package ec.uisrael.sisreha.com.dao;

import java.io.Serializable;
import java.util.List;

import ec.uisrael.sisreha.com.entity.RoleUser;
import ec.uisrael.sisreha.com.entity.User;
import ec.uisrael.sisreha.com.exception.ExceptionManager;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * 
 * @author renetravez
 * @version $1.0$
 */
public interface IRoleUserDao extends IGenericDao<RoleUser, Long>, Serializable {

	public List<RoleUser> findByUser(User user) throws ExceptionManager;

}

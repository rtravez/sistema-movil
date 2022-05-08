package ec.uisrael.sisreha.com.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.uisrael.sisreha.com.dao.IGenericDao;
import ec.uisrael.sisreha.com.dao.IRoleUserDao;
import ec.uisrael.sisreha.com.entity.RoleUser;
import ec.uisrael.sisreha.com.entity.User;
import ec.uisrael.sisreha.com.exception.ExceptionManager;
import ec.uisrael.sisreha.com.service.IRoleUserService;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * 
 * @author renetravez
 * @version $1.0$
 */
@Scope("singleton")
@Service("RoleUserService")
public class RoleUserService extends GenericService<RoleUser, Long> implements IRoleUserService {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(RoleUserService.class);

	@Autowired
	private IRoleUserDao roleUserDao;

	public RoleUserService() {
	}

	@Autowired
	public RoleUserService(IGenericDao<RoleUser, Long> genericDao) {
		super(genericDao);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RoleUser> findByUser(User user) throws ExceptionManager {
		try {
			return roleUserDao.findByUser(user);
		} catch (Exception e) {
			LOG.error("findByUserId: ", e);
			throw new ExceptionManager().new GettingException("Error al buscar los registro");
		}
	}
}

package ec.uisrael.sisreha.com.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ec.uisrael.sisreha.com.dao.IGenericDao;
import ec.uisrael.sisreha.com.dao.IUserDao;
import ec.uisrael.sisreha.com.exception.ExceptionManager;
import ec.uisrael.sisreha.com.service.IUserService;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * 
 * @author renetravez
 * @version $1.0$
 */
@Scope("singleton")
@Service("UserService")
public class UserService extends GenericService<ec.uisrael.sisreha.com.entity.User, Long> implements IUserService {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private IUserDao userDao;

	public UserService() {
	}

	@Autowired
	public UserService(IGenericDao<ec.uisrael.sisreha.com.entity.User, Long> genericDao) {
		super(genericDao);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ec.uisrael.sisreha.com.entity.User user = findByUsername(username).orElse(null);

		if (user == null) {
			LOG.error("Error en el Login: no existe el usuario '" + username + "' en el sistema!");
			throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
		}

		List<GrantedAuthority> authorities = user.getRolesUsers().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole().getNombre()))
				.peek(authority -> LOG.info("Role ".concat(authority.getAuthority()))).collect(Collectors.toList());

		if (authorities.isEmpty()) {
			LOG.error("Error en el Login: Usuario " + username + " no tiene roles asignados!");
			throw new UsernameNotFoundException(
					"Error en el Login: usuario " + username + " no tiene roles asignados!");
		}
		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ec.uisrael.sisreha.com.entity.User> findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = ExceptionManager.class)
	public ec.uisrael.sisreha.com.entity.User save(ec.uisrael.sisreha.com.entity.User user) throws ExceptionManager {
		try {
			return super.save(user);
		} catch (Exception e) {
			LOG.error("save: ", e);
			throw new ExceptionManager().new GettingException("Error al guardar el registro");
		}
	}
}

package ec.uisrael.sisreha.com.dao.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import ec.uisrael.sisreha.com.dao.IUserDao;
import ec.uisrael.sisreha.com.entity.User;
import ec.uisrael.sisreha.com.exception.ExceptionManager;


public class UserDao extends GenericDao<User, Long> implements IUserDao {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);

	public UserDao(JpaEntityInformation<User, Long> ei, EntityManager em) {
		super(ei, em);
	}

	@Override
	public Optional<User> findByUsername(String username) throws ExceptionManager {
		try {
			TypedQuery<User> query = em.createQuery("select u from Usuario u where u.username =:username", User.class);
			query.setParameter("username", username);
			return Optional.ofNullable(query.getSingleResult());
		} catch (Exception e) {
			LOG.error("findByUsername: ", e);
			throw new ExceptionManager().new FindingException("Error al buscar el registro");
		}
	}
}

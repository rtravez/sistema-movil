package ec.uisrael.sisreha.com.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import ec.uisrael.sisreha.com.dao.IRoleUserDao;
import ec.uisrael.sisreha.com.entity.RoleUser;
import ec.uisrael.sisreha.com.entity.User;
import ec.uisrael.sisreha.com.exception.ExceptionManager;

public class RoleUserDao extends GenericDao<RoleUser, Long> implements IRoleUserDao {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(RoleUserDao.class);

	public RoleUserDao(JpaEntityInformation<RoleUser, Long> ei, EntityManager em) {
		super(ei, em);
	}

	@Override
	public List<RoleUser> findByUser(User user) throws ExceptionManager {
		try {
			TypedQuery<RoleUser> query = em
					.createQuery("select ru from RoleUser ru where ru.user.usuarioId =:usuarioId", RoleUser.class);
			query.setParameter("usuarioId", user.getUsuarioId());
			return query.getResultList();
		} catch (Exception e) {
			LOG.error("findByUserId: ", e);
			throw new ExceptionManager().new FindingException("Error al buscar los registros");
		}
	}

}

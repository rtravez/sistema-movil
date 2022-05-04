package ec.uisrael.sisreha.com.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import ec.uisrael.sisreha.com.dao.IGenericDao;

@NoRepositoryBean
public class GenericDao<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements IGenericDao<T, ID> {

	protected EntityManager em;

	public GenericDao(JpaEntityInformation<T, ID> ei, EntityManager em) {
		super(ei, em);
		this.em = em;
	}

	public List<T> findByAttributeContainsText(String attributeName, String text) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> cQuery = builder.createQuery(getDomainClass());
		Root<T> root = cQuery.from(getDomainClass());
		cQuery.select(root).where(builder.like(root.<String> get(attributeName), "%" + text + "%"));
		TypedQuery<T> query = em.createQuery(cQuery);
		return query.getResultList();
	}
}

package ec.uisrael.sisreha.com.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ec.uisrael.sisreha.com.dao.IGenericDao;
import ec.uisrael.sisreha.com.exception.ExceptionManager;
import ec.uisrael.sisreha.com.service.IGenericService;

@Scope("singleton")
@Service("GenericService")
public class GenericService<T, ID extends Serializable> implements IGenericService<T, ID> {
	private static final Logger LOG = LoggerFactory.getLogger(GenericService.class);

	private IGenericDao<T, ID> genericDao;

	public GenericService() {
	}

	public GenericService(IGenericDao<T, ID> genericDao) {
		this.genericDao = genericDao;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = ExceptionManager.class)
	public T save(T entity) throws ExceptionManager {
		try {
			return genericDao.save(entity);
		} catch (Exception e) {
			LOG.error("save: ", e);
			throw new ExceptionManager().new GettingException("Error al guardar el registro");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = ExceptionManager.class)
	public List<T> saveAll(List<T> entities) throws ExceptionManager {
		try {
			return genericDao.saveAll(entities);
		} catch (Exception e) {
			LOG.error("save: ", e);
			throw new ExceptionManager().new GettingException("Error al guardar los registros");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<T> findById(ID id) throws ExceptionManager {
		try {
			return genericDao.findById(id);
		} catch (Exception e) {
			LOG.error("findById: ", e);
			throw new ExceptionManager().new FindingException("Error al buscar el registro");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsById(ID id) throws ExceptionManager {
		try {
			return genericDao.existsById(id);
		} catch (Exception e) {
			LOG.error("existsById: ", e);
			throw new ExceptionManager().new FindingException("Error al buscar el registro");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll() throws ExceptionManager {
		try {
			return genericDao.findAll();
		} catch (Exception e) {
			LOG.error("findAll: ", e);
			throw new ExceptionManager().new FindingException("Error al buscar los registros");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAllById(List<ID> ids) throws ExceptionManager {
		try {
			return genericDao.findAllById(ids);
		} catch (Exception e) {
			LOG.error("findAllById: ", e);
			throw new ExceptionManager().new FindingException("Error al buscar los registros");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() throws ExceptionManager {
		try {
			return genericDao.count();
		} catch (Exception e) {
			LOG.error("count: ", e);
			throw new ExceptionManager().new FindingException("Error al contar los registros");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = ExceptionManager.class)
	public void deleteById(ID id) throws ExceptionManager {
		try {
			genericDao.deleteById(id);
		} catch (Exception e) {
			LOG.error("deleteById: ", e);
			throw new ExceptionManager().new GettingException("Error al eliminar el registro");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = ExceptionManager.class)
	public void delete(T entity) throws ExceptionManager {
		try {
			genericDao.delete(entity);
		} catch (Exception e) {
			LOG.error("delete: ", e);
			throw new ExceptionManager().new GettingException("Error al eliminar el registro");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = ExceptionManager.class)
	public void deleteAllById(List<ID> ids) throws ExceptionManager {
		try {
			genericDao.deleteAllById(ids);
		} catch (Exception e) {
			LOG.error("deleteAllById: ", e);
			throw new ExceptionManager().new GettingException("Error al eliminar los registros");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = ExceptionManager.class)
	public void deleteAll(List<T> entities) throws ExceptionManager {
		try {
			genericDao.deleteAll(entities);
		} catch (Exception e) {
			LOG.error("deleteAll: ", e);
			throw new ExceptionManager().new GettingException("Error al eliminar los registros");
		}
	}
}

package ec.uisrael.sisreha.com.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import ec.uisrael.sisreha.com.exception.ExceptionManager;

public interface IGenericService<T, ID extends Serializable> {

	public T save(T entity) throws ExceptionManager;

	public List<T> saveAll(List<T> entities) throws ExceptionManager;

	public Optional<T> findById(ID id) throws ExceptionManager;

	public boolean existsById(ID id) throws ExceptionManager;

	public List<T> findAll() throws ExceptionManager;

	public List<T> findAllById(List<ID> ids) throws ExceptionManager;

	public Long count() throws ExceptionManager;

	public void deleteById(ID id) throws ExceptionManager;

	public void delete(T entity) throws ExceptionManager;

	public void deleteAllById(List<ID> ids) throws ExceptionManager;

	public void deleteAll(List<T> entities) throws ExceptionManager;
}

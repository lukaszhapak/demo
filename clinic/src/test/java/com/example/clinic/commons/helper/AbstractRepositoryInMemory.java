package com.example.clinic.commons.helper;

import com.example.clinic.commons.AbstractEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public abstract class AbstractRepositoryInMemory<T extends AbstractEntity> implements Repository<T, Long> {

  protected List<T> list = new ArrayList<>();
  private Long id = 0L;

  public void insertData(List<T> data) {
	list.addAll(data);
  }

  public void cleanData() {
	list.clear();
  }

  public T getById(Long id) {
	return findById(id).get();
  }

  public boolean existsById(Long id) {
	Optional<T> entity = list.stream()
		.filter(x -> Objects.equals(x.getId(), id))
		.findFirst();
	return entity.isPresent();
  }

  protected T save(T entity) {
	if (entity.getId() == null || !existsById(entity.getId())) {
	  setId(entity);
	}
	if (existsById(entity.getId())) {
	  deleteAllById(entity.getId());
	}
	list.add(entity);
	return entity;
  }

  protected Optional<T> findById(Long id) {
	return list.stream()
		.filter(x -> Objects.equals(x.getId(), id))
		.findFirst();
  }

  protected Long count() {
	return (long) list.size();
  }

  protected Long deleteAllById(Long id) {
	Optional<T> entity = list.stream()
		.filter(x -> Objects.equals(x.getId(), id))
		.findFirst();
	if (entity.isPresent()) {
	  list.remove(entity.get());
	  return 1L;
	} else {
	  return 0L;
	}
  }

  private void setId(T entity) {
	entity.setId(++id);
  }
}

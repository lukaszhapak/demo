package com.example.demo.commons;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public abstract class AbstractRepositoryInMemory<T extends AbstractEntity> implements
	Repository<T, Long> {

  protected Map<Long, T> map = new HashMap<>();
  private Long id = 0L;

  protected T save(T entity) {
	if (entity.getId() == null || !existsById(entity.getId())) {
	  setId(entity);
	}
	map.put(entity.getId(), entity);
	return entity;
  }

  protected Optional<T> findById(Long id) {
	return Optional.ofNullable(map.get(id));
  }

  protected Long count() {
	return (long) map.size();
  }

  protected Long deleteAllById(Long id) {
	if (map.containsKey(id)) {
	  map.remove(id);
	  return 1L;
	} else {
	  return 0L;
	}
  }

  protected boolean existsById(Long id) {
	return map.containsKey(id);
  }

  private void setId(T entity) {
	entity.setId(++id);
  }
}

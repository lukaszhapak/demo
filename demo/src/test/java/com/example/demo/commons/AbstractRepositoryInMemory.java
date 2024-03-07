package com.example.demo.commons;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public abstract class AbstractRepositoryInMemory<T extends AbstractEntity> implements Repository<T, Long> {

  protected Map<Long, T> map = new HashMap<>();
  private Long id = 0L;

  public T save(T entity) {
	if (entity.getId() == null || !map.containsKey(entity.getId())) {
	  setId(entity);
	}
	map.put(entity.getId(), entity);
	return entity;
  }

  public Optional<T> findById(Long id) {
	return Optional.ofNullable(map.get(id));
  }

  public Long deleteAllById(Long id) {
	return map.remove(id) == null ? 0L : 1L;
  }

  private void setId(T entity) {
	entity.setId(++id);
  }
}

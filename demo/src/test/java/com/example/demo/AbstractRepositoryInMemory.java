package com.example.demo;

import com.example.demo.commons.AbstractEntity;
import java.util.HashMap;
import java.util.Map;
import org.springframework.data.repository.Repository;

public abstract class AbstractRepositoryInMemory<T extends AbstractEntity> implements
	Repository<T, Long> {

  protected Map<Long, T> map = new HashMap<>();
  private Long id = 0L;

  protected T save(T entity) {
	setId(entity);
	map.put(id, entity);
	return entity;
  }

  protected T findById(Long id) {
	return map.get(id);
  }

  protected Long count() {
	return (long) map.size();
  }

  protected void setId(T entity) {
	entity.setId(++id);
  }
}

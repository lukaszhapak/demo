package com.example.demo.common.repositoryInMemory

import com.example.demo.commons.AbstractEntity

abstract class AbstractInMemoryRepositoryAbstractEntity<T extends AbstractEntity> {

    private Map<Long, T> map = new HashMap<>()
    private Long id = 0L

    T save(T entity) {
        if (entity.getId() == null || !map.containsKey(entity.getId())) {
            setId(entity)
        }
        map.put(entity.getId(), entity)
        return entity
    }

    T findById(Long id) {
        map.get(id)
    }

    private void setId(T entity) {
        entity.setId(++this.id)
    }
}

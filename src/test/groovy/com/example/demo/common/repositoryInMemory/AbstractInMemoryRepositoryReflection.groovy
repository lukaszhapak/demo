package com.example.demo.common.repositoryInMemory


import static org.springframework.test.util.ReflectionTestUtils.getField
import static org.springframework.test.util.ReflectionTestUtils.setField

abstract class AbstractInMemoryRepositoryReflection {

    private Map<Long, Object> map = new HashMap<>()
    private Long id = 0L

    Object save(Object entity) {
        Long id = getField(entity, "id") as Long
        if (id == null || !map.containsKey(id)) {
            setId(entity)
        }
        map.put(id, entity)
        return entity
    }

    Object findById(Long id) {
        map.get(id)
    }

    Object findBy(String fieldName, Object value) {
        map.values().stream()
                .filter { it -> getField(it, fieldName) == value }
                .findFirst().orElse(null)
    }

    private void setId(Object entity) {
        setField(entity, "id", ++this.id)
    }
}

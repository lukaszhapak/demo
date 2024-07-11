package com.example.demo.test.unit.inMemoryImplementation.inMemoryImplementation

class InMemoryStudentRepository implements StudentRepository {

    private Map<Long, Student> map = [:]
    private Long id = 0L

    @Override
    Student save(Student student) {
        if (student.getId() == null || !map.containsKey(student.getId())) {
            setId(student)
        }
        map.put(student.getId(), student)
        return student
    }

    @Override
    Student findById(Long id) {
        map.get(id)
    }

    @Override
    Student findByName(String name) {
        map.values().stream()
                .filter { it -> it.getName() == name }
                .findFirst().orElse(null)
    }

    private void setId(Student student) {
        student.setId(++this.id)
    }
}

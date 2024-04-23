package com.example.demo.test.unit.inMemoryImplementation.inMemoryImplementation

class StudentInMemoryRepository implements StudentRepository {

    private Map<Long, Student> map = new HashMap<>()
    private Long id = 0L

    Student save(Student student) {
        if (student.getId() == null || !map.containsKey(student.getId())) {
            setId(student)
        }
        map.put(student.getId(), student)
        return student
    }

    Student findById(Long id) {
        map.get(id)
    }

    Student findByName(String name) {
        map.values().stream()
                .filter { it -> it.getName() == name }
                .findFirst().orElse(null)
    }

    private void setId(Student student) {
        student.setId(++this.id)
    }
}

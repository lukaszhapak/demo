package com.example.demo.test.unit.pitest

import com.example.demo.common.repositoryInMemory.AbstractInMemoryRepositoryReflection

class StudentInMemoryRepository extends AbstractInMemoryRepositoryReflection implements StudentRepository {

    @Override
    Student save(Student student) {
        super.save(student) as Student
    }

    Student findById(Long id) {
        findBy("id", id) as Student
    }

    Student findByName(String name) {
        findBy("name", name) as Student
    }
}

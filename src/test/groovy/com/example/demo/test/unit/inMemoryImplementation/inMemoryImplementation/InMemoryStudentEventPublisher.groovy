package com.example.demo.test.unit.inMemoryImplementation.inMemoryImplementation

class InMemoryStudentEventPublisher implements StudentEventPublisher {

    List<Student> events = []

    @Override
    void publishStudentSavedEvent(Student student) {
        events.add(student)
    }
}

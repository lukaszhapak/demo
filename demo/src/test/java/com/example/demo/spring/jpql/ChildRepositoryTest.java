package com.example.demo.spring.jpql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ChildRepositoryTest extends AbstractIntegrationTest {

  @Autowired
  ChildRepository childRepository;

  @Autowired
  ParentRepository parentRepository;

  @Test
  @DisplayName("should test repository")
  void shouldTestRepository() {
	Child child = new Child();
	child.setName("John");
	Parent parent = new Parent();
	parent.setName("Jimmy");
	child.setParents(List.of(parent));

	Parent savedParent = parentRepository.save(parent);
	Child savedChild = childRepository.save(child);
	parent.setChild(child);
	parentRepository.save(parent);

	Child childById = childRepository.findById(savedChild.getId()).get();
	Parent parentById = parentRepository.findById(savedParent.getId()).get();

	assertThat(childById.getId()).isNotNull();
	assertThat(childById.getName()).isEqualTo("John");
	assertThat(childById.getParents().get(0).getName()).isEqualTo("Jimmy");
  }
}
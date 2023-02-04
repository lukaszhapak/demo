package com.example.clinic.commons;

import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public interface PageRequestHelper {

  int PAGE_SIZE_DEFAULT = 20;
  int PAGE_SIZE_MAX = 20;
  String defaultProperty = "id";

  static PageRequest getPageRequest(Integer page, Integer size, String orderBy, Boolean ascOrder) {
	Integer finalPage = Optional.ofNullable(page).filter(value -> value >= 0).orElse(0);
	Integer finalSize = Optional.ofNullable(size)
		.map(value -> value < 0 ? PAGE_SIZE_DEFAULT : value)
		.map(value -> value > PAGE_SIZE_MAX ? PAGE_SIZE_MAX : value)
		.orElse(PAGE_SIZE_DEFAULT);
	return PageRequest.of(finalPage, finalSize, getSort(orderBy, ascOrder));
  }

  private static Sort getSort(String property, Boolean ascOrder) {
	String sortBy = Optional.ofNullable(property).orElse(defaultProperty);
	Sort.Direction direction = Optional.ofNullable(ascOrder).filter(asc -> !asc)
		.map(asc -> Sort.Direction.DESC)
		.orElse(Sort.Direction.ASC);
	return Sort.by(direction, sortBy);
  }

}
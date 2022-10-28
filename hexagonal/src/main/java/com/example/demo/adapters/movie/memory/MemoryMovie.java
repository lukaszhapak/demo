package com.example.demo.adapters.movie.memory;

import com.example.demo.domain.movie.Movie;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class MemoryMovie {

  private Long id;
  private String title;
  private String author;

  Movie toMovie() {
	Movie movie = new Movie();
	movie.setId(id);
	movie.setTitle(title);
	movie.setAuthor(author);
	return movie;
  }
}

package com.example.batch.config;

import com.example.batch.core.model.Entry;
import com.example.batch.core.repository.EntryRepository;
import java.util.List;
import java.util.Optional;

public interface TestEntryRepository extends EntryRepository {

  List<Entry> findAllById(Iterable<Long> ids);

  void deleteAllById(Iterable<? extends Long> ids);

  void deleteById(Long id);

  Optional<Entry> findById(Long id);
}

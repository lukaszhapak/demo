package com.example.generated.generated.service;

import com.example.generated.generated.entity.Generated;
import com.example.generated.generated.repository.GeneratedRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneratedService {

    private final GeneratedRepository generatedRepository;

    public List<Generated> findAll() {
        return generatedRepository.findAll();
    }

    public Generated findById(long id) {
        return generatedRepository.findById(id).orElse(null);
    }

    public void save(Generated generated) {
        generatedRepository.save(generated);
    }

    public void deleteById(long id) {
        generatedRepository.deleteById(id);
    }
}


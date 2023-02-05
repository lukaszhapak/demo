package com.example.generated.generated.controller;

import com.example.generated.generated.entity.Generated;
import com.example.generated.generated.service.GeneratedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generated")
@RequiredArgsConstructor
public class GeneratedController {

    private final GeneratedService generatedService;

    @GetMapping
    public List<Generated> findAll() {
        return generatedService.findAll();
    }

    @GetMapping("/{id}")
    public Generated findById(@PathVariable int id) {
        return generatedService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Generated generated) {
        generatedService.save(generated);
    }

    @PutMapping
    public void update(@RequestBody Generated generated) {
        generatedService.save(generated);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        generatedService.deleteById(id);
    }
}


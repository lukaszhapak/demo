package com.example.demo.spring.modules.batch.config

import com.example.demo.spring.modules.batch.core.model.Entry
import org.springframework.data.jpa.repository.JpaRepository

interface SpecEntryRepository extends JpaRepository<Entry, Long> {

}
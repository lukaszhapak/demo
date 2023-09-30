package com.example.demo.outbox;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface OutboxRepository extends JpaRepository<Outbox, Long> {

  List<Outbox> findBySent(boolean sent);

}

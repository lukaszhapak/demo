package com.example.demo.test.integration.gameEngine.modules.draw.domain;

import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawStatus;
import org.springframework.data.repository.Repository;

interface DrawRepository extends Repository<Draw, Long> {

  int countByProductId(int productId);

  Draw save(Draw draw);

  Draw findFirstByProductIdAndStatusOrderByDrawNumberAsc(int productId, DrawStatus drawStatus);

  Draw findFirstByProductIdAndStatusOrderByDrawNumberDesc(int productId, DrawStatus open);

  Draw findByProductIdAndDrawNumber(int productId, int drawNumber);
}

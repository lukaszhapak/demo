package com.example.demo.test.integration.gameEngine.modules.draw.domain;

import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawStatus;

interface DrawRepository {

  int countByProductId(int productId);

  Draw save(Draw draw);


  Draw findFirstByProductIdAndStatus(int productId, DrawStatus drawStatus);

  Draw findByProductIdAndDrawNumber(int productId, int drawNumber);
}

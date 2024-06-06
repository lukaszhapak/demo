package com.example.demo.test.integration.gameEngine.modules.draw.domain;

import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class DrawFindService {

  private final DrawRepository drawRepository;

  Draw findDraw(int productId, int drawNumber) {
	return getDraw(productId, drawNumber);
  }

  private Draw getDraw(int productId, int drawNumber) {
	Draw draw = drawRepository.findByProductIdAndDrawNumber(productId, drawNumber);
	if (draw == null) {
	  throw new DrawNotFoundException("Draw does not exists");
	}
	return draw;
  }
}

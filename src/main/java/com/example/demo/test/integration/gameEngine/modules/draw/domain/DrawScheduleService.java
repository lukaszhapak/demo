package com.example.demo.test.integration.gameEngine.modules.draw.domain;

import static com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawStatus.OPEN;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class DrawScheduleService {

  private final DrawRepository drawRepository;

  void scheduleDraws(int productId, int numberOfDrawsToSchedule) {
	for (int i = 0; i < numberOfDrawsToSchedule; i++) {
	  Draw draw = createDraw(productId, i);
	  drawRepository.save(draw);
	}
  }

  int getCountOfScheduledDraws(int productId) {
	return drawRepository.countByProductId(productId);
  }

  private Draw createDraw(int productId, int i) {
	return new Draw()
		.setProductId(productId)
		.setDrawNumber(i + 1)
		.setStatus(OPEN);
  }
}

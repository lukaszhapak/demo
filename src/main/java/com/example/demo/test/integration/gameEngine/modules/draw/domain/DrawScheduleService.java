package com.example.demo.test.integration.gameEngine.modules.draw.domain;

import static com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawStatus.OPEN;

import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawLimitExceededException;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class DrawScheduleService {

  private final DrawRepository drawRepository;
  private final Map<Integer, Integer> scheduledDrawsPerGame;

  void scheduleDraws(int productId, int requestedNumberOfDrawsToSchedule) {
	validate(productId, requestedNumberOfDrawsToSchedule);
	int numberOfDrawsToSchedule = calculateNumberOfDrawsToSchedule(productId, requestedNumberOfDrawsToSchedule);
	Draw lastDraw = drawRepository.findFirstByProductIdAndStatusOrderByDrawNumberDesc(productId, OPEN);
	scheduleDraws(productId, numberOfDrawsToSchedule, lastDraw);
  }

  int getCountOfScheduledDraws(int productId) {
	return drawRepository.countByProductId(productId);
  }

  private void validate(int productId, int numberOfDrawsToSchedule) {
	if (numberOfDrawsToSchedule > scheduledDrawsPerGame.get(productId)) {
	  throw new DrawLimitExceededException("Requested number of draws is above the limit");
	}
  }

  private int calculateNumberOfDrawsToSchedule(int productId, int requestedNumberOfDrawsToSchedule) {
	int alreadyScheduledDraws = getCountOfScheduledDraws(productId);
	int numberOfDrawsToSchedule = requestedNumberOfDrawsToSchedule;
	int drawsLimit = scheduledDrawsPerGame.get(productId);
	while (numberOfDrawsToSchedule + alreadyScheduledDraws > drawsLimit) {
	  numberOfDrawsToSchedule--;
	}
	return numberOfDrawsToSchedule;
  }

  private void scheduleDraws(int productId, int numberOfDrawsToSchedule, Draw lastDraw) {
	for (int i = 0; i < numberOfDrawsToSchedule; i++) {
	  Draw draw = createDraw(productId, lastDraw);
	  drawRepository.save(draw);
	  lastDraw = draw;
	}
  }

  private Draw createDraw(int productId, Draw lastDraw) {
	return new Draw()
		.setProductId(productId)
		.setDrawNumber(lastDraw == null ? 1 : lastDraw.getDrawNumber() + 1)
		.setStatus(OPEN);
  }
}

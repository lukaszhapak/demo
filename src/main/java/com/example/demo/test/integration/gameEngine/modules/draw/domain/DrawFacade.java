package com.example.demo.test.integration.gameEngine.modules.draw.domain;

import static com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawStatus.OPEN;

import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawDTO;
import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawNotFoundException;
import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DrawFacade {

  private final DrawRepository drawRepository;
  private final DrawMapper drawMapper;

  public void scheduleDraws(int productId, int numberOfDrawsToSchedule) {
	for (int i = 0; i < numberOfDrawsToSchedule; i++) {
	  Draw draw = new Draw()
		  .setProductId(productId)
		  .setDrawNumber(i + 1)
		  .setStatus(OPEN);
	  drawRepository.save(draw);
	}
  }

  public int getCountOfScheduledDraws(int productId) {
	return drawRepository.countByProductId(productId);
  }

  public void closeDraw(int productId) {
	Draw draw = drawRepository.findFirstByProductIdAndStatus(productId, OPEN);
	if (draw == null) {
	  throw new DrawNotFoundException("Cannot close draw because there is no scheduled draws");
	}
	Draw nextDraw = drawRepository.findByProductIdAndDrawNumber(productId, draw.getDrawNumber() + 1);
	if (nextDraw == null) {
	  throw new DrawNotFoundException("Cannot close draw because there is no next draw");
	}
	draw.setStatus(DrawStatus.CLOSED);
  }

  public DrawDTO findDraw(int productId, int drawNumber) {
	Draw draw = drawRepository.findByProductIdAndDrawNumber(productId, drawNumber);
	if (draw == null) {
	  throw new DrawNotFoundException("Draw does not exists");
	}
	return drawMapper.toDTO(draw);
  }
}

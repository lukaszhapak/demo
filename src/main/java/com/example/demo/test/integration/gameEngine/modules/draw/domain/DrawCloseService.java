package com.example.demo.test.integration.gameEngine.modules.draw.domain;

import static com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawStatus.OPEN;

import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawNotFoundException;
import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class DrawCloseService {

  private final DrawRepository drawRepository;

  void closeDraw(int productId) {
	Draw draw = getFirstOpenDraw(productId);
	checkIfNextDrawExists(productId, draw);
	closeDraw(draw);
  }

  private Draw getFirstOpenDraw(int productId) {
	Draw draw = drawRepository.findFirstByProductIdAndStatus(productId, OPEN);
	if (draw == null) {
	  throw new DrawNotFoundException("Cannot close draw because there is no scheduled draws");
	}
	return draw;
  }

  private void checkIfNextDrawExists(int productId, Draw draw) {
	Draw nextDraw = drawRepository.findByProductIdAndDrawNumber(productId, draw.getDrawNumber() + 1);
	if (nextDraw == null) {
	  throw new DrawNotFoundException("Cannot close draw because there is no next draw");
	}
  }

  private static void closeDraw(Draw draw) {
	draw.setStatus(DrawStatus.CLOSED);
  }
}

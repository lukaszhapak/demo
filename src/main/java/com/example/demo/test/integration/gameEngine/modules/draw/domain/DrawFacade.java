package com.example.demo.test.integration.gameEngine.modules.draw.domain;

import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DrawFacade {

  private final DrawCloseService drawCloseService;
  private final DrawScheduleService drawScheduleService;
  private final DrawFindService drawFindService;
  private final DrawMapper drawMapper;

  public void scheduleDraws(int productId, int numberOfDrawsToSchedule) {
	drawScheduleService.scheduleDraws(productId, numberOfDrawsToSchedule);
  }

  public int getCountOfScheduledDraws(int productId) {
	return drawScheduleService.getCountOfScheduledDraws(productId);
  }

  public void closeDraw(int productId) {
	drawCloseService.closeDraw(productId);
  }

  public DrawDTO findDraw(int productId, int drawNumber) {
	return drawMapper.toDTO(drawFindService.findDraw(productId, drawNumber));
  }
}

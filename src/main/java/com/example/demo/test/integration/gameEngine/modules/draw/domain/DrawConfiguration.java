package com.example.demo.test.integration.gameEngine.modules.draw.domain;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DrawConfiguration {

  @Bean
  DrawFacade drawFacade(DrawRepository drawRepository, @Value("#{${game.engine.maxScheduledDrawsPerGame}}") Map<Integer, Integer> scheduledDrawsPerGame) {
	return new DrawFacade(drawCloseService(drawRepository),
		drawScheduleService(drawRepository, scheduledDrawsPerGame),
		drawFindService(drawRepository),
		drawMapper());
  }

  DrawCloseService drawCloseService(DrawRepository drawRepository) {
	return new DrawCloseService(drawRepository);
  }

  DrawScheduleService drawScheduleService(DrawRepository drawRepository, Map<Integer, Integer> scheduledDrawsPerGame) {
	return new DrawScheduleService(drawRepository, scheduledDrawsPerGame);
  }

  DrawFindService drawFindService(DrawRepository drawRepository) {
	return new DrawFindService(drawRepository);
  }

  DrawMapper drawMapper() {
	return new DrawMapper();
  }
}

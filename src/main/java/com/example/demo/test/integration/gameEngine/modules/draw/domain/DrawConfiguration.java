package com.example.demo.test.integration.gameEngine.modules.draw.domain;

class DrawConfiguration {

  DrawFacade drawFacade(DrawRepository drawRepository) {
	return new DrawFacade(drawCloseService(drawRepository),
		drawScheduleService(drawRepository),
		drawFindService(drawRepository),
		drawMapper());
  }

  DrawCloseService drawCloseService(DrawRepository drawRepository) {
	return new DrawCloseService(drawRepository);
  }

  DrawScheduleService drawScheduleService(DrawRepository drawRepository) {
	return new DrawScheduleService(drawRepository);
  }

  DrawFindService drawFindService(DrawRepository drawRepository) {
	return new DrawFindService(drawRepository);
  }

  DrawMapper drawMapper() {
	return new DrawMapper();
  }
}

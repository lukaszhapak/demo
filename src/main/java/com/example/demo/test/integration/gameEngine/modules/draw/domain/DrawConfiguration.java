package com.example.demo.test.integration.gameEngine.modules.draw.domain;

class DrawConfiguration {

  DrawFacade drawFacade(DrawRepository drawRepository) {
	return new DrawFacade(drawRepository, drawMapper());
  }

  DrawMapper drawMapper() {
	return new DrawMapper();
  }
}

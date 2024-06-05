package com.example.demo.test.integration.gameEngine.modules.draw.domain;

import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawDTO;

class DrawMapper {

  public DrawDTO toDTO(Draw draw) {
	return new DrawDTO()
		.setProductId(draw.getProductId())
		.setDrawNumber(draw.getDrawNumber())
		.setStatus(draw.getStatus());
  }
}

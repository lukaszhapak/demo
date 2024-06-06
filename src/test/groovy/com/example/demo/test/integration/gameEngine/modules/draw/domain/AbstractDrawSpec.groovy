package com.example.demo.test.integration.gameEngine.modules.draw.domain

import spock.lang.Specification

abstract class AbstractDrawSpec extends Specification {

    Integer scheduledDrawsLimit = 50
    Map<Integer, Integer> scheduledDrawsPerGame = Map.of(8, scheduledDrawsLimit)
    DrawRepository drawRepository = new InMemoryDrawRepository()
    DrawFacade drawFacade = new DrawConfiguration().drawFacade(drawRepository, scheduledDrawsPerGame)
}

package com.example.demo.test.integration.gameEngine.modules.draw.domain

import spock.lang.Specification

abstract class AbstractDrawSpec extends Specification {

    DrawRepository drawRepository = new InMemoryDrawRepository()
    DrawFacade drawFacade = new DrawConfiguration().drawFacade(drawRepository)
}

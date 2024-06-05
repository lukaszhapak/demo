package com.example.demo.test.integration.gameEngine.modules.draw.domain

import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawNotFoundException
import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawStatus
import spock.lang.Specification

class DrawFacadeSpec extends Specification {

    DrawRepository drawRepository = new InMemoryDrawRepository()
    DrawFacade drawFacade = new DrawConfiguration().drawFacade(drawRepository)

    def "should schedule draws"() {
        given:
        int productId = 8
        int numberOfDrawsToSchedule = 20

        when:
        drawFacade.scheduleDraws(productId, numberOfDrawsToSchedule)

        then:
        drawFacade.getCountOfScheduledDraws(productId) == numberOfDrawsToSchedule
        drawFacade.findDraw(productId, 1).getStatus() == DrawStatus.OPEN
        drawFacade.findDraw(productId, 20).getStatus() == DrawStatus.OPEN
    }

    def "should throw exception when draw does not exist in the system"() {
        given:
        int productId = 8
        int drawNumber = 1

        when:
        drawFacade.findDraw(productId, drawNumber)

        then:
        DrawNotFoundException thrown = thrown()
        thrown.getMessage() == "Draw does not exists"
    }

    def "should close current draw"() {
        given:
        int productId = 8
        int drawNumber = 1
        drawFacade.scheduleDraws(productId, 3)

        when:
        drawFacade.closeDraw(productId)

        then:
        drawFacade.findDraw(productId, drawNumber).getStatus() == DrawStatus.CLOSED
    }

    def "should throw exception when there is no draw to close"() {
        given:
        int productId = 8

        when:
        drawFacade.closeDraw(productId)

        then:
        DrawNotFoundException thrown = thrown()
        thrown.getMessage() == "Cannot close draw because there is no scheduled draws"
    }

    def "should throw exception when there is no next draw during draw close"() {
        given:
        int productId = 8
        drawFacade.scheduleDraws(productId, 1)

        when:
        drawFacade.closeDraw(productId)

        then:
        DrawNotFoundException thrown = thrown()
        thrown.getMessage() == "Cannot close draw because there is no next draw"
    }
}

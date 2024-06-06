package com.example.demo.test.integration.gameEngine.modules.draw.domain


import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawLimitExceededException
import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawStatus

class DrawScheduleSpec extends AbstractDrawSpec {

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

    def "should not schedule any draws if there are already enough scheduled"() {
        given:
        int productId = 8

        when:
        drawFacade.scheduleDraws(productId, 30)
        drawFacade.scheduleDraws(productId, 30)

        then:
        drawFacade.getCountOfScheduledDraws(productId) == scheduledDrawsLimit
    }

    def "should not schedule with repeated draw number"() {
        given:
        int productId = 8

        when:
        drawFacade.scheduleDraws(productId, 10)
        drawFacade.scheduleDraws(productId, 10)

        then:
        drawFacade.findDraw(productId, 20) != null
    }

    def "should throw exception if number of draws to schedule is above limit"() {
        given:
        int productId = 8

        when:
        drawFacade.scheduleDraws(productId, scheduledDrawsLimit + 1)

        then:
        drawFacade.getCountOfScheduledDraws(productId) == 0
        DrawLimitExceededException thrown = thrown()
        thrown.getMessage() == "Requested number of draws is above the limit"
    }
}

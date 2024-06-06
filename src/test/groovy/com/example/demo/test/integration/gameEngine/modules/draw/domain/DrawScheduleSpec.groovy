package com.example.demo.test.integration.gameEngine.modules.draw.domain


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
}

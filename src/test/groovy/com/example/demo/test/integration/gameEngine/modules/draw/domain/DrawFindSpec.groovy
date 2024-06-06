package com.example.demo.test.integration.gameEngine.modules.draw.domain

import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawNotFoundException
import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawStatus

class DrawFindSpec extends AbstractDrawSpec {

    def "should get draw"() {
        given:
        int productId = 8

        when:
        drawFacade.scheduleDraws(productId, 1)

        then:
        drawFacade.findDraw(productId, 1).getStatus() == DrawStatus.OPEN
        drawFacade.findDraw(productId, 1).getProductId() == productId
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
}

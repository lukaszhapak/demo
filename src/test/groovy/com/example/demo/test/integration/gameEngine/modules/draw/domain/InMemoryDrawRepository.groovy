package com.example.demo.test.integration.gameEngine.modules.draw.domain

import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawStatus

class InMemoryDrawRepository implements DrawRepository {

    private Map<Long, Draw> map = [:]
    private Long id = 0L

    int countByProductId(int productId) {
        map.values().stream()
                .filter { it -> it.getProductId() == productId }
                .count()
    }

    Draw save(Draw draw) {
        if (draw.getId() == null || !map.containsKey(draw.getId())) {
            setId(draw)
        }
        map.put(draw.getId(), draw)
        draw
    }

    Draw findFirstByProductIdAndStatusOrderByDrawNumberAsc(int productId, DrawStatus drawStatus) {
        map.values().stream()
                .filter { it -> it.getProductId() == productId }
                .filter { it -> it.getStatus() == drawStatus }
                .findFirst().orElse(null)
    }

    Draw findByProductIdAndDrawNumber(int productId, int drawNumber) {
        map.values().stream()
                .filter { it -> it.getProductId() == productId }
                .filter { it -> it.getDrawNumber() == drawNumber }
                .findFirst().orElse(null)
    }

    Draw findFirstByProductIdAndStatusOrderByDrawNumberDesc(int productId, DrawStatus drawStatus) {
        map.values().stream()
                .filter { it -> it.getProductId() == productId }
                .filter { it -> it.getStatus() == drawStatus }
                .sorted((o1, o2) -> o2.getDrawNumber() <=> o1.getDrawNumber()).
                findFirst().orElse(null)
    }

    private void setId(Draw draw) {
        draw.setId(++this.id)
    }
}

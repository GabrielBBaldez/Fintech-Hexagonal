package br.com.gabriel.fintech.wallet_service.domain.port.out;

import java.util.UUID;

public interface OutboxEventPublisher {
    void publish(UUID aggregateId, String eventType, String payload);
}

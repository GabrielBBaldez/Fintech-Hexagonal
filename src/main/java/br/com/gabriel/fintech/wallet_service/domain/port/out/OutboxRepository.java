package br.com.gabriel.fintech.wallet_service.domain.port.out;

import br.com.gabriel.fintech.wallet_service.domain.event.DomainEvent;

import java.util.List;
import java.util.UUID;

public interface OutboxRepository {
    void saveAll(List<DomainEvent> events, UUID aggregateId);
}

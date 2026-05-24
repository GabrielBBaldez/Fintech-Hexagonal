package br.com.gabriel.fintech.wallet_service.adapter.out.persistence;

import br.com.gabriel.fintech.wallet_service.domain.event.DomainEvent;
import br.com.gabriel.fintech.wallet_service.domain.port.out.OutboxRepository;
import tools.jackson.core.JacksonException;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class OutboxPersistenceAdapter implements OutboxRepository {

    private final OutboxJpaRepository outboxJpaRepository;
    private final ObjectMapper objectMapper;

    public OutboxPersistenceAdapter(OutboxJpaRepository outboxJpaRepository, ObjectMapper objectMapper) {
        this.outboxJpaRepository = outboxJpaRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void saveAll(List<DomainEvent> events, UUID aggregateId) {
      List<OutboxJpaEntity> entities = new ArrayList<>();
      for(DomainEvent event: events){
          entities.add(toEntity(event,aggregateId));
      }
      outboxJpaRepository.saveAll(entities);
    }

    private OutboxJpaEntity toEntity(DomainEvent event, UUID aggregateId){
        try {
            String json = objectMapper.writeValueAsString(event);
            return new OutboxJpaEntity(
                    event.eventId(),
                    aggregateId,
                    event.getClass().getSimpleName(),
                    json);
        }catch (JacksonException e){
            throw new IllegalStateException("Failed to serialize event: " + event, e);
        }
    }
}

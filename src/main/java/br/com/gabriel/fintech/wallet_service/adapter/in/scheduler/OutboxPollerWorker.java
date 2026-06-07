package br.com.gabriel.fintech.wallet_service.adapter.in.scheduler;

import br.com.gabriel.fintech.wallet_service.adapter.out.persistence.OutboxJpaEntity;
import br.com.gabriel.fintech.wallet_service.adapter.out.persistence.OutboxJpaRepository;
import br.com.gabriel.fintech.wallet_service.domain.port.out.OutboxEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class OutboxPollerWorker {
   private final OutboxJpaRepository outboxJpaRepository;
   private final OutboxEventPublisher outboxEventPublisher;

    public OutboxPollerWorker(OutboxJpaRepository outboxJpaRepository, OutboxEventPublisher outboxEventPublisher) {
        this.outboxJpaRepository = outboxJpaRepository;
        this.outboxEventPublisher = outboxEventPublisher;
    }

    @Scheduled(fixedDelay = 1000)
    public void pollAndPublish() {
        List<OutboxJpaEntity> pending = outboxJpaRepository.findByPublishedAtIsNullOrderByCreatedAtAsc();
        for(OutboxJpaEntity entity : pending){
            outboxEventPublisher.publish(entity.getAggregateId(),entity.getEventType(),entity.getPayload());
            entity.setPublishedAt(Instant.now());
            outboxJpaRepository.save(entity);
        }
    }
}

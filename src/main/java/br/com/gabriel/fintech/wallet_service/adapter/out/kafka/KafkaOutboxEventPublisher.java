package br.com.gabriel.fintech.wallet_service.adapter.out.kafka;

import br.com.gabriel.fintech.wallet_service.domain.port.out.OutboxEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class KafkaOutboxEventPublisher implements OutboxEventPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "wallet-events";

    public KafkaOutboxEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(UUID aggregateId, String eventType, String payload) {
             kafkaTemplate.send(TOPIC, aggregateId.toString(), payload);
    }
}

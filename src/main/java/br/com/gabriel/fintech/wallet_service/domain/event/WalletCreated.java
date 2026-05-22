package br.com.gabriel.fintech.wallet_service.domain.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record WalletCreated(UUID eventId,
                            Instant occurredAt,
                            int schemaVersion,
                            UUID walletId,
                            String ownerId,
                            BigDecimal initialBalance)
        implements DomainEvent {
    public static final int CURRENT_VERSION = 1;

    public WalletCreated{
        if(eventId == null){ throw new IllegalArgumentException("eventId cannot be null");}
        if(occurredAt == null){ throw new IllegalArgumentException("occurredAt cannot be null");}
        if(schemaVersion < 1){ throw new IllegalArgumentException("schemaVersion must be >= 1");}
        if(walletId == null){ throw new IllegalArgumentException("walletId cannot be null");}
        if(ownerId == null || ownerId.isBlank()){ throw new IllegalArgumentException("ownerId cannot be null or blank");}
        if(initialBalance == null){ throw new IllegalArgumentException("initialBalance cannot be null");}
        if(initialBalance.signum() < 0){ throw new IllegalArgumentException("initialBalance cannot be negative");}
    }
}

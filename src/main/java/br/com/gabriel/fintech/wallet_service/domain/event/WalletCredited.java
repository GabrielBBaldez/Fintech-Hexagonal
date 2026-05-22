package br.com.gabriel.fintech.wallet_service.domain.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record WalletCredited(UUID eventId,
                             Instant occurredAt,
                             int schemaVersion,
                             UUID walletId,
                             BigDecimal amount,
                             BigDecimal balanceAfter) implements DomainEvent {
    public static final int CURRENT_VERSION = 1;

    public WalletCredited{
        if(eventId == null){ throw new IllegalArgumentException("eventId cannot be null");}
        if(occurredAt == null){ throw new IllegalArgumentException("occurredAt cannot be null");}
        if(schemaVersion < 1){ throw new IllegalArgumentException("schemaVersion must be >= 1");}
        if(walletId == null){ throw new IllegalArgumentException("walletId cannot be null");}
        if(amount == null){ throw new IllegalArgumentException("amount cannot be null");}
        if(amount.signum() <= 0){ throw new IllegalArgumentException("amount cannot be negative");}
        if(balanceAfter == null){ throw new IllegalArgumentException("balanceAfter cannot be null");}
        if(balanceAfter.signum() < 0){ throw new IllegalArgumentException("balanceAfter cannot be negative");}
    }
}

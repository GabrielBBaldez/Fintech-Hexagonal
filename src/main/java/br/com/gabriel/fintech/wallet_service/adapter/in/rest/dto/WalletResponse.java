package br.com.gabriel.fintech.wallet_service.adapter.in.rest.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletResponse(
        UUID id,
        String ownerId,
        BigDecimal balance,
        long version
) {}
package br.com.gabriel.fintech.wallet_service.adapter.in.rest.mapper;

import br.com.gabriel.fintech.wallet_service.adapter.in.rest.dto.WalletResponse;
import br.com.gabriel.fintech.wallet_service.domain.model.Money;
import br.com.gabriel.fintech.wallet_service.domain.model.Wallet;
import br.com.gabriel.fintech.wallet_service.domain.model.WalletId;

import java.math.BigDecimal;
import java.util.UUID;

public final class WalletDtoMapper {

    private WalletDtoMapper() {
    }

    public static Money toMoney(BigDecimal amount){
        return new Money(amount);
    }

    public static WalletId toWalletId(UUID id){
        return new WalletId(id);
    }

    public static WalletResponse toResponse(Wallet wallet){
        return new WalletResponse(
                wallet.getId().value(),
                wallet.getOwnerId(),
                wallet.getBalance().amount(),
                wallet.getVersion());
    }
}

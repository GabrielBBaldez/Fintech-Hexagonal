package br.com.gabriel.fintech.wallet_service.adapter.out.persistence.mapper;

import br.com.gabriel.fintech.wallet_service.adapter.out.persistence.WalletJpaEntity;
import br.com.gabriel.fintech.wallet_service.domain.model.Money;
import br.com.gabriel.fintech.wallet_service.domain.model.Wallet;
import br.com.gabriel.fintech.wallet_service.domain.model.WalletId;

public final class WalletPersistenceMapper {

    private WalletPersistenceMapper() {
    }

    public static WalletJpaEntity toEntity(Wallet wallet){
        return new WalletJpaEntity(
                wallet.getWalletId().value(),
                wallet.getOwnerId(),
                wallet.getBalance().amount(),
                wallet.getVersion(),
                null,
                null
        );
    }

    public static Wallet toDomain(WalletJpaEntity entity){
        return Wallet.restore(
                new WalletId(entity.getId()),
                entity.getOwnerId(),
                new Money(entity.getBalance()),
                entity.getVersion());
    }
}

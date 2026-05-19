package br.com.gabriel.fintech.wallet_service.domain.port.out;

import br.com.gabriel.fintech.wallet_service.domain.model.Wallet;
import br.com.gabriel.fintech.wallet_service.domain.model.WalletId;

import java.util.Optional;

public interface WalletRepository {

    Optional<Wallet> findById(WalletId id);

    Wallet save(Wallet wallet);
}

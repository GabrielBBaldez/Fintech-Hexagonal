package br.com.gabriel.fintech.wallet_service.domain.port.in;

import br.com.gabriel.fintech.wallet_service.domain.model.Wallet;
import br.com.gabriel.fintech.wallet_service.domain.model.WalletId;

public interface GetWalletUseCase {
    Wallet execute(WalletId walletId);
}

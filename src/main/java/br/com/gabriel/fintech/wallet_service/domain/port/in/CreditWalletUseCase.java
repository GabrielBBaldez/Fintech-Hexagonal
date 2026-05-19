package br.com.gabriel.fintech.wallet_service.domain.port.in;

import br.com.gabriel.fintech.wallet_service.domain.model.Money;
import br.com.gabriel.fintech.wallet_service.domain.model.Wallet;
import br.com.gabriel.fintech.wallet_service.domain.model.WalletId;

public interface CreditWalletUseCase {

    Wallet execute(WalletId walletId, Money amount);
}

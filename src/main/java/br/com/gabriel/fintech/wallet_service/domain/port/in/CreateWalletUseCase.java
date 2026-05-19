package br.com.gabriel.fintech.wallet_service.domain.port.in;

import br.com.gabriel.fintech.wallet_service.domain.model.Money;
import br.com.gabriel.fintech.wallet_service.domain.model.Wallet;

public interface CreateWalletUseCase {

    Wallet execute(String ownerId, Money initialBalance);
}

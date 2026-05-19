package br.com.gabriel.fintech.wallet_service.application.service;

import br.com.gabriel.fintech.wallet_service.domain.exception.WalletNotFoundException;
import br.com.gabriel.fintech.wallet_service.domain.model.Money;
import br.com.gabriel.fintech.wallet_service.domain.model.Wallet;
import br.com.gabriel.fintech.wallet_service.domain.model.WalletId;
import br.com.gabriel.fintech.wallet_service.domain.port.in.DebitWalletUseCase;
import br.com.gabriel.fintech.wallet_service.domain.port.out.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class DebitWalletService implements DebitWalletUseCase {

    private final WalletRepository repository;

    public DebitWalletService(WalletRepository repository) {
        this.repository = repository;
    }

    @Override
    public Wallet execute(WalletId walletId, Money amount) {
        Wallet wallet = repository.findById(walletId).orElseThrow(() -> new WalletNotFoundException(walletId.value()));
        wallet.debit(amount);
        return repository.save(wallet);
    }
}

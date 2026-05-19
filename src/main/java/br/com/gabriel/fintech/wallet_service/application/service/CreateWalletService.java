package br.com.gabriel.fintech.wallet_service.application.service;

import br.com.gabriel.fintech.wallet_service.domain.model.Money;
import br.com.gabriel.fintech.wallet_service.domain.model.Wallet;
import br.com.gabriel.fintech.wallet_service.domain.port.in.CreateWalletUseCase;
import br.com.gabriel.fintech.wallet_service.domain.port.out.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateWalletService implements CreateWalletUseCase {

    private final WalletRepository repository;

    public CreateWalletService(WalletRepository repository) {
        this.repository = repository;
    }

    @Override
    public Wallet execute(String ownerId, Money initialBalance) {
        return repository.save(Wallet.create(ownerId,initialBalance));
    }
}

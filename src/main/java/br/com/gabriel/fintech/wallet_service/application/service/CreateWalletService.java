package br.com.gabriel.fintech.wallet_service.application.service;

import br.com.gabriel.fintech.wallet_service.domain.model.Money;
import br.com.gabriel.fintech.wallet_service.domain.model.Wallet;
import br.com.gabriel.fintech.wallet_service.domain.port.in.CreateWalletUseCase;
import br.com.gabriel.fintech.wallet_service.domain.port.out.OutboxRepository;
import br.com.gabriel.fintech.wallet_service.domain.port.out.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateWalletService implements CreateWalletUseCase {

    private final WalletRepository repository;
    private final OutboxRepository outboxRepository;

    public CreateWalletService(WalletRepository repository, OutboxRepository outboxRepository) {
        this.repository = repository;
        this.outboxRepository = outboxRepository;
    }

    @Transactional
    @Override
    public Wallet execute(String ownerId, Money initialBalance) {
      Wallet wallet =  Wallet.create(ownerId,initialBalance);
      Wallet saved = repository.save(wallet);
      outboxRepository.saveAll(wallet.pollEvents(),wallet.getId().value());
      return saved;
    }
}

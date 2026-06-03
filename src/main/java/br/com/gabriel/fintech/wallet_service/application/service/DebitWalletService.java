package br.com.gabriel.fintech.wallet_service.application.service;

import br.com.gabriel.fintech.wallet_service.domain.exception.WalletNotFoundException;
import br.com.gabriel.fintech.wallet_service.domain.model.Money;
import br.com.gabriel.fintech.wallet_service.domain.model.Wallet;
import br.com.gabriel.fintech.wallet_service.domain.model.WalletId;
import br.com.gabriel.fintech.wallet_service.domain.port.in.DebitWalletUseCase;
import br.com.gabriel.fintech.wallet_service.domain.port.out.OutboxRepository;
import br.com.gabriel.fintech.wallet_service.domain.port.out.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DebitWalletService implements DebitWalletUseCase {

    private final WalletRepository repository;
    private final OutboxRepository outboxRepository;

    public DebitWalletService(WalletRepository repository, OutboxRepository outboxRepository) {
        this.repository = repository;
        this.outboxRepository = outboxRepository;
    }

    @Transactional
    @Override
    public Wallet execute(WalletId walletId, Money amount) {
        Wallet wallet = repository.findById(walletId).orElseThrow(() -> new WalletNotFoundException(walletId.value()));
        wallet.debit(amount);
        Wallet saved = repository.save(wallet);
        outboxRepository.saveAll(wallet.pollEvents(),wallet.getId().value());
        return saved;
    }
}

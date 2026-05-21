package br.com.gabriel.fintech.wallet_service.application.service;

import br.com.gabriel.fintech.wallet_service.domain.exception.WalletNotFoundException;
import br.com.gabriel.fintech.wallet_service.domain.model.Wallet;
import br.com.gabriel.fintech.wallet_service.domain.model.WalletId;
import br.com.gabriel.fintech.wallet_service.domain.port.in.GetWalletUseCase;
import br.com.gabriel.fintech.wallet_service.domain.port.out.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class GetWalletService implements GetWalletUseCase {

    private final WalletRepository repository;

    public GetWalletService(WalletRepository repository) {
        this.repository = repository;
    }

    @Override
    public Wallet execute(WalletId walletId) {
        return repository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException(walletId.value()));
    }
}

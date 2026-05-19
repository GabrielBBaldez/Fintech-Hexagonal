package br.com.gabriel.fintech.wallet_service.domain.exception;

import java.util.UUID;

public class WalletNotFoundException extends RuntimeException {

    private final UUID walletId;

    public WalletNotFoundException(UUID walletId) {
        super("Wallet not found: %s".formatted(walletId));
        this.walletId = walletId;
    }

    public UUID getWalletId() { return walletId; }
}

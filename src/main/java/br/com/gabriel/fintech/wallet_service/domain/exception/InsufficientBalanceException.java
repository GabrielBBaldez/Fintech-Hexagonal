package br.com.gabriel.fintech.wallet_service.domain.exception;

import br.com.gabriel.fintech.wallet_service.domain.model.Money;

import java.util.UUID;

public class InsufficientBalanceException extends RuntimeException {

    private final UUID walletId;
    private final Money currentBalance;
    private final Money attemptAmount;

    public InsufficientBalanceException (UUID walletId, Money currentBalance, Money attemptAmount){
        super("Insufficient balance on wallet %s: current=%s, attempted=%s"
                .formatted(walletId, currentBalance, attemptAmount));
      this.walletId = walletId;
      this.attemptAmount = attemptAmount;
      this.currentBalance = currentBalance;
    }

    public UUID getWalletId() { return walletId; }
    public Money getCurrentBalance() { return currentBalance; }
    public Money getAttemptAmount() { return attemptAmount; }
}

package br.com.gabriel.fintech.wallet_service.domain.model;

import br.com.gabriel.fintech.wallet_service.domain.exception.InsufficientBalanceException;

public class Wallet {

    private final WalletId walletId;
    private final String ownerId;
    private Money balance;
    private long version;

    private Wallet(WalletId walletId, String ownerId, Money balance, long version) {
        if (walletId == null) {
            throw new IllegalArgumentException("WalletId cannot be null");
        }

        if (ownerId == null || ownerId.isBlank()) {
            throw new IllegalArgumentException("OwnerId cannot be null or empty");
        }

        if (balance == null) {
            throw new IllegalArgumentException("Balance  cannot be null");
        }

        this.walletId = walletId;
        this.ownerId = ownerId;
        this.balance = balance;
        this.version = version;
    }

    public static Wallet create(String ownerId, Money initialBalance) {
        return new Wallet(WalletId.newId(), ownerId, initialBalance, 0L);
    }

    public static Wallet restore(WalletId walletId, String ownerId, Money balance, long version) {
        return new Wallet(walletId, ownerId, balance, version);
    }

    public void debit(Money amount) {
        if (this.balance.isLessThan(amount)) {
            throw new InsufficientBalanceException(
                    this.walletId.value(),
                    this.balance,
                    amount
            );
        }
       this.balance = this.balance.subtract(amount);
    }

    public void credit(Money amount){
        this.balance = this.balance.add(amount);
    }

    public WalletId getWalletId(){ return walletId;}
    public String getOwnerId(){ return ownerId;}
    public Money getBalance(){return balance;}
    public long getVersion(){return version;}
}

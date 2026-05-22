package br.com.gabriel.fintech.wallet_service.domain.model;

import br.com.gabriel.fintech.wallet_service.domain.event.DomainEvent;
import br.com.gabriel.fintech.wallet_service.domain.event.WalletCreated;
import br.com.gabriel.fintech.wallet_service.domain.event.WalletCredited;
import br.com.gabriel.fintech.wallet_service.domain.event.WalletDebited;
import br.com.gabriel.fintech.wallet_service.domain.exception.InsufficientBalanceException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Wallet {

    private final WalletId walletId;
    private final String ownerId;
    private Money balance;
    private long version;
    private final List<DomainEvent> pendingEvents = new ArrayList<>();

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
        Wallet w = new Wallet(WalletId.newId(), ownerId, initialBalance, 0L);
        w.registerEvent(new WalletCreated(
                UUID.randomUUID(),
                Instant.now(),
                WalletCreated.CURRENT_VERSION,
                w.walletId.value(),
                ownerId,
                initialBalance.amount()
        ));
        return w;
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
        this.registerEvent(new WalletDebited(
                UUID.randomUUID(),
                Instant.now(),
                WalletDebited.CURRENT_VERSION,
                this.walletId.value(),
                amount.amount(),
                this.balance.amount()
        ));
    }

    public void credit(Money amount){
        this.balance = this.balance.add(amount);
        this.registerEvent(new WalletCredited(
                UUID.randomUUID(),
                Instant.now(),
                WalletCredited.CURRENT_VERSION,
                this.walletId.value(),
                amount.amount(),
                this.balance.amount()
        ));
    }

    public List<DomainEvent> pollEvents(){
        List<DomainEvent> copy = List.copyOf(pendingEvents);
        pendingEvents.clear();
        return copy;
    }

    private void registerEvent(DomainEvent event){
        this.pendingEvents.add(event);
    }

    public WalletId getId(){ return walletId;}
    public String getOwnerId(){ return ownerId;}
    public Money getBalance(){return balance;}
    public long getVersion(){return version;}
}

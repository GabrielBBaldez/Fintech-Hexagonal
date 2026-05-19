package br.com.gabriel.fintech.wallet_service.domain.model;

import java.util.UUID;

public record WalletId(UUID value) {

    public WalletId{
        if (value == null){
            throw new IllegalArgumentException("WalletId cannot be null");
        }
    }

    public static WalletId newId(){
        return new WalletId(UUID.randomUUID());
    }

    public static WalletId of(String value){
        return new WalletId(UUID.fromString(value));
    }
}

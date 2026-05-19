package br.com.gabriel.fintech.wallet_service.domain.model;

import java.math.BigDecimal;

public record Money (BigDecimal amount) {

    public Money{
       if (amount == null){
           throw new IllegalArgumentException("amount cannot be null");
       }
       if (amount.scale() > 2) {
           throw new IllegalArgumentException("Money cannot have more than 2 decimal places:" + amount);
       }
    }

    public static Money of(String value){
        return new Money(new BigDecimal(value));
    }

    public static Money zero(){
        return new Money(BigDecimal.ZERO);
    }

    public Money add(Money other){
        return new Money(this.amount.add(other.amount));
    }

    public Money subtract(Money other){
        return new Money(this.amount.subtract(other.amount));
    }

    public boolean isLessThan(Money other){
        return this.amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThanOrEqual(Money other){
        return this.amount.compareTo(other.amount) >= 0;
    }

    public boolean isNegative(){
        return this.amount.signum() < 0;
    }
}

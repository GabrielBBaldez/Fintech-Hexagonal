package br.com.gabriel.fintech.wallet_service.adapter.in.rest.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;


public record CreateWalletRequest(
        @NotBlank String ownerId,
        @NotNull @DecimalMin("0.00") BigDecimal initialBalance
) {}

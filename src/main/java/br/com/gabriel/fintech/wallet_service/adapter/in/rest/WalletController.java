package br.com.gabriel.fintech.wallet_service.adapter.in.rest;

import br.com.gabriel.fintech.wallet_service.adapter.in.rest.dto.AmountRequest;
import br.com.gabriel.fintech.wallet_service.adapter.in.rest.dto.CreateWalletRequest;
import br.com.gabriel.fintech.wallet_service.adapter.in.rest.dto.WalletResponse;
import br.com.gabriel.fintech.wallet_service.adapter.in.rest.mapper.WalletDtoMapper;
import br.com.gabriel.fintech.wallet_service.domain.model.Money;
import br.com.gabriel.fintech.wallet_service.domain.model.Wallet;
import br.com.gabriel.fintech.wallet_service.domain.model.WalletId;
import br.com.gabriel.fintech.wallet_service.domain.port.in.CreateWalletUseCase;
import br.com.gabriel.fintech.wallet_service.domain.port.in.CreditWalletUseCase;
import br.com.gabriel.fintech.wallet_service.domain.port.in.DebitWalletUseCase;
import br.com.gabriel.fintech.wallet_service.domain.port.in.GetWalletUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final CreateWalletUseCase createWalletUseCase;
    private final GetWalletUseCase getWalletUseCase;
    private final DebitWalletUseCase debitWalletUseCase;
    private final CreditWalletUseCase creditWalletUseCase;


    public WalletController(CreateWalletUseCase createWalletUseCase, GetWalletUseCase getWalletUseCase, DebitWalletUseCase debitWalletUseCase, CreditWalletUseCase creditWalletUseCase) {
        this.createWalletUseCase = createWalletUseCase;
        this.getWalletUseCase = getWalletUseCase;
        this.debitWalletUseCase = debitWalletUseCase;
        this.creditWalletUseCase = creditWalletUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WalletResponse create(@Valid @RequestBody CreateWalletRequest request) {
        Money initialBalance = WalletDtoMapper.toMoney(request.initialBalance());
        Wallet wallet = createWalletUseCase.execute(request.ownerId(), initialBalance);
        return WalletDtoMapper.toResponse(wallet);
    }

    @GetMapping("/{id}")
    public WalletResponse get(@PathVariable UUID id) {
        WalletId walletId = WalletDtoMapper.toWalletId(id);
        Wallet wallet = getWalletUseCase.execute(walletId);
        return WalletDtoMapper.toResponse(wallet);
    }

    @PostMapping("/{id}/debit")
    public WalletResponse debit(@PathVariable UUID id,@PathVariable AmountRequest request) {
        WalletId walletId = WalletDtoMapper.toWalletId(id);
        Money amount = WalletDtoMapper.toMoney(request.amount());
        Wallet wallet = debitWalletUseCase.execute(walletId, amount);
        return WalletDtoMapper.toResponse(wallet);
    }

    @PostMapping("/{id}/credit")
    public WalletResponse credit(@PathVariable UUID id, @Valid @RequestBody AmountRequest request) {
        WalletId walletId = WalletDtoMapper.toWalletId(id);
        Money amount = WalletDtoMapper.toMoney(request.amount());
        Wallet wallet = creditWalletUseCase.execute(walletId, amount);
        return WalletDtoMapper.toResponse(wallet);
    }


}

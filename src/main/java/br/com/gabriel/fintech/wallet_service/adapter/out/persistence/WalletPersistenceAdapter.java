package br.com.gabriel.fintech.wallet_service.adapter.out.persistence;

import br.com.gabriel.fintech.wallet_service.adapter.out.persistence.mapper.WalletPersistenceMapper;
import br.com.gabriel.fintech.wallet_service.domain.model.Wallet;
import br.com.gabriel.fintech.wallet_service.domain.model.WalletId;
import br.com.gabriel.fintech.wallet_service.domain.port.out.WalletRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WalletPersistenceAdapter implements WalletRepository {

    private final WalletJpaRepository jpaRepository;

    public WalletPersistenceAdapter(WalletJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }


    @Override
    public Optional<Wallet> findById(WalletId id) {
        return jpaRepository.findById(id.value())
                .map(WalletPersistenceMapper::toDomain);
    }

    @Override
    public Wallet save(Wallet wallet) {
       WalletJpaEntity entity = WalletPersistenceMapper.toEntity(wallet);
       WalletJpaEntity saved = jpaRepository.save(entity);
       return WalletPersistenceMapper.toDomain(saved);
    }
}

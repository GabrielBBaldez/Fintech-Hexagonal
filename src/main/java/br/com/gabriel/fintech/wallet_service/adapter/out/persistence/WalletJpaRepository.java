package br.com.gabriel.fintech.wallet_service.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletJpaRepository extends JpaRepository<WalletJpaEntity, UUID> {
}

package com.dashboard.financeiro.repository;

import com.dashboard.financeiro.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
    Optional<Carteira> findByUsuarioId(Long usuarioId);
    boolean existsByUsuarioId(Long usuarioId);
}

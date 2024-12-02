package univc.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import univc.banco.entity.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
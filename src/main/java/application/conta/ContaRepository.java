package application.conta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Integer>{
	Iterable<Conta> findByFlagAtivo(boolean flagAtivo);
}

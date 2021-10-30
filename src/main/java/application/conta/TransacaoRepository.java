package application.conta;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer>{
	
	// Encontrar pelo id da conta
	Iterable<Transacao> findByIdConta(int idConta);
	
	// Encontrar as transaçoes do id da conta no periodo passado (comeco e fim)
	Iterable<Transacao> findByIdContaAndDataTransacaoGreaterThanEqualAndDataTransacaoLessThanEqual
	(int idConta, LocalDate comeco, LocalDate fim);
}

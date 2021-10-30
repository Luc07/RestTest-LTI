package application.conta;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
	private final int MENOR_QUE_ZERO = 0;
	@Autowired
	private ContaRepository repository;
	private Transacao t;
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	//Criar Conta
	public Conta criarConta(Conta c){
		Date date = new Date();
		// Converter tipo Date para LocalTime
		c.setDataCriacao(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		return repository.save(c);
	}
	
	//Listar Contas no banco de dados
	public Iterable<Conta> listarContas(){
		return repository.findByFlagAtivo(true);
	} 
	
	// Achar conta pelo id passado
	public Optional<Conta> acharPeloId(int id) {
		return repository.findById(id);
	}
	
	// Consultar saldo pelo id da conta
	public double consultarSaldo(int id) {
		Conta c = repository.getById(id);
		return c.getSaldo();
	}
	
	// Sacar valor passado na conta do id passado
	public void sacar(int id, double valor) throws ValorDoSaqueNaoPodeSerNegativoException{
		if(valor < MENOR_QUE_ZERO) {
			throw new ValorDoSaqueNaoPodeSerNegativoException();
		}
		Conta c = repository.getById(id);
		c.setSaldo(c.getSaldo() - valor);
		repository.save(c);
		t = new Transacao();
		t.setTipoTransacao("Saque");
		t.setIdConta(id);
		t.setValor(valor);
		Date date = new Date();
		// Converter tipo Date para LocalTime
		t.setDataTransacao(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		criarTransacao(t);
	}
	
	// Faz um deposito de um valor passado na conta do id passado e cria uma transacao
	public void depositar(int id, double valor) throws ValorDoSaqueNaoPodeSerNegativoException{
		if(valor < MENOR_QUE_ZERO) {
			throw new ValorDoSaqueNaoPodeSerNegativoException();
		}
		Conta c = repository.getById(id);
		c.setSaldo(c.getSaldo() + valor);
		repository.save(c);
		t = new Transacao();
		t.setTipoTransacao("Deposito");
		t.setIdConta(id);
		t.setValor(valor);
		Date date = new Date();
		t.setDataTransacao(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(2));
		criarTransacao(t);
	}
	
	// Bloquear a conta do id passado
	public void bloquearConta(int id) {
		Conta c = repository.getById(id);
		c.setFlagAtivo(false);
		repository.save(c);
	}
	
	// Desbloquear a conta
	public void desbloquearConta(int id) {
		Conta c = repository.getById(id);
		c.setFlagAtivo(true);
		repository.save(c);
	}
	
	// Cria a transacao apos fazer saque/deposito
	public void criarTransacao(Transacao t) {
		transacaoRepository.save(t);
	}
	
	// Lista todas transacoes da conta do id passado
	public Iterable<Transacao> listarTransacoesDaConta(int idConta){
		return transacaoRepository.findByIdConta(idConta);
	}
	
	// Lista todas as transacoes no determinado periodo na id da conta passada
	public Iterable<Transacao> listarTransacoesPeriodo(int idConta, LocalDate comeco, LocalDate fim){
		return transacaoRepository.findByIdContaAndDataTransacaoGreaterThanEqualAndDataTransacaoLessThanEqual(idConta, comeco, fim);
	}
}

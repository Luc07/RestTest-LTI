package application.conta;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContaController {
	
	@Autowired
	private ContaService service;
	
	//Consultar Contas
	@GetMapping("/conta")
	public ResponseEntity< Iterable< Conta > > listarContas(){
		return new ResponseEntity<Iterable<Conta>> (service.listarContas(), HttpStatus.OK);
	}
	
	//Achar Conta pela ID
	@GetMapping("/conta/{id}")
	public ResponseEntity< Optional< Conta > > acharPeloId(@PathVariable int id){
		return new ResponseEntity< Optional< Conta > > (service.acharPeloId(id), HttpStatus.OK);
	}
	
	//Criar Conta
	@PostMapping("/conta")
	public ResponseEntity<Conta> criarConta(@RequestBody Conta c){
		return new ResponseEntity<Conta>(service.criarConta(c), HttpStatus.CREATED);
	}
	
	//Verificar Saldo
	@GetMapping("/conta/{id}/saldo")
	public ResponseEntity<Double> consultarSaldo(@PathVariable int id){
		return new ResponseEntity<Double>(service.consultarSaldo(id), HttpStatus.OK);
	}
	
	//Sacar de uma conta
	@PostMapping("/conta/{id}/sacar/{valor}")
	public ResponseEntity<HttpStatus> saque(@PathVariable int id,@PathVariable Double valor) throws ValorDoSaqueNaoPodeSerNegativoException{
		service.sacar(id,valor);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	//Depositar em uma conta
	@PostMapping("/conta/{id}/depositar/{valor}")
	public ResponseEntity<HttpStatus> depositar(@PathVariable int id, @PathVariable Double valor) throws ValorDoSaqueNaoPodeSerNegativoException{
		service.depositar(id, valor);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	//Bloquear Conta
	@PutMapping("/conta/{id}/bloquear")
	public ResponseEntity<HttpStatus> bloquearConta(@PathVariable int id){
		service.bloquearConta(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	// Desbloquear conta
	@PutMapping("/conta/{id}/desbloquear")
	public ResponseEntity<HttpStatus> desbloquearConta(@PathVariable int id){
		service.desbloquearConta(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	//Listar transacoes de uma conta especifica
	@GetMapping("/conta/{idConta}/transacoes")
	public ResponseEntity<Iterable<Transacao>> listarTransacoesDaConta(@PathVariable int idConta){
		return new ResponseEntity<Iterable<Transacao>>(service.listarTransacoesDaConta(idConta), HttpStatus.OK);
	}
	
	// Listar transacoes da conta no determinado periodo (comeco e fim)
	@GetMapping("/conta/{idConta}/transacoes/{comeco}/{fim}")
	public ResponseEntity<Iterable<Transacao>> listarTransacoesPeriodo(@PathVariable int idConta, @PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate comeco, @PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate fim){
		return new ResponseEntity<Iterable<Transacao>>(service.listarTransacoesPeriodo(idConta,comeco,fim), HttpStatus.OK);
	}
}

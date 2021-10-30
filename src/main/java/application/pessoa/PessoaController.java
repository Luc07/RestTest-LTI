package application.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessoaController {
	@Autowired
	private PessoaService service;
	
	@GetMapping("/pessoa")
	public ResponseEntity<Iterable<Pessoa>> listarPessoas(){
		return new ResponseEntity<Iterable<Pessoa>>(service.listarPessoas(), HttpStatus.OK);
	}
	
	@PostMapping("/pessoa")
	public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa p){
		return new ResponseEntity<Pessoa>(service.criarPessoa(p), HttpStatus.CREATED);
	}
}

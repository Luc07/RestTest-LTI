package application.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {
	@Autowired
	private PessoaRepository repository;
	
	public Iterable<Pessoa> listarPessoas(){
		return repository.findAll();
	}
	
	public Pessoa criarPessoa(Pessoa p) {
		return repository.save(p);
	}
}

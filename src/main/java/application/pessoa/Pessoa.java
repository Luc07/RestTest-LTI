package application.pessoa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Pessoa {
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPessoa;
	private String nome;
	@Id
	private String cpf;
	private Date dataNascimento;
}

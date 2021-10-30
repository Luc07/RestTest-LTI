package application.conta;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Conta {
	@Id
	private int idConta;
	private int idPessoa;
	private double saldo;
	private double limiteSaqueDiario;
	private boolean flagAtivo;
	private int tipoConta;
	private LocalDate dataCriacao;
	
}

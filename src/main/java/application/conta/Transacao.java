package application.conta;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Transacao {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int idTransacao;
	private int idConta;
	private double valor;
	// Se o tipo é saque ou deposito
	private String tipoTransacao;
	private LocalDate dataTransacao;
}

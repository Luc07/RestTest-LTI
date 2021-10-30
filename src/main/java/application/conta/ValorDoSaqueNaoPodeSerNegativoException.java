package application.conta;

public class ValorDoSaqueNaoPodeSerNegativoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValorDoSaqueNaoPodeSerNegativoException() {
		super("Valor do saque não pode ser negativo");
	}
}

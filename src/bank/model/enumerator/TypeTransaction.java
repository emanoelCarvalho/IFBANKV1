package bank.model.enumerator;

public enum TypeTransaction {

	CREDITO(1), DEBITO(2), TRANSACAO_CREDITO(3), TRANSACAO_DEBITO(4);

	private final int valor;

	private TypeTransaction(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public static TypeTransaction transacaoFromValor(int valor) {
		for (TypeTransaction t : values()) {
			if (t.getValor() == valor) {
				return t;
			}
		}
		return null;
	}
}

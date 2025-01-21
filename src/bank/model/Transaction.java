package bank.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

import bank.model.enumerator.TypeTransaction;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private BigDecimal valor;
	private TypeTransaction tipo;
	private LocalDateTime data;

	public Transaction(BigDecimal valor, TypeTransaction tipo, LocalDateTime data) {
		this.id = new Random().nextInt(999999999);
		this.valor = valor;
		this.tipo = tipo;
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TypeTransaction getTipo() {
		return tipo;
	}

	public void setTipo(TypeTransaction tipo) {
		this.tipo = tipo;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, id, tipo, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(data, other.data) && Objects.equals(id, other.id) && tipo == other.tipo
				&& Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return "Transação:\n" + "  ID: " + id + "\n" + "  Valor: " + valor + "\n" + "  Tipo: " + tipo + "\n"
				+ "  Data: " + data + "\n";
	}

}

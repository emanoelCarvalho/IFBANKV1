package bank.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import bank.model.enumerator.TypeTransaction;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer numero;
	private BigDecimal saldo;
	private LocalDateTime dataAbertura;
	private boolean status;
	private List<Transaction> transacoes;

	public Account() {
		this.numero = new Random().nextInt(999999999);
		this.saldo = BigDecimal.ZERO;
		saldo.setScale(4, RoundingMode.HALF_UP);
		this.dataAbertura = LocalDateTime.now();
		this.status = true;
		this.transacoes = new ArrayList<>();
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Transaction> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transaction> transacoes) {
		this.transacoes = transacoes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(numero, other.numero);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Conta:\n");
		sb.append("  Número: ").append(numero).append("\n");
		sb.append("  Saldo: ").append(saldo).append("\n");
		sb.append("  Data de Abertura: ").append(dataAbertura).append("\n");
		sb.append("  Status: ").append(status).append("\n");
		sb.append("  Transações:\n");
		for (Transaction transacao : transacoes) {
			sb.append("    ").append(transacao).append("\n");
		}
		return sb.toString();
	}

	public void depositar(BigDecimal quantia) {

		if (status) {
			if (quantia.compareTo(BigDecimal.ZERO) > 0) {
				this.saldo = this.saldo.add(quantia);
				transacoes.add(new Transaction(quantia, TypeTransaction.CREDITO, LocalDateTime.now()));

				System.out.println("Depósito realizado com sucesso!");
			} else {
				System.err.println("Valor invalido para deposito.");

			}
		} else {
			System.err.println("Operação não permitida. Conta desativada.");
		}
	}

	public void sacar(BigDecimal quantia) {

		if (status) {
			if (quantia.compareTo(BigDecimal.ZERO) > 0) {
				if (this.saldo.compareTo(quantia) > 0) {
					this.saldo = this.saldo.subtract(quantia);
					transacoes.add(new Transaction(quantia, TypeTransaction.DEBITO, LocalDateTime.now()));
					System.out.println("Saque realizado com sucesso!");
				} else {
					System.err.println("Saldo insuficiente para a operação.");
				}
			} else {
				System.err.println("Valor invalido para saque.");
			}
		} else {
			System.err.println("Operação não permitida. Conta desativada.");
		}
	}

	public void transferir(Account c, BigDecimal quantia) {
		if (status && c.isStatus()) {
			if (quantia.compareTo(BigDecimal.ZERO) < 0) {
				System.err.println("Valor inválido para transferencia.");
			} else if (quantia.compareTo(saldo) > 0) {
				setSaldo(saldo.subtract(quantia));
				c.setSaldo(c.getSaldo().add(quantia));
				c.transacoes.add(new Transaction(quantia, TypeTransaction.TRANSACAO_CREDITO, LocalDateTime.now()));
				transacoes.add(new Transaction(quantia, TypeTransaction.TRANSACAO_DEBITO, LocalDateTime.now()));
			} else
				System.err.println("Saldo insuficiente para realizar a transferencia.");
		} else {
			System.err.println("Operacao nao pode ser realizada entre contas desativadas.");
		}
	}

}

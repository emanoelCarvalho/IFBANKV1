package bank.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Objects;

public class client implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cpf;
	private String nome;

	private ArrayList<account> contas;

	public client() {
		// TODO Auto-generated constructor stub
	}

	public client(String cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome;
		this.contas = new ArrayList<>();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<account> getContas() {
		return contas;
	}

	public void setContas(ArrayList<account> contas) {
		this.contas = contas;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cliente:\n");
		sb.append("  CPF: ").append(cpf).append("\n");
		sb.append("  Nome: ").append(nome).append("\n");
		sb.append("  Contas:\n");
		for (account conta : contas) {
			sb.append("    ").append(conta).append("\n");
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		client other = (client) obj;
		return Objects.equals(cpf, other.cpf);
	}

	public void adicionarConta(account c) {
		if (contas.contains(c)) {
			System.err.println("A conta já existe em nosso sistema.");
		} else { 
			this.contas.add(c);
			System.out.println("Cadastramos sua conta com sucesso.");
		}
	}

	public void removerConta(account c) {

		if (contas.contains(c)) {
			this.contas.remove(c);
			System.out.println("A conta foi  excluída com sucesso.");
		} else {
			System.err.println("Conta inexistente no nosso sistema.");
		}
	}

	public account localizarContaNumero(int numero) {
		for (int i = 0; i < contas.size(); i++) {
			account c = contas.get(i);

			if (c.getNumero() == numero) {
				System.out.println("Conta encontrada com sucesso!");
				return c;
			}
		}
		System.err.println("Conta não encontrada no nosso sistema");
		return null;
	}

	public double balancoEntreContas() {
		double ValorSaldo = 0.0;
		for (int i = 0; i < contas.size(); i++) {
			account c = contas.get(i);
			ValorSaldo += c.getSaldo().doubleValue();
		}

		System.out.println("Balanco entre contas: RS" + ValorSaldo);
		return ValorSaldo;
	}

	public void emitirExtrato(Month mes, int year) {
		System.out.println("Extrado do mês " + mes.toString() + "/" + year);
		for (account c : contas) {
			System.out.println("Conta " + c.getNumero() + ":");
			for (transaction t : c.getTransacoes()) {
				LocalDateTime dataTransacao = t.getData();
				if (dataTransacao.getMonth() == mes && dataTransacao.getYear() == year) {
					System.out.println(" " + t.toString());
				}
			}
			System.out.println();
		}
	}
}

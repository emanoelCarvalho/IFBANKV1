package bank.app;

import java.math.BigDecimal;

import java.time.Month;
import java.util.List;
import java.util.Scanner;

import bank.model.Client;
import bank.model.Account;
import persistencia.PersistenciaArquivo;

public class App {

	private static Scanner scanner = new Scanner(System.in);
	private static Client cliente = null;

	public static void main(String[] args) {

		while (true) {
			exibirMenuPrincipal();

			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				cadastrarNovoCliente();
				break;
			case 2:
				selecionarClienteExistente();
				break;
			case 3:
				listarClientes();
				break;
			case 4:
				removerCliente();
				break;
			case 5:
				sair();
				break;
			default:
				System.out.println("Opção inválida");
				break;
			}
		}
	}

	private static void exibirMenuPrincipal() {
		System.out.println("==================================================");
		System.out.println("            Bem-vindo ao banco IFBANK!            ");
		System.out.println("==================================================");
		System.out.println("O que você gostaria de fazer?");
		System.out.println("1. Cadastrar novo cliente");
		System.out.println("2. Selecionar cliente existente");
		System.out.println("3. Listar todos os clientes");
		System.out.println("4. Remover cliente");
		System.out.println("5. Sair");
		System.out.println("==================================================");
	}

	private static void cadastrarNovoCliente() {
		System.out.println("==================================================");
		System.out.println("           Cadastro de Novo Cliente");
		System.out.println("==================================================");
		System.out.print("Digite o nome do cliente: ");
		String nome = scanner.nextLine();
		System.out.print("Digite o CPF do cliente: ");
		String cpf = scanner.nextLine();
		cliente = new Client(cpf, nome);
		System.out.println("Cliente cadastrado com sucesso!");
		PersistenciaArquivo.getInstance().cadastrarCliente(cliente);
	}

	private static void selecionarClienteExistente() {
		System.out.print("Digite o CPF do cliente: ");
		String cpf = scanner.next();

		cliente = PersistenciaArquivo.getInstance().buscarClienteCpf(cpf);

		if (cliente == null) {
			System.err.println("Cliente não encontrado.");
			return;
		}

		boolean continuar = true;

		while (continuar) {
			exibirMenuCliente();

			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				criarNovaConta();
				break;
			case 2:
				verInformacoesContas();
				break;
			case 3:
				realizarDeposito();
				break;
			case 4:
				realizarSaque();
				break;
			case 5:
				realizarTransferencia();
				break;
			case 6:
				realizarTransferenciaEntreClientes();
				break;
			case 7:
				realizarBalancoEntreContas();
				break;
			case 8:
				checarSaldoConta();
				break;
			case 9:
				removerConta();
				break;
			case 10:
				emitirExtratoMensal();
				break;
			default:
				System.out.println("Opção inválida");
				break;
			}

			System.out.println("Deseja realizar outra operação para este cliente? (s/n): ");
			String resposta = scanner.nextLine();
			if (!resposta.equalsIgnoreCase("s")) {
				continuar = false;
			}
		}
	}

	private static void exibirMenuCliente() {
		System.out.println("Cliente selecionado: " + cliente.getNome());
		System.out.println("==================================================");
		System.out.println("O que você gostaria de fazer?");
		System.out.println("1. Criar nova conta");
		System.out.println("2. Ver informações das contas");
		System.out.println("3. Realizar Depósito");
		System.out.println("4. Realizar Saque");
		System.out.println("5. Realizar Transferência entre contas do mesmo cliente");
		System.out.println("6. Realizar Transferência entre contas de clientes diferentes");
		System.out.println("7. Realizar Balanço entre contas");
		System.out.println("8. Checar saldo de uma conta");
		System.out.println("9. Remover conta");
		System.out.println("10. Emitir extrato mensal de uma conta");
		System.out.println("==================================================");
	}

	private static void criarNovaConta() {
		Account conta = new Account();
		cliente.adicionarConta(conta);
		PersistenciaArquivo.getInstance().atualizarCliente(cliente);
	}

	private static void verInformacoesContas() {
		if (cliente.getContas().isEmpty()) {
			System.err.println("Não há contas associadas a este cliente.");
		} else {
			for (Account c : cliente.getContas()) {
				System.out.println(c);
			}
		}
	}

	private static void realizarDeposito() {
		if (cliente.getContas().isEmpty()) {
			System.err.println("Não há contas associadas a este cliente.");
			return;
		}

		for (Account c : cliente.getContas()) {
			System.out.println(c);
		}
		System.out.print("Em qual conta deseja realizar o depósito? ");
		int opcaoContaDeposito = scanner.nextInt();
		System.out.print("Insira o valor da quantia a ser depositada: ");
		double quantiaDeposito = scanner.nextDouble();
		Account tempContaDeposito = cliente.localizarContaNumero(opcaoContaDeposito);
		if (tempContaDeposito != null) {
			tempContaDeposito.depositar(new BigDecimal(quantiaDeposito));
			PersistenciaArquivo.getInstance().atualizarCliente(cliente);
		}
	}

	private static void realizarSaque() {
		if (cliente.getContas().isEmpty()) {
			System.err.println("Não há contas associadas a este cliente.");
			return;
		}

		for (Account c : cliente.getContas()) {
			System.out.println(c);
		}
		System.out.print("Em qual conta deseja realizar o saque? ");
		int opcaoContaSaque = scanner.nextInt();
		System.out.print("Insira o valor da quantia a ser sacada: ");
		double quantiaSaque = scanner.nextDouble();
		Account tempContaSaque = cliente.localizarContaNumero(opcaoContaSaque);
		if (tempContaSaque != null) {
			tempContaSaque.sacar(new BigDecimal(quantiaSaque));
			PersistenciaArquivo.getInstance().atualizarCliente(cliente);
		}
	}

	private static void realizarTransferencia() {
		if (cliente.getContas().isEmpty()) {
			System.err.println("Não há contas associadas a este cliente.");
			return;
		}

		for (Account c : cliente.getContas()) {
			System.out.println(c);
		}
		System.out.print("Selecione a conta de origem: ");
		int opcaoContaOrigem = scanner.nextInt();
		System.out.print("Selecione a conta de destino: ");
		int opcaoContaDestino = scanner.nextInt();
		System.out.print("Insira o valor da quantia a ser transferida: ");
		double quantiaTransferencia = scanner.nextDouble();
		Account contaOrigem = cliente.localizarContaNumero(opcaoContaOrigem);
		Account contaDestino = cliente.localizarContaNumero(opcaoContaDestino);
		if (contaOrigem != null && contaDestino != null) {
			contaOrigem.transferir(contaDestino, new BigDecimal(quantiaTransferencia));
			PersistenciaArquivo.getInstance().atualizarCliente(cliente);
			System.out.println("Transferência realizada com sucesso!");
		} else {
			System.err.println("Contas inválidas.");
		}
	}

	private static void realizarTransferenciaEntreClientes() {
		System.out.print("Digite o CPF do cliente de origem: ");
		String cpfOrigem = scanner.next();
		Client clienteOrigem = PersistenciaArquivo.getInstance().buscarClienteCpf(cpfOrigem);

		if (clienteOrigem == null) {
			System.err.println("Cliente de origem não encontrado.");
			return;
		}

		for (Account c : clienteOrigem.getContas()) {
			System.out.println(c);
		}

		System.out.print("Selecione o número da conta de origem: ");
		int numeroContaOrigem = scanner.nextInt();
		Account contaOrigem = clienteOrigem.localizarContaNumero(numeroContaOrigem);
		if (contaOrigem == null) {
			System.err.println("Conta de origem não encontrada.");
			return;
		}

		System.out.print("Digite o CPF do cliente de destino: ");
		String cpfDestino = scanner.next();
		Client clienteDestino = PersistenciaArquivo.getInstance().buscarClienteCpf(cpfDestino);

		if (clienteDestino == null) {
			System.err.println("Cliente de destino não encontrado.");
			return;
		}

		for (Account c : clienteDestino.getContas()) {
			System.out.println(c);
		}

		System.out.print("Selecione o número da conta de destino: ");
		int numeroContaDestino = scanner.nextInt();
		Account contaDestino = clienteDestino.localizarContaNumero(numeroContaDestino);
		if (contaDestino == null) {
			System.err.println("Conta de destino não encontrada.");
			return;
		}

		System.out.print("Insira o valor da quantia a ser transferida: ");
		double quantiaTransferencia = scanner.nextDouble();

		contaOrigem.transferir(contaDestino, new BigDecimal(quantiaTransferencia));

		PersistenciaArquivo.getInstance().atualizarCliente(clienteOrigem);
		PersistenciaArquivo.getInstance().atualizarCliente(clienteDestino);

		System.out.println("Transferência entre clientes realizada com sucesso!");
	}

	private static void realizarBalancoEntreContas() {
		if (cliente.getContas().isEmpty()) {
			System.err.println("Não há contas associadas a este cliente.");
		} else {
			double balanco = cliente.balancoEntreContas();
			System.out.println("Balanço entre contas: R$" + balanco);
		}
	}

	private static void checarSaldoConta() {
		if (cliente.getContas().isEmpty()) {
			System.err.println("Não há contas associadas a este cliente.");
		} else {
			for (Account c : cliente.getContas()) {
				System.out.println(c);
			}
			System.out.print("Digite o número da conta para checar o saldo: ");
			int numeroConta = scanner.nextInt();
			Account contaParaSaldo = cliente.localizarContaNumero(numeroConta);
			if (contaParaSaldo != null) {
				System.out.println("Saldo da conta " + numeroConta + ": R$" + contaParaSaldo.getSaldo());
			} else {
				System.err.println("Conta não encontrada.");
			}
		}
	}

	private static void removerConta() {
		if (cliente.getContas().isEmpty()) {
			System.err.println("Não há contas associadas a este cliente.");
		} else {
			for (Account c : cliente.getContas()) {
				System.out.println(c);
			}
			System.out.print("Digite o número da conta para remover: ");
			int numeroContaRemover = scanner.nextInt();
			Account contaParaRemover = cliente.localizarContaNumero(numeroContaRemover);
			if (contaParaRemover != null) {
				cliente.removerConta(contaParaRemover);
				PersistenciaArquivo.getInstance().atualizarCliente(cliente);
				System.out.println("Conta removida com sucesso!");
			} else {
				System.err.println("Conta não encontrada.");
			}
		}
	}

	private static void emitirExtratoMensal() {
		if (cliente.getContas().isEmpty()) {
			System.err.println("Não há contas associadas a este cliente.");
			return;
		}

		System.out.print("Digite o mês (número) para emitir o extrato: ");
		int mesNumero = scanner.nextInt();
		Month mes = Month.of(mesNumero);
		System.out.print("Digite o ano para emitir o extrato: ");
		int ano = scanner.nextInt();
		cliente.emitirExtrato(mes, ano);
	}

	private static void listarClientes() {
		List<Client> clientes = PersistenciaArquivo.getInstance().listarClientes();
		if (clientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado.");
		} else {
			System.out.println("==================================================");
			System.out.println("                Lista de Clientes");
			System.out.println("==================================================");
			for (Client cli : clientes) {
				System.out.println(cli);
			}
		}
	}

	private static void removerCliente() {
		System.out.print("Digite o CPF do cliente a ser removido: ");
		String cpf = scanner.next();
		PersistenciaArquivo.getInstance().removerCliente(cpf);
	}

	private static void sair() {
		System.out.println("Até logo!");
		scanner.close();
		System.exit(0);
	}
}

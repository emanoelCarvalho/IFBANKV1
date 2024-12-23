package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import banco.model.Cliente;
import banco.model.Conta;

public class PersistenciaArquivo implements Serializable {

	private static final long serialVersionUID = 1L;
	private static PersistenciaArquivo instance;
	private ArrayList<Cliente> clientesCadastrados = new ArrayList<>();

	public PersistenciaArquivo() {
		clientesCadastrados = new ArrayList<>();
		carregarArquivo();
	}

	public static PersistenciaArquivo getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return new PersistenciaArquivo();
		}
	}

	public void cadastrarCliente(Cliente c) {
		if (clientesCadastrados.contains(c)) {
			System.err.println("Cliente já cadastrado!");
		} else {
			clientesCadastrados.add(c);
			salvarArquivo();
		}
	}

	public Cliente buscarClienteCpf(String cpf) {
		Cliente c = new Cliente();

		c.setCpf(cpf);
		if (clientesCadastrados.contains(c)) {
			int index = clientesCadastrados.indexOf(c);
			c = clientesCadastrados.get(index);
			return c;
		} else {
			return null;
		}
	}

	public List<Cliente> listarClientes() {
		return new ArrayList<>(clientesCadastrados);
	}

	public void removerCliente(String cpf) {
		Cliente cliente = buscarClienteCpf(cpf);

		if (cliente != null) {
			clientesCadastrados.remove(cliente);
			salvarArquivo();
			System.out.println("Cliente removido com sucesso.");
		} else {
			System.err.println("Cliente não encontrado.");
		}
	}

	public void atualizarCliente(Cliente c) {
		if (clientesCadastrados.contains(c)) {
			int index = clientesCadastrados.indexOf(c);
			clientesCadastrados.set(index, c);
			salvarArquivo();
		} else {
			System.err.println("Cliente não encontrado.");
		}
	}

	public void salvarArquivo() {
		try {
			FileOutputStream fos = new FileOutputStream("dados");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(clientesCadastrados);
			oos.close();
			fos.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void carregarArquivo() {
		try {
			FileInputStream fis = new FileInputStream("dados");
			ObjectInputStream ois = new ObjectInputStream(fis);
			clientesCadastrados = (ArrayList<Cliente>) ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

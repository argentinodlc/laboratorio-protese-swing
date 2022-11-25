package dados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dados.interfaces.IRepoFuncionario;
import sistema.classes.Dentista;
import sistema.classes.Fornecedor;
import sistema.classes.Funcionario;

public class RepoFuncionario implements IRepoFuncionario {
	private ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	public ArrayList<Funcionario> getArrayFunc() {
		return funcionarios;
	}
	
	public boolean newFuncionario(String nome, String cpf, double comissao, double salario) {
		Funcionario f = Funcionario.newInstance(nome, cpf, comissao, salario);
		if (f != null) {
			funcionarios.add(f);
			return true;
		}
		return false;
	}
	
	public boolean alterFuncionario(long cod, String nome, String cpf, double comissao, double salario) {
		if (funcionarios != null) {
			for (int i = 0; i < funcionarios.size(); i++) {
				if (funcionarios.get(i).getCod() == cod) {
					funcionarios.get(i).setComissao(comissao);
					funcionarios.get(i).setCpf(cpf);
					funcionarios.get(i).setNome(nome);
					funcionarios.get(i).setSalario(salario);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean excluirFuncionario(long cod) {
		if (funcionarios != null) {
			for (int i = 0; i < funcionarios.size(); i++) {
				if (funcionarios.get(i).getCod() == cod) {
					funcionarios.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void carregar() throws IOException, ParseException, IndexOutOfBoundsException {
		String user = System.getProperty("user.home");
		File diretorio = new File(user + "\\.sistemaDentArt");
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		File arq = new File(diretorio, "funcionarios.txt");
		if (!arq.exists())
			arq.createNewFile();
		else {
			Scanner leitor = new Scanner(arq);
			List<String> linhas = new ArrayList<>();
			while (leitor.hasNextLine()) {
			    linhas.add(leitor.nextLine());
			}
			Funcionario.setGeraCod(Long.parseLong(linhas.get(0)));
			for (int i = 1; i<linhas.size();i++) {
				String[] array = linhas.get(i).split(";");
				long cod = Long.parseLong(array[0]);
				String nome = array[1];
				String cpf = array[2];
				double comissao = Double.parseDouble(array[3]);
				double salario = Double.parseDouble(array[4]);
				Funcionario f = Funcionario.newInstance(nome, cpf,comissao, cod, salario);
				funcionarios.add(f);
			}
			leitor.close();
		}
	}
	
	@Override
	public void escrever() throws IOException {
		String user = System.getProperty("user.home");
		File diretorio = new File(user + "\\.sistemaDentArt");
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		File arq = new File(diretorio, "funcionarios.txt");
		if (!arq.exists())
			arq.createNewFile();
		String path = arq.getAbsolutePath();
		String linha = "";
		BufferedWriter arqD = new BufferedWriter(new FileWriter(path));
		arqD.write(String.valueOf(Funcionario.getGeraCod()) + "\n");
		for (int i = 0; i < funcionarios.size(); i++) {
			linha = "";
			if (funcionarios.get(i) == null) {
			} else {
				linha = funcionarios.get(i).getCod() + ";"+ funcionarios.get(i).getNome() + ";"+ funcionarios.get(i).getCpf() + ";" + funcionarios.get(i).getComissao() + ";" + funcionarios.get(i).getSalario();
				arqD.write(linha + "\n");
			}
		}
		arqD.close();
	}
}

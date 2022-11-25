package dados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dados.interfaces.IRepoFornecedor;
import sistema.classes.Dentista;
import sistema.classes.Fornecedor;

public class RepoFornecedor implements IRepoFornecedor {

	private ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

	@Override
	public boolean newFornecedor(String nome, long tel, String logra, int num, String bairro, String cidade,
			String cnpj, String comp) {
		Fornecedor f = Fornecedor.newInstance(nome, tel, logra, num, bairro, cidade, cnpj, comp);
		if (f!=null) {
			fornecedores .add(f);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Fornecedor> getArrayFornecedores() {
		return fornecedores;
	}

	@Override
	public boolean alterFornecedor(long cod, String nome, long tel2, String logra, int num, String bairro,
			String cidade, String cnpj, String comp) {
		if (fornecedores.size()>0) {
			for (int i = 0; i < fornecedores.size(); i++) {
				if (fornecedores.get(i).getCod() == cod) {
					fornecedores.get(i).setNome(nome);
					fornecedores.get(i).setTel(tel2);
					fornecedores.get(i).setLogra(logra);
					fornecedores.get(i).setNum(num);
					fornecedores.get(i).setBairro(bairro);
					fornecedores.get(i).setCidade(cidade);
					fornecedores.get(i).setCnpj(cnpj);
					fornecedores.get(i).setComp(comp);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean excluirFornecedor(long cod) {
		if (fornecedores.size()>0) {
			for (int i = 0; i < fornecedores.size(); i++) {
				if (fornecedores.get(i).getCod() == cod) {
					fornecedores.remove(i);
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
		File arq = new File(diretorio, "fornecedores.txt");
		if (!arq.exists())
			arq.createNewFile();
		else {
			Scanner leitor = new Scanner(arq);
			List<String> linhas = new ArrayList<>();
			while (leitor.hasNextLine()) {
			    linhas.add(leitor.nextLine());
			}
			Fornecedor.setGeraCod(Long.parseLong(linhas.get(0)));
			for (int i = 1; i<linhas.size();i++) {
				String[] array = linhas.get(i).split(";");
				long cod = Long.parseLong(array[0]);
				String nome = array[1];
				String rua = array[2];
				int numero = Integer.parseInt(array[3]);
				String comp = array[4];
				String bairro = array[5];
				String cidade = array[6];
				long tel = Long.parseLong(array[7]);
				String cnpj = array[8];
				Fornecedor f = Fornecedor.newInstance(nome, rua, bairro, cidade, numero, tel, cnpj, comp, cod);
				fornecedores.add(f);
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
		File arq = new File(diretorio, "fornecedores.txt");
		if (!arq.exists())
			arq.createNewFile();
		String path = arq.getAbsolutePath();
		String linha = "";
		BufferedWriter arqD = new BufferedWriter(new FileWriter(path));
		arqD.write(String.valueOf(Fornecedor.getGeraCod()) + "\n");
		for (int i = 0; i < fornecedores.size(); i++) {
			linha = "";
			if (fornecedores.get(i) == null) {
			} else {
				linha = fornecedores.get(i).getCod() + ";"+ fornecedores.get(i).getNome() + ";"+ fornecedores.get(i).getLogra() + ";" + fornecedores.get(i).getNum() + ";" + fornecedores.get(i).getComp() + ";" +
						fornecedores.get(i).getBairro() + ";" + fornecedores.get(i).getCidade() + ";" + fornecedores.get(i).getTel() + ";" + fornecedores.get(i).getCnpj();
				arqD.write(linha + "\n");
			}
		}
		arqD.close();
	}

}

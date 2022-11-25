package dados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dados.interfaces.IRepoProduto;
import sistema.classes.Paciente;
import sistema.classes.Produto;

public class RepoProduto implements IRepoProduto {

	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	
	@Override
	public boolean newProduto(String nome, double valorMedio) {
		Produto p = Produto.newInstance(nome,valorMedio);
		if (p != null) {
			produtos.add(p);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Produto> getArrayProdutos() {
		return produtos;
	}

	@Override
	public boolean alterProduto(long cod, String nome, double valorMedio) {
		if (produtos.size()>0) {
			for (int i = 0; i < produtos.size(); i++) {
				if (produtos.get(i).getCod() == cod) {
					produtos.get(i).setNome(nome);
					produtos.get(i).setValorMedio(valorMedio);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean excluirProduto(long cod) {
		if (produtos.size()>0) {
			for (int i = 0; i < produtos.size(); i++) {
				if (produtos.get(i).getCod() == cod) {
					produtos.remove(i);
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
		File arq = new File(diretorio, "produtos.txt");
		if (!arq.exists())
			arq.createNewFile();
		else {
			Scanner leitor = new Scanner(arq);
			List<String> linhas = new ArrayList<>();
			while (leitor.hasNextLine()) {
			    linhas.add(leitor.nextLine());
			}
			Produto.setGeraCod(Long.parseLong(linhas.get(0)));
			for (int i = 1; i<linhas.size();i++) {
				String[] array = linhas.get(i).split(";");
				long cod = Long.parseLong(array[0]);
				String nome = array[1];
				double valor = Double.parseDouble(array[2]);
				Produto p = Produto.newInstance(nome,valor, cod);
				produtos.add(p);
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
		File arq = new File(diretorio, "produtos.txt");
		if (!arq.exists())
			arq.createNewFile();
		String path = arq.getAbsolutePath();
		String linha = "";
		BufferedWriter arqD = new BufferedWriter(new FileWriter(path));
		arqD.write(String.valueOf(Produto.getGeraCod()) + "\n");
		for (int i = 0; i < produtos.size(); i++) {
			linha = "";
			if (produtos.get(i) == null) {
			} else {
				linha = produtos.get(i).getCod() + ";"+ produtos.get(i).getNome() + ";" + produtos.get(i).getValorMedio();
				arqD.write(linha + "\n");
			}
		}
		arqD.close();
	}

}

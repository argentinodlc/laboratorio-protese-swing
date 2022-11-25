package dados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dados.interfaces.IRepoTipoServicos;
import sistema.classes.Despesa;
import sistema.classes.TipoServico;

public class RepoTipoServicos implements IRepoTipoServicos{
	private ArrayList<TipoServico> tipos = new ArrayList<TipoServico>();

	@Override
	public ArrayList<TipoServico> getArrayTipos() {
		return tipos;
	}

	@Override
	public boolean newTipoServico(String nome, double preco) {
		TipoServico t = TipoServico.newInstance(nome, preco);
		if (t != null) {
			tipos.add(t);
			return true;
		}
		return false;
	}

	@Override
	public boolean alterarTipoServico(long cod, String nome, double preco, double valorComissao) {
		if (!(tipos.isEmpty())) {
			for (int i = 0; i < tipos.size(); i++) {
				if (tipos.get(i).getCod()==cod) {
					tipos.get(i).setNome(nome);
					tipos.get(i).setPreco(preco);
					tipos.get(i).setValorComissao(valorComissao);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean excluirTipoServico(long cod) {
		if (cod == 1)
			return false;
		if (!(tipos.isEmpty())) {
			for (int i = 0; i < tipos.size(); i++) {
				if (tipos.get(i).getCod()==cod) {
					tipos.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void escrever() throws IOException {
		String user = System.getProperty("user.home");
		File diretorio = new File(user + "\\.sistemaDentArt");
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		File arq = new File(diretorio, "tipos.txt");
		if (!arq.exists())
			arq.createNewFile();
		String path = arq.getAbsolutePath();
		String linha = "";
		BufferedWriter arqD = new BufferedWriter(new FileWriter(path));
		arqD.write(String.valueOf(TipoServico.getGeraCod()) + "\n");
		for (int i = 0; i < tipos.size(); i++) {
			linha = "";
			if (tipos.get(i) == null) {
			} else {
				linha = tipos.get(i).getCod() + ";"+ tipos.get(i).getNome() + ";" + tipos.get(i).getPreco() + ";" + tipos.get(i).getValorComissao();
				arqD.write(linha + "\n");
			}
		}
		arqD.close();
	}
	
	@Override
	public void carregar() throws IOException, ParseException, IndexOutOfBoundsException {
		String user = System.getProperty("user.home");
		File diretorio = new File(user + "\\.sistemaDentArt");
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		File arq = new File(diretorio, "tipos.txt");
		if (!arq.exists())
			arq.createNewFile();
		else {
			Scanner leitor = new Scanner(arq);
			List<String> linhas = new ArrayList<>();
			while (leitor.hasNextLine()) {
			    linhas.add(leitor.nextLine());
			}
			TipoServico.setGeraCod(Long.parseLong(linhas.get(0)));
			for (int i = 1; i<linhas.size();i++) {
				String[] array = linhas.get(i).split(";");
				long cod = Long.parseLong(array[0]);
				String nome = array[1];
				double preco = Double.parseDouble(array[2]);
				double valorComissao = Double.parseDouble(array[3]);
				TipoServico t = TipoServico.newInstance(nome, preco, cod, valorComissao);
				tipos.add(t);
			}
			leitor.close();
		}
	}

}

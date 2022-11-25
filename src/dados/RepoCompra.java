package dados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dados.interfaces.IRepoCompra;
import sistema.classes.Compra;
import sistema.classes.Fornecedor;
import sistema.classes.Paciente;
import sistema.classes.Produto;

public class RepoCompra implements IRepoCompra {

	private ArrayList<Compra> compras = new ArrayList<Compra>();

	@Override
	public boolean newCompra(String descricao, double valor, Date data, Fornecedor fornecedor) {
		Compra c = Compra.newInstance(descricao, valor, data, fornecedor);
		if (c!=null) {
			compras .add(c);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Compra> getArrayCompras() {
		return compras;
	}

	@Override
	public boolean alterCompra(long cod, String descricao, double valor, Date data, Fornecedor fornecedor) {
		if (compras.size()>0) {
			for (int i = 0; i < compras.size(); i++) {
				if (compras.get(i).getCod() == cod) {
					compras.get(i).setDescricao(descricao);
					compras.get(i).setValor(valor);
					compras.get(i).setData(data);
					compras.get(i).setFornecedor(fornecedor);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean excluirCompra(long cod) {
		if (compras.size()>0) {
			for (int i = 0; i < compras.size(); i++) {
				if (compras.get(i).getCod() == cod) {
					compras.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void carregar(ArrayList<Fornecedor> f) throws IOException, ParseException, IndexOutOfBoundsException {
		String user = System.getProperty("user.home");
		File diretorio = new File(user + "\\.sistemaDentArt");
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		File arq = new File(diretorio, "compras.txt");
		if (!arq.exists())
			arq.createNewFile();
		else {
			Scanner leitor = new Scanner(arq);
			List<String> linhas = new ArrayList<>();
			while (leitor.hasNextLine()) {
			    linhas.add(leitor.nextLine());
			}
			Compra.setGeraCod(Long.parseLong(linhas.get(0)));
			for (int i = 1; i<linhas.size();i++) {
				String[] array = linhas.get(i).split(";");
				long cod = Long.parseLong(array[0]);
				String descricao = array[1];
				double valor = Double.parseDouble(array[2]);
				Date data = new SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy").parse(array[3]);
				long codForn = Long.parseLong(array[4]);
				Fornecedor fornecedor = null;
				for (int j = 0; j < f.size(); j++) {
					if (f.get(j).getCod()==codForn) {
						fornecedor = f.get(j);
					}
				}
				Compra c = Compra.newInstance(descricao, valor, data, fornecedor, cod);
				compras.add(c);
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
		File arq = new File(diretorio, "compras.txt");
		if (!arq.exists())
			arq.createNewFile();
		String path = arq.getAbsolutePath();
		String linha = "";
		BufferedWriter arqD = new BufferedWriter(new FileWriter(path));
		arqD.write(String.valueOf(Compra.getGeraCod()) + "\n");
		for (int i = 0; i < compras.size(); i++) {
			linha = "";
			if (compras.get(i) == null) {
			} else {
				linha = compras.get(i).getCod() + ";"+ compras.get(i).getDescricao() + ";" + compras.get(i).getValor() + ";" + (new SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy")).format(compras.get(i).getData()) + ";" + compras.get(i).getFornecedor().getCod();
				arqD.write(linha + "\n");
			}
		}
		arqD.close();
	}

}

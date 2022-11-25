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

import dados.interfaces.IRepoDespesa;
import sistema.classes.Compra;
import sistema.classes.Despesa;
import sistema.classes.Fornecedor;

public class RepoDespesa implements IRepoDespesa {
	ArrayList<Despesa> despesas = new ArrayList<Despesa>();
	ArrayList<Despesa> despesasFixas = new ArrayList<Despesa>();
	@Override
	public boolean newDespesa(String nome, Date data, double valor) {
		Despesa d = Despesa.newInstance(nome, data, valor);
		if (d!=null) {
			despesas.add(d);
			return true;
		}
		return false;
	}
	@Override
	public boolean newDespesaFixa(String nome, double valor) {
		Despesa d = Despesa.newInstance(nome, null, valor);
		if (d!=null) {
			despesasFixas.add(d);
			return true;
		}
		return false;
	}
	@Override
	public ArrayList<Despesa> getArrayDespesa() {
		return despesas;
	}
	@Override
	public ArrayList<Despesa> getArrayDespesaFixa() {
		return despesasFixas;
	}
	@Override
	public boolean removeDespesa(long cod) {
		if (despesas!=null) {
			if(despesas.size()>0) {
				for (int i = 0; i < despesas.size(); i++) {
					if (despesas.get(i).getCod() == cod) {
						despesas.remove(i);
						return true;
					}
				}
			}
		}
		return false;
	}
	@Override
	public boolean removeDespesaFixa(long cod) {
		if (despesasFixas!=null) {
			if(despesasFixas.size()>0) {
				for (int i = 0; i < despesasFixas.size(); i++) {
					if (despesasFixas.get(i).getCod() == cod) {
						despesasFixas.remove(i);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public void escreverFixo() throws IOException {
		String user = System.getProperty("user.home");
		File diretorio = new File(user + "\\.sistemaDentArt");
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		File arq = new File(diretorio, "fixas.txt");
		if (!arq.exists())
			arq.createNewFile();
		String path = arq.getAbsolutePath();
		String linha = "";
		BufferedWriter arqD = new BufferedWriter(new FileWriter(path));
		arqD.write(String.valueOf(Despesa.getGeraCod()) + "\n");
		for (int i = 0; i < despesasFixas.size(); i++) {
			linha = "";
			if (despesasFixas.get(i) == null) {
			} else {
				linha = despesasFixas.get(i).getCod() + ";"+ despesasFixas.get(i).getNome() + ";" + despesasFixas.get(i).getValor();
				arqD.write(linha + "\n");
			}
		}
		arqD.close();
	}
	
	@Override
	public void carregarFixo() throws IOException, ParseException, IndexOutOfBoundsException {
		String user = System.getProperty("user.home");
		File diretorio = new File(user + "\\.sistemaDentArt");
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		File arq = new File(diretorio, "fixas.txt");
		if (!arq.exists())
			arq.createNewFile();
		else {
			Scanner leitor = new Scanner(arq);
			List<String> linhas = new ArrayList<>();
			while (leitor.hasNextLine()) {
			    linhas.add(leitor.nextLine());
			}
			Despesa.setGeraCod(Long.parseLong(linhas.get(0)));
			for (int i = 1; i<linhas.size();i++) {
				String[] array = linhas.get(i).split(";");
				long cod = Long.parseLong(array[0]);
				String nome = array[1];
				double valor = Double.parseDouble(array[2]);
				Despesa d = Despesa.newInstance(nome, null, valor, cod);
				despesasFixas.add(d);
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
		File arq = new File(diretorio, "despesas.txt");
		if (!arq.exists())
			arq.createNewFile();
		String path = arq.getAbsolutePath();
		String linha = "";
		BufferedWriter arqD = new BufferedWriter(new FileWriter(path));
		arqD.write(String.valueOf(Despesa.getGeraCod()) + "\n");
		for (int i = 0; i < despesas.size(); i++) {
			linha = "";
			if (despesas.get(i) == null) {
			} else {
				linha = despesas.get(i).getCod() + ";"+ despesas.get(i).getNome() + ";" + despesas.get(i).getValor() + ";" +
			(new SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy")).format(despesas.get(i).getData());
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
		File arq = new File(diretorio, "despesas.txt");
		if (!arq.exists())
			arq.createNewFile();
		else {
			Scanner leitor = new Scanner(arq);
			List<String> linhas = new ArrayList<>();
			while (leitor.hasNextLine()) {
			    linhas.add(leitor.nextLine());
			}
			Despesa.setGeraCod(Long.parseLong(linhas.get(0)));
			for (int i = 1; i<linhas.size();i++) {
				String[] array = linhas.get(i).split(";");
				long cod = Long.parseLong(array[0]);
				String nome = array[1];
				double valor = Double.parseDouble(array[2]);
				Date data = new SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy").parse(array[3]);
				Despesa d = Despesa.newInstance(nome, data, valor, cod);
				despesas.add(d);
			}
			leitor.close();
		}
	}
	
}

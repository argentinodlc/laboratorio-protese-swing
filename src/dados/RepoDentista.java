package dados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dados.interfaces.IRepoDentista;
import sistema.classes.Dentista;

public class RepoDentista implements IRepoDentista {

	private ArrayList<Dentista> dentistas = new ArrayList<Dentista>();

	public boolean newDentista(String nome, String cro, long tel, String logra, long numero, String comp, String bairro,
			String cidade) {

		Dentista d = Dentista.newInstance(nome, logra, bairro, cidade, numero, tel, cro, comp);

		if (d == null) {
			return false;
		} else {
			dentistas.add(d);
			return true;
		}

	}

	@Override
	public ArrayList<Dentista> getArray() {
		return dentistas;
	}

	@Override
	public boolean alterDentista(long cod, String nome, String cro, long telefone, String logra, long num, String comp,
			String bairro, String cidade) {
		if (dentistas != null) {
			for (int i = 0; i < dentistas.size(); i++) {
				if (cod == dentistas.get(i).getCod()) {
					dentistas.get(i).setBairro(bairro);
					dentistas.get(i).setCidade(cidade);
					dentistas.get(i).setComp(comp);
					dentistas.get(i).setNome(nome);
					dentistas.get(i).setNumero(num);
					dentistas.get(i).setRua(logra);
					dentistas.get(i).setTelefone(telefone);
					dentistas.get(i).setCro(cro);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean excluirDentista(long cod) {
		for (int i = 0; i < dentistas.size(); i++) {
			if (cod == dentistas.get(i).getCod()) {
				dentistas.remove(i);
				return true;
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
		File arq = new File(diretorio, "dentistas.txt");
		if (!arq.exists())
			arq.createNewFile();
		else {
			Scanner leitor = new Scanner(arq);
			List<String> linhas = new ArrayList<>();
			while (leitor.hasNextLine()) {
			    linhas.add(leitor.nextLine());
			}
			Dentista.setGeraCod(Long.parseLong(linhas.get(0)));
			for (int i = 1; i<linhas.size();i++) {
				String[] array = linhas.get(i).split(";");
				long cod = Long.parseLong(array[0]);
				String nome = array[1];
				String rua = array[2];
				long numero = Long.parseLong(array[3]);
				String comp = array[4];
				String bairro = array[5];
				String cidade = array[6];
				long telefone = Long.parseLong(array[7]);
				String cro = array[8];
				Dentista d = Dentista.newInstance(nome, rua, bairro, cidade, numero, telefone, cro, comp, cod);
				dentistas.add(d);
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
		File arq = new File(diretorio, "dentistas.txt");
		if (!arq.exists())
			arq.createNewFile();
		String path = arq.getAbsolutePath();
		String linha = "";
		BufferedWriter arqD = new BufferedWriter(new FileWriter(path));
		arqD.write(String.valueOf(Dentista.getGeraCod()) + "\n");
		for (int i = 0; i < dentistas.size(); i++) {
			linha = "";
			if (dentistas.get(i) == null) {
			} else {
				linha = dentistas.get(i).getCod() + ";"+ dentistas.get(i).getNome() + ";"+ dentistas.get(i).getRua() + ";" + dentistas.get(i).getNumero() + ";" + dentistas.get(i).getComp() + ";" +
						dentistas.get(i).getBairro() + ";" + dentistas.get(i).getCidade() + ";" + dentistas.get(i).getTelefone() + ";" + dentistas.get(i).getCro();
				arqD.write(linha + "\n");
			}
		}
		arqD.close();
	}
}

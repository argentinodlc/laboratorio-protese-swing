package dados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dados.interfaces.IRepoClinica;
import sistema.Laboratorio;
import sistema.classes.Clinica;
import sistema.classes.Dentista;
import sistema.interfaces.ILaboratorio;

public class RepoClinica implements IRepoClinica {
	private ArrayList<Clinica> clinicas = new ArrayList<Clinica>();

	@Override
	public boolean newClinica(String nome, String cnpj, long telefone, String logra, long num, String comp,
			String bairro, String cidade) {
		Clinica c;
		c = Clinica.newInstance(nome, logra, bairro, cidade, num, telefone, cnpj, comp);
		if (c != null) {
			clinicas.add(c);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ArrayList<Clinica> getClinicas() {
		return clinicas;
	}

	@Override
	public boolean addDentistaNaClinica(Clinica c, Dentista d) {
		if (c != null) {
			if (d != null) {
				return c.addDentistaNaClinica(d);
			}
		}
		return false;
	}
	
	@Override
	public ArrayList<Dentista> getDentistasDaClinica(Clinica c) {
		return c.getDentistasDaClinica();
	}

	@Override
	public boolean removeDentistaDaClinica(long c, long cod) {
		if (cod > -1 && c > -1) {
			for (int i = 0; i < clinicas.size(); i++) {
				if (c == clinicas.get(i).getCod()) {
					if (clinicas.get(i).removeDentistaDaClinica(cod))
					return true;
					else
						return false;
				}
			}
		}
		return false;
	}

	@Override
	public boolean alterClinica(long cod, String nome, String cnpj, long telefone, String logra, long num, String comp,
			String bairro, String cidade) {
		if (clinicas != null) {
			for (int i = 0; i < clinicas.size(); i++) {
				if (cod == clinicas.get(i).getCod()) {
					clinicas.get(i).setBairro(bairro);
					clinicas.get(i).setCidade(cidade);
					clinicas.get(i).setComp(comp);
					clinicas.get(i).setNome(nome);
					clinicas.get(i).setNumero(num);
					clinicas.get(i).setRua(logra);
					clinicas.get(i).setTelefone(telefone);
					clinicas.get(i).setCNPJ(cnpj);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean excluirClinica(long cod) {
		for (int i = 0; i < clinicas.size(); i++) {
			if (cod == clinicas.get(i).getCod()) {
				clinicas.remove(i);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void carregar(ArrayList<Dentista> arrayList) throws IOException, ParseException, IndexOutOfBoundsException {
		String user = System.getProperty("user.home");
		File diretorio = new File(user + "\\.sistemaDentArt");
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		File arq = new File(diretorio, "clinicas.txt");
		if (!arq.exists())
			arq.createNewFile();
		else {
			Scanner leitor = new Scanner(arq);
			List<String> linhas = new ArrayList<>();
			while (leitor.hasNextLine()) {
			    linhas.add(leitor.nextLine());
			}
			Clinica.setGeraCod(Long.parseLong(linhas.get(0)));
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
				String cpf = array[8];
				Clinica c = Clinica.newInstance(nome, rua, bairro, cidade, numero, telefone, cpf, comp, cod);
				if (array.length>9){
					for (int j = 9; j < array.length; j++) {
						for (int k = 0; k < arrayList.size(); k++) {
							if (Long.parseLong(array[j]) == arrayList.get(k).getCod()) {
								c.addDentistaNaClinica(arrayList.get(k));
							}
						}
					}
				}
				clinicas.add(c);
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
		File arq = new File(diretorio, "clinicas.txt");
		if (!arq.exists())
			arq.createNewFile();
		String path = arq.getAbsolutePath();
		String linha = "";
		BufferedWriter arqD = new BufferedWriter(new FileWriter(path));
		arqD.write(String.valueOf(Clinica.getGeraCod()) + "\n");
		for (int i = 0; i < clinicas.size(); i++) {
			linha = "";
			if (clinicas.get(i) == null) {
			} else {
				linha = clinicas.get(i).getCod() + ";"+ clinicas.get(i).getNome() + ";"+ clinicas.get(i).getRua() + ";" + clinicas.get(i).getNumero() + ";" + clinicas.get(i).getComp() + ";" +
						clinicas.get(i).getBairro() + ";" + clinicas.get(i).getCidade() + ";" + clinicas.get(i).getTelefone() + ";" + clinicas.get(i).getCNPJ();
				ArrayList<Dentista> d = null;
				try {
					d = clinicas.get(i).getDentistasDaClinica();
					if (d.size()>0) {
						for (int j = 0; j < d.size(); j++) {
							linha = linha + ";"+d.get(j).getCod();
						}
					}
				} catch (Exception e) {}
				arqD.write(linha + "\n");
			}
		}
		arqD.close();
	}
}

package dados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dados.interfaces.IRepoPaciente;
import sistema.classes.Dentista;
import sistema.classes.Paciente;

public class RepoPaciente implements IRepoPaciente {
	private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();

	@Override
	public boolean addPaciente(String nome) {
		Paciente p = Paciente.newInstance(nome);
		if (p != null) {
			pacientes.add(p);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}

	@Override
	public boolean alterPaciente(long cod, String nome) {
		if (pacientes != null) {
			for (int i = 0; i < pacientes.size(); i++) {
				if (pacientes.get(i).getCod() == cod) {
					pacientes.get(i).setNome(nome);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean excluirPaciente(long cod) {
		if (pacientes != null) {
			for (int i = 0; i < pacientes.size(); i++) {
				if (pacientes.get(i).getCod() == cod) {
					pacientes.remove(i);
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
		File arq = new File(diretorio, "pacientes.txt");
		if (!arq.exists())
			arq.createNewFile();
		else {
			Scanner leitor = new Scanner(arq);
			List<String> linhas = new ArrayList<>();
			while (leitor.hasNextLine()) {
			    linhas.add(leitor.nextLine());
			}
			Paciente.setGeraCod(Long.parseLong(linhas.get(0)));
			for (int i = 1; i<linhas.size();i++) {
				String[] array = linhas.get(i).split(";");
				long cod = Long.parseLong(array[0]);
				String nome = array[1];
				Paciente p = Paciente.newInstance(nome, cod);
				pacientes.add(p);
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
		File arq = new File(diretorio, "pacientes.txt");
		if (!arq.exists())
			arq.createNewFile();
		String path = arq.getAbsolutePath();
		String linha = "";
		BufferedWriter arqD = new BufferedWriter(new FileWriter(path));
		arqD.write(String.valueOf(Paciente.getGeraCod()) + "\n");
		for (int i = 0; i < pacientes.size(); i++) {
			linha = "";
			if (pacientes.get(i) == null) {
			} else {
				linha = pacientes.get(i).getCod() + ";"+ pacientes.get(i).getNome();
				arqD.write(linha + "\n");
			}
		}
		arqD.close();
	}
}

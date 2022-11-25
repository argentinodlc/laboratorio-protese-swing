package dados.interfaces;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import sistema.classes.Paciente;

public interface IRepoPaciente {

	boolean addPaciente(String nome);
	ArrayList<Paciente> getPacientes();
	boolean alterPaciente(long cod,String nome);
	boolean excluirPaciente(long cod);
	void escrever() throws IOException;
	void carregar() throws IOException, ParseException, IndexOutOfBoundsException;
}

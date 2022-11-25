package sistema;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import dados.RepoPaciente;
import dados.interfaces.IRepoPaciente;
import sistema.classes.Paciente;

class ControlePaciente {
	private IRepoPaciente rp;
	
	protected ControlePaciente() {
		rp = new RepoPaciente();
	}

	protected boolean addPaciente(String nome) {
		return rp.addPaciente(nome);
	}
	
	protected ArrayList<Paciente> getPacientes(){
		return rp.getPacientes();
	}
	
	protected boolean alterPacientes(long cod, String nome) {
		return rp.alterPaciente(cod, nome);
	}

	protected boolean excluirPaciente(long cod) {
		return rp.excluirPaciente(cod);
	}
	
	void escrever() throws IOException {
		rp.escrever();
	}
	void carregar() throws IOException, ParseException, IndexOutOfBoundsException {
		rp.carregar();
	}
}

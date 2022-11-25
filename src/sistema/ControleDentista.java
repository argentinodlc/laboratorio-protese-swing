package sistema;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import dados.RepoDentista;
import dados.interfaces.IRepoDentista;
import sistema.classes.Dentista;

class ControleDentista {
	IRepoDentista rd;

	protected ControleDentista() {
		rd = new RepoDentista();
	}

	protected boolean newDentista(String nome, String cro, long tel, String logra, long numero, String comp, String bairro,
			String cidade) {
		return rd.newDentista(nome, cro, tel, logra, numero, comp, bairro, cidade);
	}

	protected ArrayList<Dentista> getArrayDentistas() {
		return rd.getArray();
	}

	protected boolean alterDentista(long cod, String nome, String cro, long telefone, String logra, long num, String comp,
			String bairro, String cidade) {
		return rd.alterDentista(cod, nome, cro, telefone, logra, num, comp, bairro, cidade);
	}

	protected boolean excluirDentista(long cod) {
		return rd.excluirDentista(cod);
	}
	
	void carregar() throws IOException, ParseException, IndexOutOfBoundsException{
		rd.carregar();
	}
	void escrever() throws IOException{
		rd.escrever();
	}
}

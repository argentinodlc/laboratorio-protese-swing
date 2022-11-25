package dados.interfaces;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import sistema.classes.Dentista;

public interface IRepoDentista {
	boolean newDentista(String nome, String cro, long tel, String logra, long numero, String comp, String bairro, String cidade);
	ArrayList<Dentista> getArray();
	boolean alterDentista(long cod, String nome, String cro, long telefone, String logra, long num, String comp,
			String bairro, String cidade);
	boolean excluirDentista(long cod);
	void escrever() throws IOException;
	void carregar() throws IOException, ParseException, IndexOutOfBoundsException;
}

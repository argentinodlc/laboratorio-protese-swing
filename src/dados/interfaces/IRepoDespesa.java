package dados.interfaces;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import sistema.classes.Despesa;

public interface IRepoDespesa {
	boolean newDespesa(String nome, Date data, double valor);
	boolean newDespesaFixa(String nome, double valor);
	ArrayList<Despesa> getArrayDespesa();
	ArrayList<Despesa> getArrayDespesaFixa();
	boolean removeDespesa(long cod);
	boolean removeDespesaFixa(long cod);
	void carregarFixo() throws IOException, ParseException, IndexOutOfBoundsException;
	void escreverFixo() throws IOException;
	void escrever() throws IOException;
	void carregar() throws IOException, ParseException, IndexOutOfBoundsException;
}

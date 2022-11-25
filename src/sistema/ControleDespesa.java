package sistema;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import dados.RepoDespesa;
import dados.interfaces.IRepoDespesa;
import sistema.classes.Despesa;

class ControleDespesa {
	private IRepoDespesa rd;
	protected ControleDespesa() {
		rd = new RepoDespesa();
	}
	boolean newDespesa(String nome, Date data, double valor){
		return rd.newDespesa(nome, data, valor);
	}
	boolean newDespesaFixa(String nome, double valor){
		return rd.newDespesaFixa(nome, valor);
	}
	ArrayList<Despesa> getArrayDespesa(){
		return rd.getArrayDespesa();
	}
	ArrayList<Despesa> getArrayDespesaFixa(){
		return rd.getArrayDespesaFixa();
	}
	boolean removeDespesa(long cod){
		return rd.removeDespesa(cod);
	}
	boolean removeDespesaFixa(long cod){
		return rd.removeDespesaFixa(cod);
	}
	void escrever() throws IOException {
		rd.escrever();
		rd.escreverFixo();
	}
	void carregar() throws IOException, ParseException, IndexOutOfBoundsException {
		rd.carregar();
		rd.carregarFixo();
	}
}

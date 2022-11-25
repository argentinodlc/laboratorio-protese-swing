package dados.interfaces;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import sistema.classes.Clinica;
import sistema.classes.Dentista;

public interface IRepoClinica {

	boolean newClinica(String nome, String cnpj, long telefone, String logra, long num, String comp, String bairro,
			String cidade);
	ArrayList<Clinica> getClinicas();
	boolean addDentistaNaClinica(Clinica c, Dentista d);
	ArrayList<Dentista> getDentistasDaClinica(Clinica c);
	boolean removeDentistaDaClinica(long c, long index);
	boolean alterClinica(long cod, String nome, String cnpj, long telefone, String logra, long num, String comp,
			String bairro, String cidade);
	boolean excluirClinica(long cod);
	void carregar(ArrayList<Dentista> arrayList) throws IOException, ParseException, IndexOutOfBoundsException;
	void escrever() throws IOException;
}

package sistema;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import dados.RepoClinica;
import dados.interfaces.IRepoClinica;
import sistema.classes.Clinica;
import sistema.classes.Dentista;

class ControleClinica {
	private IRepoClinica rc;
	public ControleClinica() {
		rc = new RepoClinica();
	}
	protected boolean newClinica(String nome, String cnpj, long telefone, String logra, long num, String comp,
			String bairro, String cidade) {
		return rc.newClinica(nome, cnpj, telefone, logra, num, comp, bairro, cidade);
	}
	protected ArrayList<Clinica> getClinicas() {
		return rc.getClinicas();
	}
	
	protected boolean addDentistaNaClinica(Clinica c, Dentista d) {
		return rc.addDentistaNaClinica(c, d);
	}
	
	protected ArrayList<Dentista> getDentistasDaClinica(Clinica c) {
		return rc.getDentistasDaClinica(c);
	}
	
	protected boolean removeDentistaDaClinica(long c, long index) {
		return rc.removeDentistaDaClinica(c, index);
	}
	protected boolean alterClinica(long cod, String nome, String cnpj, long telefone, String logra, long num, String comp,
			String bairro, String cidade) {
		return rc.alterClinica(cod, nome, cnpj, telefone, logra, num, comp, bairro, cidade);
	}
	protected boolean excluirClinica(long cod) {
		return rc.excluirClinica(cod);
	}
	void carregar(ArrayList<Dentista> arrayList) throws IOException, ParseException, IndexOutOfBoundsException {
		rc.carregar(arrayList);
	}
	void escrever() throws IOException {
		rc.escrever();
	}
}

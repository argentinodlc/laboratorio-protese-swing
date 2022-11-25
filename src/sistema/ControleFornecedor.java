package sistema;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import dados.RepoFornecedor;
import dados.interfaces.IRepoFornecedor;
import sistema.classes.Fornecedor;

class ControleFornecedor {
	private IRepoFornecedor rf;
	
	protected ControleFornecedor() {
		rf = new RepoFornecedor();
	}
	protected boolean newFornecedor(String nome, long tel2, String logra, int num, String bairro,String cidade, String cnpj, String comp) {
		return rf.newFornecedor(nome, tel2, logra, num, bairro, cidade, cnpj, comp);
	}
	protected ArrayList<Fornecedor> getArrayFornecedores() {
		return rf.getArrayFornecedores();
	}
	protected boolean alterFornecedor(long cod, String nome, long tel2, String logra, int num, String bairro,String cidade, String cnpj, String comp) {
		return rf.alterFornecedor(cod, nome, tel2, logra, num, bairro, cidade, cnpj, comp);
	}
	protected boolean excluirFornecedor(long cod) {
		return rf.excluirFornecedor(cod);
	}
	void escrever() throws IOException{
		rf.escrever();
	}
	void carregar() throws IOException, ParseException, IndexOutOfBoundsException{
		rf.carregar();
	}
}

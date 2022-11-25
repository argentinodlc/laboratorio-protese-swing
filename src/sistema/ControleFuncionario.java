package sistema;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import dados.RepoFuncionario;
import dados.interfaces.IRepoFuncionario;
import sistema.classes.Funcionario;

class ControleFuncionario {
	private IRepoFuncionario rp;
	
	protected ControleFuncionario(){
		rp = new RepoFuncionario();
	}
	
	protected ArrayList<Funcionario> getArrayFunc() {
		return rp.getArrayFunc();
	}
	protected boolean newFuncionario(String nome, String cpf, double comissao, double salario) {
		return rp.newFuncionario(nome, cpf, comissao, salario);
	}
	protected boolean alterFuncionario(long cod, String nome, String cpf, double comissao, double salario) {
		return rp.alterFuncionario(cod, nome, cpf, comissao, salario);
	}
	protected boolean excluirFuncionario(long cod) {
		return rp.excluirFuncionario(cod);
	}
	void carregar() throws IOException, ParseException, IndexOutOfBoundsException{
		rp.carregar();
	}
	void escrever() throws IOException{
		rp.escrever();
	}
}

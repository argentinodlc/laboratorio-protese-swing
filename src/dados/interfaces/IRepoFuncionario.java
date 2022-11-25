package dados.interfaces;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import sistema.classes.Funcionario;

public interface IRepoFuncionario {
	ArrayList<Funcionario> getArrayFunc();
	boolean newFuncionario(String nome, String cpf, double comissao, double salario);
	boolean alterFuncionario(long cod, String nome, String cpf, double comissao, double salario);
	boolean excluirFuncionario(long cod);
	void carregar() throws IOException, ParseException, IndexOutOfBoundsException;
	void escrever() throws IOException;
}

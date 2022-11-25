package dados.interfaces;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import sistema.classes.Fornecedor;

public interface IRepoFornecedor {
	boolean newFornecedor(String nome, long tel2, String logra, int num, String bairro,String cidade, String cnpj, String comp);
	ArrayList<Fornecedor> getArrayFornecedores();
	boolean alterFornecedor(long cod, String nome, long tel2, String logra, int num, String bairro,String cidade, String cnpj, String comp);
	boolean excluirFornecedor(long cod);
	void escrever() throws IOException;
	void carregar() throws IOException, ParseException, IndexOutOfBoundsException;
}

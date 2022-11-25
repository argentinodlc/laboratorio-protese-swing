package dados.interfaces;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import sistema.classes.Compra;
import sistema.classes.Fornecedor;
import sistema.classes.Produto;

public interface IRepoCompra {
	boolean newCompra(String descricao, double valor, Date data, Fornecedor fornecedor);
	ArrayList<Compra> getArrayCompras();
	boolean alterCompra(long cod, String descricao, double valor, Date data, Fornecedor fornecedor);
	boolean excluirCompra(long cod);
	void escrever() throws IOException;
	void carregar(ArrayList<Fornecedor> f) throws IOException, ParseException, IndexOutOfBoundsException;
}

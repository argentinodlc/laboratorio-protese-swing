package dados.interfaces;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import sistema.classes.Produto;

public interface IRepoProduto {
	
	boolean newProduto(String nome, double valorMedio);
	ArrayList<Produto> getArrayProdutos();
	boolean alterProduto(long cod, String nome, double valorMedio);
	boolean excluirProduto(long cod);
	void carregar() throws IOException, ParseException, IndexOutOfBoundsException;
	void escrever() throws IOException;
}

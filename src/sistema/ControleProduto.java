package sistema;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import dados.RepoProduto;
import dados.interfaces.IRepoProduto;
import sistema.classes.Produto;

class ControleProduto {
	
	private IRepoProduto rp;
	
	protected ControleProduto(){
		rp = new RepoProduto();
	}
	
	protected boolean newProduto(String nome, double valorMedio) {
		return rp.newProduto(nome, valorMedio);
	}
	
	protected ArrayList<Produto> getArrayProdutos() {
		return rp.getArrayProdutos();
	}
	
	protected boolean alterProduto(long cod, String nome, double valorMedio) {
		return rp.alterProduto(cod, nome, valorMedio);
	}
	
	protected boolean excluirProduto(long cod) {
		return rp.excluirProduto(cod);
	}
	void carregar() throws IOException, ParseException, IndexOutOfBoundsException {
		rp.carregar();
	}
	void escrever() throws IOException {
		rp.escrever();
	}
}

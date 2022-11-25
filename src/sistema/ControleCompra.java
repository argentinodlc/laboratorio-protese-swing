package sistema;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import dados.RepoCompra;
import dados.interfaces.IRepoCompra;
import sistema.classes.Compra;
import sistema.classes.Fornecedor;
import sistema.classes.Produto;

class ControleCompra {
	private IRepoCompra rc;

	protected ControleCompra() {
		rc = new RepoCompra();
	}
	
	protected boolean newCompra(String descricao, double valor, Date data, Fornecedor fornecedor) {
		return rc.newCompra(descricao, valor, data, fornecedor);
	}
	protected ArrayList<Compra> getArrayCompras() {
		return rc.getArrayCompras();
	}
	protected boolean alterCompra(long cod, String descricao, double valor, Date data, Fornecedor fornecedor) {
		return rc.alterCompra(cod, descricao, valor, data, fornecedor);
	}
	protected boolean excluirCompra(long cod) {
		return rc.excluirCompra(cod);
	}
	void escrever() throws IOException {
		rc.escrever();
	}
	void carregar(ArrayList<Fornecedor> f) throws IOException, ParseException, IndexOutOfBoundsException {
		rc.carregar(f);
	}
}

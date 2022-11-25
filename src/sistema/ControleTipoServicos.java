package sistema;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import dados.RepoTipoServicos;
import dados.interfaces.IRepoTipoServicos;
import sistema.classes.TipoServico;

class ControleTipoServicos {
	
	private IRepoTipoServicos rt;
	
	protected ControleTipoServicos() {
		rt = new RepoTipoServicos();
	}
	
	protected ArrayList<TipoServico> getArrayTipos(){
		return rt.getArrayTipos();
	}
	
	protected boolean newTipoServico(String nome, double preco) {
		return rt.newTipoServico(nome, preco);
	}
	
	protected boolean alterarTipoServico(long cod, String nome, double preco, double valorComissao) {
		return rt.alterarTipoServico(cod, nome, preco, valorComissao);
	}
	
	protected boolean excluirTipoServico(long cod) {
		return rt.excluirTipoServico(cod);
	}
	void carregar() throws IOException, ParseException, IndexOutOfBoundsException {
		rt.carregar();
	}
	void escrever() throws IOException{
		rt.escrever();
	}
}

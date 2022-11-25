package dados.interfaces;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import sistema.classes.TipoServico;

public interface IRepoTipoServicos {
	ArrayList<TipoServico> getArrayTipos();
	boolean newTipoServico(String nome, double preco);
	boolean excluirTipoServico(long cod);
	void carregar() throws IOException, ParseException, IndexOutOfBoundsException;
	void escrever() throws IOException;
	boolean alterarTipoServico(long cod, String nome, double preco, double valorComissao);
}

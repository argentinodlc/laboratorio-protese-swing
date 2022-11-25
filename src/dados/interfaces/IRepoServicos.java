package dados.interfaces;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import sistema.classes.Cliente;
import sistema.classes.Clinica;
import sistema.classes.Dentista;
import sistema.classes.Fornecedor;
import sistema.classes.Funcionario;
import sistema.classes.Paciente;
import sistema.classes.Servicos;

import sistema.classes.TipoServico;

public interface IRepoServicos {
	ArrayList<Servicos> getArrayServicos();
	boolean newServico(String descricao, double valorFinal, Cliente cliente, Date entrada,
			Funcionario funcionario, Paciente paciente, TipoServico tipo, String situacao, boolean pagamento, Date saida);
	boolean alterServico(long cod, boolean pagamento, String situacao, String descricao,
			double valorFinal, Cliente cliente, Date entrada, Date saida, Funcionario funcionario,
			Paciente paciente, TipoServico tipo);
	boolean excluirServico(long cod);
	void escrever() throws IOException;
	void carregar(ArrayList<Dentista> d, ArrayList<Clinica> c, ArrayList<Funcionario> f, ArrayList<Paciente> p,
			ArrayList<TipoServico> t) throws IOException, ParseException, IndexOutOfBoundsException;
	boolean isPago(long cod);
	boolean setPago(long cod, boolean pago);
}

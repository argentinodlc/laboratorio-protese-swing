package sistema;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import dados.RepoServicos;
import dados.interfaces.IRepoServicos;
import sistema.classes.Cliente;
import sistema.classes.Clinica;
import sistema.classes.Dentista;
import sistema.classes.Funcionario;
import sistema.classes.Paciente;
import sistema.classes.Servicos;
import sistema.classes.TipoServico;

class ControleServicos {
	private IRepoServicos rs;

	protected ControleServicos() {
		rs = new RepoServicos();
	}

	protected ArrayList<Servicos> getArrayServicos() {
		return rs.getArrayServicos();
	}

	protected boolean newServico(String descricao, double valorFinal, Cliente cliente, Date entrada,
			Funcionario funcionario, Paciente paciente, TipoServico tipo, String situacao, boolean pagamento, Date saida) {
		return rs.newServico(descricao, valorFinal, cliente, entrada, funcionario, paciente,tipo, situacao, pagamento, saida);
	}

	protected boolean alterServico(long cod, boolean pagamento, String situacao, String descricao, double valorFinal,
			Cliente cliente, Date entrada, Date saida, Funcionario funcionario, Paciente paciente, TipoServico tipo) {
		return rs.alterServico(cod, pagamento, situacao, descricao, valorFinal, cliente, entrada, saida, funcionario,
				paciente, tipo);
	}

	protected boolean excluirServico(long cod) {
		return rs.excluirServico(cod);
	}
	
	boolean isPago(long cod) {
		return rs.isPago(cod);
	}
	
	boolean setPago(long cod, boolean pago) {
		return rs.setPago(cod, pago);
	}
	
	void escrever() throws IOException {
		rs.escrever();
	}
	void carregar(ArrayList<Dentista> d, ArrayList<Clinica> c, ArrayList<Funcionario> f, ArrayList<Paciente> p, ArrayList<TipoServico> t)
			throws IOException, ParseException, IndexOutOfBoundsException {
		rs.carregar(d, c, f, p, t);
	}
}

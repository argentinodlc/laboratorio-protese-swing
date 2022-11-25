package sistema.classes;

import java.util.Date;

public class Servicos {
	private static long geraCod = 0;
	private long cod;
	private boolean pagamento;
	private String situacao;
	private String descricao;
	private double valorFinal;
	private Cliente cliente;
	private Date entrada;
	private Date saida;
	private Funcionario funcionario;
	private Paciente paciente;
	private TipoServico tipo;
	private boolean pago;
	
	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	private Servicos(String descricao, double valorFinal, Cliente cliente,
			Date entrada, Funcionario funcionario, Paciente paciente, TipoServico tipo,
			String situacao, boolean pagamento, Date saida) {
		geraCod = geraCod + 1;
		cod = geraCod;
		this.pagamento = pagamento;
		this.situacao = situacao;
		this.descricao = descricao;
		this.valorFinal = valorFinal;
		this.cliente = cliente;
		this.entrada = entrada;
		this.saida = saida;
		this.funcionario = funcionario;
		this.paciente = paciente;
		this.tipo = tipo;
		this.pago = false;
	}
	
	private Servicos(long cod, String descricao, double valorFinal, Cliente cliente,
	Date entrada, Funcionario funcionario, Paciente paciente, TipoServico tipo, String situacao,
	boolean pagamento, Date saida, boolean pago) {
		this.cod = cod;
		this.pagamento = pagamento;
		this.situacao = situacao;
		this.descricao = descricao;
		this.valorFinal = valorFinal;
		this.cliente = cliente;
		this.entrada = entrada;
		this.saida = saida;
		this.funcionario = funcionario;
		this.paciente = paciente;
		this.tipo = tipo;
		this.pago = pago;
	}

	public TipoServico getTipo() {
		return tipo;
	}

	public void setTipo(TipoServico tipo) {
		this.tipo = tipo;
	}

	public static Servicos newInstance(String descricao, double valorFinal, Cliente cliente,
			Date entrada, Funcionario funcionario, Paciente paciente, TipoServico tipo,
			String situacao, boolean pagamento, Date saida) {
		if (valorFinal >= 0 && cliente != null && entrada != null && funcionario != null && tipo !=null)
			return new Servicos(descricao, valorFinal, cliente, entrada, funcionario, paciente,tipo, situacao, pagamento, saida);
		else
			return null;
	}

	public static long getGeraCod() {
		return geraCod;
	}

	public static void setGeraCod(long geraCod) {
		Servicos.geraCod = geraCod;
	}

	public long getCod() {
		return cod;
	}

	public boolean isPagamento() {
		return pagamento;
	}

	public void setPagamento(boolean pagamento) {
		this.pagamento = pagamento;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getEntrada() {
		return entrada;
	}

	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}

	public Date getSaida() {
		return saida;
	}

	public void setSaida(Date saida) {
		this.saida = saida;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public static Servicos newInstance(long cod, String descricao, double valorFinal, Cliente cliente,
			Date entrada, Funcionario funcionario, Paciente paciente, TipoServico tipo, String situacao,
			boolean pagamento, Date saida, boolean pago) {
		return new Servicos(cod, descricao, valorFinal, cliente, entrada, funcionario, paciente, tipo, situacao, pagamento, saida, pago);
	}
	
}

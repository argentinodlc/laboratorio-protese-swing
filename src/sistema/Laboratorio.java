package sistema;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import sistema.classes.Cliente;
import sistema.classes.Clinica;
import sistema.classes.Compra;
import sistema.classes.Dentista;
import sistema.classes.Despesa;
import sistema.classes.Fornecedor;
import sistema.classes.Funcionario;
import sistema.classes.Paciente;
import sistema.classes.Produto;
import sistema.classes.Servicos;

import sistema.classes.TipoServico;
import sistema.interfaces.ILaboratorio;

public class Laboratorio implements ILaboratorio {
	private static Laboratorio instance;
	private ControleDentista cd;
	private ControleClinica cc;
	private ControlePaciente cp;
	private ControleFuncionario cf;
	private ControleTipoServicos ct;
	private ControleServicos cs;
	private ControleProduto cpr;
	private ControleFornecedor cfo;
	private ControleCompra cco;
	private ControleDespesa cde;

	public static Laboratorio getInstance() {
		if (instance == null) {
			instance = new Laboratorio();
		}
		return instance;
	}

	private Laboratorio() {
		cd = new ControleDentista();
		cc = new ControleClinica();
		cp = new ControlePaciente();
		cf = new ControleFuncionario();
		ct = new ControleTipoServicos();
		cs = new ControleServicos();
		cpr = new ControleProduto();
		cfo = new ControleFornecedor();
		cco = new ControleCompra();
		cde = new ControleDespesa();
	}

	@Override
	public boolean newDentista(String nome, String cro, long tel, String logra, long numero, String comp, String bairro,
			String cidade) {
		return cd.newDentista(nome, cro, tel, logra, numero, comp, bairro, cidade);
	}

	@Override
	public ArrayList<Dentista> getArrayDentistas() {
		return cd.getArrayDentistas();
	}

	@Override
	public boolean alterDentista(long cod, String nome, String cro, long telefone, String logra, long num, String comp,
			String bairro, String cidade) {
		return cd.alterDentista(cod, nome, cro, telefone, logra, num, comp, bairro, cidade);
	}

	@Override
	public boolean excluirDentista(long cod) {
		return cd.excluirDentista(cod);
	}

	@Override
	public boolean newClinica(String nome, String cnpj, long telefone, String logra, long num, String comp,
			String bairro, String cidade) {
		return cc.newClinica(nome, cnpj, telefone, logra, num, comp, bairro, cidade);
	}

	@Override
	public ArrayList<Clinica> getClinicas() {
		return cc.getClinicas();
	}

	@Override
	public boolean addDentistaNaClinica(Clinica c, Dentista d) {
		return cc.addDentistaNaClinica(c, d);
	}

	@Override
	public ArrayList<Dentista> getDentistasDaClinica(Clinica c) {
		return cc.getDentistasDaClinica(c);
	}

	@Override
	public boolean removeDentistaDaClinica(long c, long index) {
		return cc.removeDentistaDaClinica(c, index);
	}

	@Override
	public boolean alterClinica(long cod, String nome, String cnpj, long telefone, String logra, long num, String comp,
			String bairro, String cidade) {
		return cc.alterClinica(cod, nome, cnpj, telefone, logra, num, comp, bairro, cidade);
	}

	@Override
	public boolean excluirClinica(long cod) {
		return cc.excluirClinica(cod);
	}

	@Override
	public boolean addPaciente(String nome) {
		return cp.addPaciente(nome);
	}

	@Override
	public ArrayList<Paciente> getPacientes() {
		return cp.getPacientes();
	}

	public boolean alterPacientes(long cod, String nome) {
		return cp.alterPacientes(cod, nome);
	}

	@Override
	public boolean excluirPaciente(long cod) {
		return cp.excluirPaciente(cod);
	}

	@Override
	public ArrayList<Funcionario> getArrayFunc() {
		return cf.getArrayFunc();
	}

	@Override
	public boolean newFuncionario(String nome, String cpf, double comissao, double salario) {
		return cf.newFuncionario(nome, cpf, comissao, salario);
	}

	@Override
	public boolean alterFuncionario(long cod, String nome, String cpf, double comissao, double salario) {
		return cf.alterFuncionario(cod, nome, cpf, comissao, salario);
	}

	@Override
	public boolean excluirFuncionario(long cod) {
		return cf.excluirFuncionario(cod);
	}

	public ArrayList<TipoServico> getArrayTipos() {
		return ct.getArrayTipos();
	}

	public boolean newTipoServico(String nome, double preco) {
		return ct.newTipoServico(nome, preco);
	}

	public boolean alterarTipoServico(long cod, String nome, double preco, double valorComissao) {
		return ct.alterarTipoServico(cod, nome, preco, valorComissao);
	}

	public boolean excluirTipoServico(long cod) {
		return ct.excluirTipoServico(cod);
	}

	@Override
	public ArrayList<Servicos> getArrayServicos() {
		return cs.getArrayServicos();
	}

	@Override
	public boolean newServico(String descricao, double valorFinal, Cliente cliente, Date entrada,
			Funcionario funcionario, Paciente paciente, TipoServico tipo, String situacao, boolean pagamento,
			Date saida) {
		return cs.newServico(descricao, valorFinal, cliente, entrada, funcionario, paciente, tipo, situacao, pagamento,
				saida);
	}

	@Override
	public boolean alterServico(long cod, boolean pagamento, String situacao, String descricao, double valorFinal,
			Cliente cliente, Date entrada, Date saida, Funcionario funcionario, Paciente paciente, TipoServico tipo) {
		return cs.alterServico(cod, pagamento, situacao, descricao, valorFinal, cliente, entrada, saida, funcionario,
				paciente, tipo);
	}

	@Override
	public boolean excluirServico(long cod) {
		return cs.excluirServico(cod);
	}
	
	@Override
	public boolean isPago(long cod) {
		return cs.isPago(cod);
	}
	
	@Override
	public boolean setPago(long cod, boolean pago) {
		return cs.setPago(cod, pago);
	}
	
	@Override
	public boolean newProduto(String nome, double valorMedio) {
		return cpr.newProduto(nome, valorMedio);
	}

	@Override
	public ArrayList<Produto> getArrayProdutos() {
		return cpr.getArrayProdutos();
	}

	@Override
	public boolean alterProduto(long cod, String nome, double valorMedio) {
		return cpr.alterProduto(cod, nome, valorMedio);
	}

	@Override
	public boolean excluirProduto(long cod) {
		return cpr.excluirProduto(cod);
	}

	@Override
	public boolean newFornecedor(String nome, long tel2, String logra, int num, String bairro, String cidade,
			String cnpj, String comp) {
		return cfo.newFornecedor(nome, tel2, logra, num, bairro, cidade, cnpj, comp);
	}

	@Override
	public ArrayList<Fornecedor> getArrayFornecedores() {
		return cfo.getArrayFornecedores();
	}

	@Override
	public boolean alterFornecedor(long cod, String nome, long tel2, String logra, int num, String bairro,
			String cidade, String cnpj, String comp) {
		return cfo.alterFornecedor(cod, nome, tel2, logra, num, bairro, cidade, cnpj, comp);
	}

	@Override
	public boolean excluirFornecedor(long cod) {
		return cfo.excluirFornecedor(cod);
	}

	@Override
	public boolean newCompra(String descricao, double valor, Date data, Fornecedor fornecedor) {
		return cco.newCompra(descricao, valor, data, fornecedor);
	}

	@Override
	public ArrayList<Compra> getArrayCompras() {
		return cco.getArrayCompras();
	}

	@Override
	public boolean alterCompra(long cod, String descricao, double valor, Date data, Fornecedor fornecedor) {
		return cco.alterCompra(cod, descricao, valor, data, fornecedor);
	}

	@Override
	public boolean excluirCompra(long cod) {
		return cco.excluirCompra(cod);
	}

	@Override
	public boolean newDespesa(String nome, Date data, double valor) {
		return cde.newDespesa(nome, data, valor);
	}

	@Override
	public boolean newDespesaFixa(String nome, double valor) {
		return cde.newDespesaFixa(nome, valor);
	}

	@Override
	public ArrayList<Despesa> getArrayDespesa() {
		return cde.getArrayDespesa();
	}

	@Override
	public ArrayList<Despesa> getArrayDespesaFixa() {
		return cde.getArrayDespesaFixa();
	}

	@Override
	public boolean removeDespesa(long cod) {
		return cde.removeDespesa(cod);
	}

	@Override
	public boolean removeDespesaFixa(long cod) {
		return cde.removeDespesaFixa(cod);
	}

	@Override
	public void carregar() throws IndexOutOfBoundsException, IOException, ParseException {
		cd.carregar();
		cp.carregar();
		cf.carregar();
		cc.carregar(this.getArrayDentistas());
		cpr.carregar();
		cfo.carregar();
		cco.carregar(this.getArrayFornecedores());
		cde.carregar();
		ct.carregar();
		cs.carregar(getArrayDentistas(), getClinicas(), getArrayFunc(), getPacientes(), getArrayTipos());
	}

	@Override
	public void escrever() throws IOException {
		cd.escrever();
		cp.escrever();
		cf.escrever();
		cc.escrever();
		cpr.escrever();
		cfo.escrever();
		cco.escrever();
		cde.escrever();
		ct.escrever();
		cs.escrever();
	}
}

package sistema.interfaces;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

public interface ILaboratorio {
	boolean newDentista(String nome, String cro, long tel, String logra, long numero, String comp, String bairro,
			String cidade);

	ArrayList<Dentista> getArrayDentistas();

	boolean alterDentista(long cod, String nome, String cro, long telefone, String logra, long num, String comp,
			String bairro, String cidade);

	boolean excluirDentista(long cod);

	boolean newClinica(String nome, String cnpj, long telefone, String logra, long num, String comp, String bairro,
			String cidade);

	ArrayList<Clinica> getClinicas();

	boolean addDentistaNaClinica(Clinica c, Dentista d);

	ArrayList<Dentista> getDentistasDaClinica(Clinica c);

	boolean removeDentistaDaClinica(long cod, long index);

	boolean alterClinica(long cod, String nome, String cnpj, long telefone, String logra, long num, String comp,
			String bairro, String cidade);

	boolean excluirClinica(long cod);

	boolean addPaciente(String nome);

	ArrayList<Paciente> getPacientes();

	boolean alterPacientes(long cod, String nome);

	boolean excluirPaciente(long cod);

	ArrayList<Funcionario> getArrayFunc();

	boolean newFuncionario(String nome, String cpf, double comissao, double salario);

	boolean alterFuncionario(long cod, String nome, String cpf, double comissao, double salario);

	boolean excluirFuncionario(long cod);

	ArrayList<TipoServico> getArrayTipos();

	boolean newTipoServico(String nome, double preco);

	boolean alterarTipoServico(long cod, String nome, double preco, double valorComissao);

	boolean excluirTipoServico(long cod);

	ArrayList<Servicos> getArrayServicos();

	boolean excluirServico(long cod);

	boolean newServico(String descricao, double valorFinal, Cliente cliente, Date entrada, Funcionario funcionario,
			Paciente paciente, TipoServico tipo, String situacao, boolean pagamento, Date saida);

	boolean alterServico(long cod, boolean pagamento, String situacao, String descricao, double valorFinal,
			Cliente cliente, Date entrada, Date saida, Funcionario funcionario, Paciente paciente, TipoServico tipo);

	boolean newProduto(String nome, double valorMedio);

	ArrayList<Produto> getArrayProdutos();

	boolean alterProduto(long cod, String nome, double valorMedio);

	boolean excluirProduto(long cod);

	boolean newFornecedor(String nome, long tel2, String logra, int num, String bairro, String cidade, String cnpj,
			String comp);

	ArrayList<Fornecedor> getArrayFornecedores();

	boolean alterFornecedor(long cod, String nome, long tel2, String logra, int num, String bairro, String cidade,
			String cnpj, String comp);

	boolean excluirFornecedor(long cod);

	boolean newCompra(String descricao, double valor, Date data, Fornecedor fornecedor);

	ArrayList<Compra> getArrayCompras();

	boolean alterCompra(long cod, String descricao, double valor, Date data, Fornecedor fornecedor);

	boolean excluirCompra(long cod);

	boolean newDespesa(String nome, Date data, double valor);

	boolean newDespesaFixa(String nome, double valor);

	ArrayList<Despesa> getArrayDespesa();

	ArrayList<Despesa> getArrayDespesaFixa();

	boolean removeDespesa(long cod);

	boolean removeDespesaFixa(long cod);
	
	void carregar() throws IndexOutOfBoundsException, IOException, ParseException;
	
	void escrever() throws IOException;

	boolean isPago(long cod);

	boolean setPago(long cod, boolean pago);
}

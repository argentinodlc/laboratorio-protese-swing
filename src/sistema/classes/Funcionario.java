package sistema.classes;

import java.util.InputMismatchException;

public class Funcionario {
	private static long geraCod = 0;
	private long cod;
	private String nome;
	private String cpf;
	private double comissao;
	private double salario;
	
	public static Funcionario newInstance(String nome, String cpf, double comissao, double salario) {
		if (nome !=null && nome.length()>0 && comissao > 0 && salario > 0)
			return new Funcionario(nome, cpf, comissao, salario);
		else
			return null;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	private Funcionario(String nome, String cpf, double comissao, double salario) {
		this.nome = nome;
		this.cpf = cpf;
		this.comissao = comissao;
		geraCod++;
		this.cod = geraCod;
		this.salario = salario;
	}
	
	private Funcionario(String nome2, String cpf2, double comissao2, long cod2, double salario) {
		this.nome = nome2;
		this.cpf = cpf2;
		this.comissao = comissao2;
		this.cod = cod2;
		this.salario = salario;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public static long getGeraCod() {
		return geraCod;
	}
	public static void setGeraCod(long geraCod) {
		Funcionario.geraCod = geraCod;
	}
	public double getComissao() {
		return comissao;
	}
	public void setComissao(double comissao) {
		this.comissao = comissao;
	}
	public long getCod() {
		return cod;
	}
	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public static Funcionario newInstance(String nome2, String cpf2, double comissao2, long cod2, double salario) {
		return new Funcionario(nome2, cpf2, comissao2, cod2, salario);
	}
}

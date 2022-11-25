package sistema.classes;

import java.util.Date;

public class Despesa {
	private static long geraCod = 0;
	private long cod;
	private String nome;
	private Date data;
	private double valor;
	
	private Despesa(String nome, Date data, double valor) {
		geraCod++;
		cod = geraCod;
		this.nome = nome;
		this.data = data;
		this.valor = valor;
	}
	private Despesa(String nome, Date data, double valor, long cod) {
		this.cod = cod;
		this.nome = nome;
		this.data = data;
		this.valor = valor;
	}
	public static Despesa newInstance(String nome, Date data, double valor) {
		if (nome!=null && valor > 0) {
			return new Despesa(nome,data,valor);
 		} else
 			return null;
	}
	public static long getGeraCod() {
		return geraCod;
	}
	public static void setGeraCod(int geraCod) {
		Despesa.geraCod = geraCod;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public long getCod() {
		return cod;
	}
	public static void setGeraCod(long geraCod) {
		Despesa.geraCod = geraCod;
	}
	public static Despesa newInstance(String nome, Date data, double valor, long cod) {
		return new Despesa(nome, data, valor, cod);
	}
}

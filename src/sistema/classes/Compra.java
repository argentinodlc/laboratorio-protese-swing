package sistema.classes;

import java.util.ArrayList;
import java.util.Date;

public class Compra {
	private static long geraCod;
	private long cod;
	private String descricao;
	private double valor;
	private Date data;
	private Fornecedor fornecedor;
	
	private Compra(String descricao, double valor, Date data, Fornecedor fornecedor) {
		geraCod++;
		cod = geraCod;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.fornecedor = fornecedor;
	}
	public static Compra newInstance(String descricao, double valor, Date data, Fornecedor fornecedor, long cod) {
		return new Compra(descricao, valor, data, fornecedor, cod);
	}
	private Compra(String descricao, double valor, Date data, Fornecedor fornecedor, long cod) {
		this.cod = cod;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.fornecedor = fornecedor;
	}
	public static Compra newInstance(String descricao, double valor, Date data, Fornecedor fornecedor) {
		if (descricao != null && valor > 0 && data != null && fornecedor != null)
			return new Compra(descricao, valor, data, fornecedor);
		else 
			return null;
	}
	public static long getGeraCod() {
		return geraCod;
	}
	public static void setGeraCod(long geraCod) {
		Compra.geraCod = geraCod;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public long getCod() {
		return cod;
	}
	
}

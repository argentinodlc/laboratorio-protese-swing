package sistema.classes;

public class Produto {
	private static long geraCod = 0;
	private long cod;
	private String nome;
	private double valorMedio;
	
	public double getValorMedio() {
		return valorMedio;
	}
	public void setValorMedio(double valorMedio) {
		this.valorMedio = valorMedio;
	}
	public static Produto newInstance(String nome, double valorMedio) {
		if (nome != null && nome.length() > 0 && valorMedio > 0)
			return new Produto(nome, valorMedio);
		else
			return null;
	}
	private Produto(String nome, double valorMedio) {
		this.valorMedio = valorMedio;
		this.nome = nome;
		geraCod++;
		this.cod = geraCod;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public static long getGeraCod() {
		return geraCod;
	}
	public static void setGeraCod(long geraCod) {
		Produto.geraCod = geraCod;
	}
	public long getCod() {
		return cod;
	}
	public String getNome() {
		return nome;
	}
	public static Produto newInstance(String nome, double valor, long cod) {
		return new Produto(nome, valor, cod);
	}
	private Produto(String nome, double valorMedio, long cod) {
		this.valorMedio = valorMedio;
		this.nome = nome;
		this.cod = cod;
	}
}

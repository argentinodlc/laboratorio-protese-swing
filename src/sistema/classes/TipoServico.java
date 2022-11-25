package sistema.classes;

public class TipoServico {
	private static long geraCod = 0;
	private long cod;
	private String nome;
	private double preco;
	private double valorComissao;
	
	public double getValorComissao() {
		return valorComissao;
	}

	public void setValorComissao(double valorComissao) {
		this.valorComissao = valorComissao;
	}

	public static TipoServico newInstance(String nome, double preco) {
		if (nome != null && nome.length()>0 && preco >= 0)
			return new TipoServico(nome,preco);
		else
			return null;
	}
	
	private TipoServico(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
		geraCod++;
		this.cod = geraCod;
		this.valorComissao = preco;
	}
	
	public TipoServico(String nome, double preco, long cod, double valorComissao)  {
		this.nome = nome;
		this.preco = preco;
		this.cod = cod;
		this.valorComissao = valorComissao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public static long getGeraCod() {
		return geraCod;
	}
	public static void setGeraCod(long Cod) {
		geraCod = Cod;
	}
	public long getCod() {
		return cod;
	}
	
	public String getNome() {
		return nome;
	}
	public double getPreco() {
		return preco;
	}

	public static TipoServico newInstance(String nome, double preco, long cod, double valorComissao) {
		return new TipoServico(nome, preco, cod, valorComissao);
	}
}

package sistema.classes;

public class Cliente {
	public static long geraCod = 0;
	private long cod;
	private String nome;
	private String rua;
	private String bairro;
	private String cidade;
	private long numero;
	private String comp;
	private long telefone;
	
	public static long getGeraCod() {
		return geraCod;
	}
	public static void setGeraCod(long geraCod) {
		Cliente.geraCod = geraCod;
	}
	public long getCod() {
		return cod;
	}
	public void setCod(long cod) {
		this.cod = cod;
	}
	public String getNome() {
		return nome;
	}
	public String getRua() {
		return rua;
	}
	
	protected Cliente(String nome, String rua, String bairro, String cidade, long numero, long telefone, String comp) {
		this.nome = nome;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.numero = numero;
		this.telefone = telefone;
		this.comp = comp;
		geraCod = geraCod + 1;
		this.cod = geraCod;
	}
	
	protected Cliente(String nome, String rua, String bairro, String cidade, long numero, long telefone, String comp, long cod) {
		this.nome = nome;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.numero = numero;
		this.telefone = telefone;
		if (comp.equals("null")) 
			this.comp=null;
		else
			this.comp = comp;
		this.cod = cod;
	}
	
	public static Cliente newInstance(String nome, String rua, String bairro, String cidade, long numero, long telefone, String comp) {
		if (nome != null && nome.length()>0 && rua != null && rua.length() > 0 && bairro != null && bairro.length() > 0
			&& cidade != null && cidade.length() > 0 && numero > 0 && telefone > 0) {
				return new Cliente(nome, rua, bairro, cidade, numero, telefone, comp);
		} else {
			return null;
		}
	}

	public String getBairro() {
		return bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public long getNumero() {
		return numero;
	}
	public long getTelefone() {
		return telefone;
	}
	
	public String getComp() {
		return comp;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	public void setComp(String comp) {
		this.comp = comp;
	}
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}
	
}

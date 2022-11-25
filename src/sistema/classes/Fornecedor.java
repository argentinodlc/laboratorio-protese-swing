package sistema.classes;

public class Fornecedor {
	private static long geraCod = 0;
	private long cod;
	private String nome;
	private long tel;
	private String logra;
	private int num;
	private String bairro;
	private String cidade;
	private String cnpj;
	private String comp;

	public static long getGeraCod() {
		return geraCod;
	}

	public static Fornecedor newInstance(String nome, long tel2, String logra, int num, String bairro, String cidade,
			String cnpj, String comp) {
		if (nome != null && tel2 > 0 && logra != null && num > 0 && bairro != null && cidade != null && cnpj != null) {
			return new Fornecedor(nome, tel2, logra, num, bairro, cnpj, comp, cidade);
		}
		return null;
	}

	private Fornecedor(String nome, long tel2, String logra, int num, String bairro, String cnpj, String comp,
			String cidade) {
		geraCod++;
		cod = geraCod;
		this.nome = nome;
		this.tel = tel2;
		this.logra = logra;
		this.num = num;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cnpj = cnpj;
		this.comp = comp;
	}

	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public long getCod() {
		return cod;
	}

	public static void setGeraCod(long geraCod) {
		Fornecedor.geraCod = geraCod;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTel(long tel2) {
		this.tel = tel2;
	}

	public void setLogra(String logra) {
		this.logra = logra;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNome() {
		return nome;
	}

	public long getTel() {
		return tel;
	}

	public String getLogra() {
		return logra;
	}

	public int getNum() {
		return num;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String imprimeCNPJ() {
		String CNPJ = this.cnpj;
		return (CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "." + CNPJ.substring(5, 8) + "."
				+ CNPJ.substring(8, 12) + "-" + CNPJ.substring(12, 14));
	}

	public static Fornecedor newInstance(String nome2, String rua, String bairro2, String cidade2, int numero,
			long tel2, String cnpj2, String comp2, long cod2) {
		return new Fornecedor(nome2, rua, bairro2, cidade2, numero, tel2, cnpj2, comp2, cod2);
	}

	private Fornecedor(String nome2, String rua, String bairro2, String cidade2, int numero, long tel2, String cnpj2,
			String comp2, long cod2) {
		this.cod = cod2;
		this.nome = nome2;
		this.tel = tel2;
		this.logra = rua;
		this.num = numero;
		this.bairro = bairro2;
		this.cidade = cidade2;
		this.cnpj = cnpj2;
		if (comp2.equals("null"))
			this.comp = null;
		else
			this.comp = comp2;
	}
}

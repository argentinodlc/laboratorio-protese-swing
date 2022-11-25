package sistema.classes;

public class Dentista extends Cliente {
	private String cro;
	
	private Dentista (String nome, String rua, String bairro, String cidade, long numero, long telefone, String cro, String comp) {
		super(nome, rua, bairro, cidade, numero, telefone, comp);
		this.cro = cro;
	}
	private Dentista (String nome, String rua, String bairro, String cidade, long numero, long telefone, String cro, String comp, long cod) {
		super(nome, rua, bairro, cidade, numero, telefone, comp, cod);
		this.cro=cro;
	}
	public static Dentista newInstance(String nome, String rua, String bairro, String cidade, long numero, long telefone, String cro, String comp, long cod) {
		return new Dentista(nome, rua, bairro, cidade, numero, telefone, cro, comp, cod);
	}
	public static Dentista newInstance(String nome, String rua, String bairro, String cidade, long numero, long telefone, String cro, String comp) {
		if (nome != null && nome.length()>0 && rua != null && rua.length() > 0 && bairro != null && bairro.length() > 0
				&& cidade != null && cidade.length() > 0 && numero > 0 && telefone > 0 && cro != null && cro.length() > 0) {
					return new Dentista(nome, rua, bairro, cidade, numero, telefone, cro, comp);
			} else {
				return null;
			}
	}
	
	public String getCro() {
		return cro;
	}
	
	public void setCro(String cro) {
		this.cro=cro;
	}

}

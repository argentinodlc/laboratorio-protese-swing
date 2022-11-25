package sistema.classes;

public class Paciente {
	private static long geraCod = 0;
	private long cod;
	private String nome;
	
	public static Paciente newInstance(String nome) {
		if (nome != null && nome.length() > 0)
			return new Paciente(nome);
		else
			return null;
	}
	private Paciente(String nome) {
		this.nome = nome;
		this.geraCod = geraCod+1;
		this.cod = geraCod;
	}
	
	public Paciente(String nome2, long cod2) {
		this.nome = nome2;
		this.cod = cod2;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public static long getGeraCod() {
		return geraCod;
	}
	public static void setGeraCod(long cod) {
		geraCod = cod;
	}
	public long getCod() {
		return cod;
	}
	public String getNome() {
		return nome;
	}
	public static Paciente newInstance(String nome2, long cod2) {
		return new Paciente(nome2, cod2);
	}
	
}

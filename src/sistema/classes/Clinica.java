package sistema.classes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Clinica extends Cliente {
	private String cnpj;
	private ArrayList<Dentista> dentistas;
	
	private Clinica (String nome, String rua, String bairro, String cidade, long numero, long telefone, String cnpj, String comp) {
		super(nome, rua, bairro, cidade, numero, telefone, comp);
		this.cnpj = cnpj;
		this.dentistas = new ArrayList<Dentista>();
	}
	
	public Clinica(String nome, String rua, String bairro, String cidade, long numero, long telefone, String cnpj,
			String comp, long cod) {
		super(nome, rua, bairro, cidade, numero, telefone, comp, cod);
		this.cnpj = cnpj;
		this.dentistas = new ArrayList<Dentista>();
	}

	public static Clinica newInstance(String nome, String rua, String bairro, String cidade, long numero, long telefone, String cnpj, String comp) {
		if (nome != null && nome.length()>0 && rua != null && rua.length() > 0 && bairro != null && bairro.length() > 0
				&& cidade != null && cidade.length() > 0 && numero > 0 && telefone > 0) {
					return new Clinica(nome, rua, bairro, cidade, numero, telefone, cnpj, comp);
			} else {
				return null;
			}
	}
	
	public String getCNPJ() {
		return cnpj;
	}
	
	public void setCNPJ(String cnpj) {
		this.cnpj = cnpj;
	}

	public String imprimeCNPJ() {
		String CNPJ = this.cnpj;
		    return(CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "." +
		      CNPJ.substring(5, 8) + "." + CNPJ.substring(8, 12) + "-" +
		      CNPJ.substring(12, 14));
	}
	
	public ArrayList<Dentista> getDentistasDaClinica() {
		return dentistas;
	}
	
	public boolean addDentistaNaClinica(Dentista d) {
		if (dentistas.size()>0) {
			for (int i = 0; i < dentistas.size(); i++) {
				if (d.getCod() == dentistas.get(i).getCod())
					return false;
			}
		}
		dentistas.add(d);
		return true;
	}
	
	public boolean removeDentistaDaClinica(long cod) {
		if (dentistas != null) {
			for (int i = 0; i < dentistas.size(); i++) {
				if ((dentistas.get(i)).getCod() == cod) {
					dentistas.remove(i);
					return true;
				}
			}
		}
		return false;
	}

	public static Clinica newInstance(String nome, String rua, String bairro, String cidade, long numero, long telefone,
			String cnpj, String comp, long cod) {
		return new Clinica(nome, rua, bairro, cidade, numero, telefone, cnpj, comp, cod);
	}
}

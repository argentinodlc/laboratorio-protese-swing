package ui.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sistema.classes.Clinica;

public class ClinicaTableModel extends AbstractTableModel {
	 private List<Clinica> linhas = new ArrayList<>();
	    private String[] colunas = {"NOME","CNPJ", "TELEFONE","ENDEREÇO"};
		
	    public ClinicaTableModel(List<Clinica> c) {
			super();
			if (c == null) 
				this.linhas = new ArrayList<Clinica>();
			else 
				this.linhas = new ArrayList<Clinica>(c);
		}
	    
	    public Clinica getClin (int linha) {
	    	return linhas.get(linha);
	    }
		
	    @Override
	    public String getColumnName(int column) {
	        return colunas[column];
	    }
	    
	    @Override
	    public int getRowCount() {
	        return linhas.size();
	    }

	    @Override
	    public int getColumnCount() {
	        return colunas.length;
	    }

	    @Override
	    public Object getValueAt(int linha, int coluna) {
	        
	        switch(coluna){
	            case 0:
	                return linhas.get(linha).getNome();
	            case 1:
	                return linhas.get(linha).imprimeCNPJ();
	            case 2: 
	                return linhas.get(linha).getTelefone();
	            case 3:
	            	String comp = linhas.get(linha).getComp();
	            	if (comp!=null)
	            		return linhas.get(linha).getRua() + ", "+ linhas.get(linha).getNumero()+" - "+comp+", "+linhas.get(linha).getBairro()+", "+linhas.get(linha).getCidade();
	            	else 
	            		return linhas.get(linha).getRua() + ", "+ linhas.get(linha).getNumero()+", "+linhas.get(linha).getBairro()+", "+linhas.get(linha).getCidade();
	            default:
	            	throw new IndexOutOfBoundsException(
							"Ultrapassou os limites das colunas");
	        }
	        
	    }
	    
	    public void updateTable(ArrayList<Clinica> c){
	    	linhas = c;
	    	this.fireTableDataChanged();
	    }
}

package ui.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sistema.classes.Fornecedor;

public class FornTableModel extends AbstractTableModel {
	 private List<Fornecedor> linhas = new ArrayList<>();
	    private String[] colunas = {"NOME","CNPJ", "TELEFONE","ENDEREÇO"};
		
	    public FornTableModel(List<Fornecedor> f) {
			super();
			if (f == null) 
				this.linhas = new ArrayList<Fornecedor>();
			else 
				this.linhas = new ArrayList<Fornecedor>(f);
		}
	    
	    public Fornecedor getForn (int linha) {
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
	                return String.valueOf(linhas.get(linha).getTel());
	            case 3:
	            	String comp = linhas.get(linha).getComp();
	            	if (comp!=null)
	            		return linhas.get(linha).getLogra() + ", "+ linhas.get(linha).getNum()+" - "+comp+", "+linhas.get(linha).getBairro()+", "+linhas.get(linha).getCidade();
	            	else return linhas.get(linha).getLogra() + ", "+ linhas.get(linha).getNum()+", "+linhas.get(linha).getBairro()+", "+linhas.get(linha).getCidade();
	            default:
	            	throw new IndexOutOfBoundsException(
							"Ultrapassou os limites das colunas");
	        }
	        
	    }
	    
	    public void updateTable(ArrayList<Fornecedor> f){
	    	linhas = f;
	    	this.fireTableDataChanged();
	    }
}

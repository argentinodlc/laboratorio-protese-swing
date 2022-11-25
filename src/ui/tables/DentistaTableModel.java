package ui.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sistema.classes.Dentista;


public class DentistaTableModel extends AbstractTableModel{

    private List<Dentista> linhas = new ArrayList<>();
    private String[] colunas = {"NOME","CRO", "TELEFONE","ENDEREÇO"};
	
    public DentistaTableModel(List<Dentista> d) {
		super();
		if (d == null) 
			this.linhas = new ArrayList<Dentista>();
		else 
			this.linhas = new ArrayList<Dentista>(d);
	}
    
    public Dentista getDent (int linha) {
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
                return linhas.get(linha).getCro();
            case 2: 
                return linhas.get(linha).getTelefone();
            case 3:
            	String comp = linhas.get(linha).getComp();
            	if (comp!=null)
            		return linhas.get(linha).getRua() + ", "+ linhas.get(linha).getNumero()+" - "+comp+", "+linhas.get(linha).getBairro()+", "+linhas.get(linha).getCidade();
            	else return linhas.get(linha).getRua() + ", "+ linhas.get(linha).getNumero()+", "+linhas.get(linha).getBairro()+", "+linhas.get(linha).getCidade();
            default:
            	throw new IndexOutOfBoundsException(
						"Ultrapassou os limites das colunas");
        }
        
    }
    
    public void updateTable(ArrayList<Dentista> list){
    	linhas = list;
    	this.fireTableDataChanged();
    }
}
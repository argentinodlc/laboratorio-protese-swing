package ui.tables;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import sistema.classes.TipoServico;


public class TipoTableModel extends AbstractTableModel{

    private ArrayList<TipoServico> linhas = new ArrayList<>();
    private String[] colunas = {"NOME", "PREÇO"};
	
    public TipoTableModel(ArrayList<TipoServico> t) {
		super();
		if (t == null) 
			this.linhas = new ArrayList<TipoServico>();
		else 
			this.linhas = new ArrayList<TipoServico>(t);
	}
    
    public TipoServico getTipoServico (int linha) {
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
            	DecimalFormat df = new DecimalFormat("0.##");
            	return "R$ "+df.format(linhas.get(linha).getPreco());
            default:
            	throw new IndexOutOfBoundsException("Ultrapassou os limites das colunas");
        }
    }
    
    public void updateTable(ArrayList<TipoServico> list){
    	linhas = list;
    	this.fireTableDataChanged();
    }
}

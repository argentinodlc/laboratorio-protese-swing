package ui.tables;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import sistema.classes.Despesa;


public class DespesaTableModel extends AbstractTableModel{

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private ArrayList<Despesa> linhas;
    private String[] colunas = {"NOME", "DATA",  "VALOR"};
	
    public DespesaTableModel(ArrayList<Despesa> d) {
		super();
		if (d == null) 
			this.linhas = new ArrayList<Despesa>();
		else 
			this.linhas = new ArrayList<Despesa>(d);
	}
    
    public Despesa getDespesa (int linha) {
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
            	return sdf.format(linhas.get(linha).getData());
            case 2:
            	DecimalFormat df = new DecimalFormat("0.##");
               	return df.format(linhas.get(linha).getValor());
            default:
            	throw new IndexOutOfBoundsException("Ultrapassou os limites das colunas");
        }
    }
    
    public void updateTable(ArrayList<Despesa> list){
    	linhas = list;
    	this.fireTableDataChanged();
    }
}

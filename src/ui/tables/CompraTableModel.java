package ui.tables;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import sistema.classes.Compra;


public class CompraTableModel extends AbstractTableModel{

    private ArrayList<Compra> linhas;
    private String[] colunas = {"DESCRIÇÃO", "VALOR", "DATA", "FORNECEDOR"};
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public CompraTableModel(ArrayList<Compra> c) {
		super();
		if (c == null) 
			this.linhas = new ArrayList<Compra>();
		else 
			this.linhas = new ArrayList<Compra>(c);
	}
    
    public Compra getCompra (int linha) {
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
                return linhas.get(linha).getDescricao();
            case 1:
            	DecimalFormat df = new DecimalFormat("0.##");
            	return "R$ "+String.valueOf(df.format(linhas.get(linha).getValor()));
            case 2:
            	return sdf.format(linhas.get(linha).getData());
            case 3:
            	return linhas.get(linha).getFornecedor().getNome();
            default:
            	throw new IndexOutOfBoundsException("Ultrapassou os limites das colunas");
        }
    }
    
    public void updateTable(ArrayList<Compra> list){
    	linhas = list;
    	this.fireTableDataChanged();
    }
}

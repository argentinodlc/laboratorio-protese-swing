package ui.tables;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import sistema.classes.Produto;


public class ProdutoTableModel extends AbstractTableModel{

    private ArrayList<Produto> linhas;
    private String[] colunas = {"NOME", "VALOR  MÉDIO"};
	
    public ProdutoTableModel(ArrayList<Produto> P) {
		super();
		if (P == null) 
			this.linhas = new ArrayList<Produto>();
		else 
			this.linhas = new ArrayList<Produto>(P);
	}
    
    public Produto getProduto (int linha) {
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
            	return df.format(linhas.get(linha).getValorMedio());
            default:
            	throw new IndexOutOfBoundsException("Ultrapassou os limites das colunas");
        }
    }
    
    public void updateTable(ArrayList<Produto> list){
    	linhas = list;
    	this.fireTableDataChanged();
    }
}

package ui.tables;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import sistema.classes.TipoServico;;


public class ProdutoCompraTable extends AbstractTableModel{
	
	private int linhas;
	ArrayList<String> produtos;
	ArrayList<Double> valorUni;
	ArrayList<Integer> qtd;
    private String[] colunas = {"PRODUTO", "UNID.", "QTD", "TOTAL"};
	
    public ProdutoCompraTable() {
		super();
		produtos = new ArrayList<String>();
		valorUni = new ArrayList<Double>();
		qtd = new ArrayList<Integer>();
		linhas = 0;
    }
	
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    @Override
    public int getRowCount() {
        return linhas;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        
        switch(coluna){
            case 0:
                return produtos.get(linha);
            case 1:
            	DecimalFormat df = new DecimalFormat("0.##");
            	return String.valueOf(df.format(valorUni.get(linha)));
            case 2:
            	return String.valueOf(qtd.get(linha));
            case 3:
            	DecimalFormat df1 = new DecimalFormat("0.##");
            	return String.valueOf(df1.format(qtd.get(linha)*valorUni.get(linha)));
            default:
            	throw new IndexOutOfBoundsException("Ultrapassou os limites das colunas");
        }
    }
    
    public void addRow(String produto, double valorUni, int qtd){
    	produtos.add(linhas, produto);
    	this.valorUni.add(linhas, valorUni);
    	this.qtd.add(linhas, qtd);
    	linhas++;
    	this.fireTableDataChanged();
    }
    
    public void removeRow(int arg0) {
    	produtos.remove(arg0);
    	valorUni.remove(arg0);
    	qtd.remove(arg0);
    	linhas--;
    	this.fireTableDataChanged();
    }
}

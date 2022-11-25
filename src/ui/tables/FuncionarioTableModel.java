package ui.tables;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sistema.classes.Funcionario;


public class FuncionarioTableModel extends AbstractTableModel{

    private List<Funcionario> linhas = new ArrayList<>();
    private String[] colunas = {"NOME","CPF", "COMISSÃO"};
    
    public FuncionarioTableModel(List<Funcionario> f) {
		super();
		if (f == null) 
			this.linhas = new ArrayList<Funcionario>();
		else 
			this.linhas = new ArrayList<Funcionario>(f);
	}
    
    public Funcionario getFunc (int linha) {
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
                return linhas.get(linha).getCpf();
            case 2: 
            	DecimalFormat df = new DecimalFormat("0.##");
                return df.format(linhas.get(linha).getComissao())+"%";
            default:
            	throw new IndexOutOfBoundsException(
						"Ultrapassou os limites das colunas");
        }
        
    }
    
    public void updateTable(ArrayList<Funcionario> list){
    	linhas = list;
    	this.fireTableDataChanged();
    }
}
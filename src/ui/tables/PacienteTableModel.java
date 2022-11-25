package ui.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sistema.classes.Paciente;


public class PacienteTableModel extends AbstractTableModel{

    private List<Paciente> linhas = new ArrayList<>();
    private String[] colunas = {"NOME"};
	
    public PacienteTableModel(List<Paciente> p) {
		super();
		if (p == null) 
			this.linhas = new ArrayList<Paciente>();
		else 
			this.linhas = new ArrayList<Paciente>(p);
	}
    
    public Paciente getPaciente (int linha) {
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
            default:
            	throw new IndexOutOfBoundsException("Ultrapassou os limites das colunas");
        }
    }
    
    public void updateTable(ArrayList<Paciente> list){
    	linhas = list;
    	this.fireTableDataChanged();
    }
}

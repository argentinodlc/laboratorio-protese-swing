package ui.tables;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import sistema.classes.Servicos;

public class ServicosTableModel extends AbstractTableModel {

	private ArrayList<Servicos> linhas = new ArrayList<>();
	private String[] colunas = { "SERVIÇO", "DESCRIÇÃO", "SITUAÇÃO", "PAGAMENTO", "VALOR", "ENTRADA", "SAÍDA",
			"CLIENTE", "FUNCIONÁRIO", "PACIENTE" };
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public ServicosTableModel(ArrayList<Servicos> s) {
		super();
		if (s == null)
			this.linhas = new ArrayList<Servicos>();
		else
			this.linhas = new ArrayList<Servicos>(s);
	}

	public Servicos getServicos(int linha) {
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

		switch (coluna) {
		case 0:
			return linhas.get(linha).getTipo().getNome();
		case 1:
			return linhas.get(linha).getDescricao();
		case 2:
			return linhas.get(linha).getSituacao();
		case 3:
			String pagamento;
			if (linhas.get(linha).isPagamento())
				pagamento = "JÁ COBRADO, ";
			else
				pagamento = "NÃO COBRADO, ";
			String pago;
			if (linhas.get(linha).isPago())
				pago = "PAGO";
			else
				pago = "NÃO PAGO";
			return pagamento + pago;
		case 4:
			DecimalFormat df = new DecimalFormat("0.##");
			return "R$ " + df.format(linhas.get(linha).getValorFinal());
		case 5:
			return sdf.format(linhas.get(linha).getEntrada());

		case 6:
			if (linhas.get(linha).getSaida() == null)
				return "Não teve saída";
			else
				return sdf.format(linhas.get(linha).getSaida());
		case 7:
			return linhas.get(linha).getCliente().getNome();

		case 8:
			return linhas.get(linha).getFuncionario().getNome();
		case 9:
			if (linhas.get(linha).getPaciente() == null) {
				return "PACIENTE NÃO INFORMADO";
			} else
				return linhas.get(linha).getPaciente().getNome();
		default:
			throw new IndexOutOfBoundsException("Ultrapassou os limites das colunas");
		}

	}

	public void updateTable(ArrayList<Servicos> list) {
		linhas = list;
		this.fireTableDataChanged();
	}
}
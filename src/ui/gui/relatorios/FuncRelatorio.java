package ui.gui.relatorios;

import sistema.classes.Funcionario;

public class FuncRelatorio {
	private Funcionario f;
	private double comissao;
	private int qtdServicos;
	
	public FuncRelatorio(Funcionario funcionario, double comissao, int qtdServicos) {
		this.f = funcionario;
		this.comissao = comissao;
		this.qtdServicos = qtdServicos;
	}
	
	public Funcionario getF() {
		return f;
	}
	public double getComissao() {
		return comissao;
	}
	public int getQtdServicos() {
		return qtdServicos;
	}
}

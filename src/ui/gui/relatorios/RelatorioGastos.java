package ui.gui.relatorios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import sistema.classes.Compra;
import sistema.classes.Despesa;

public class RelatorioGastos {
	double totalfixas = 0;
	double totaldespesas = 0;
	double totalcompras = 0;
	double totalfunc = 0;
	public void relatorio(ArrayList<Compra> compras, ArrayList<Despesa> despesas, ArrayList<Despesa> fixas, ArrayList<FuncRelatorio> array) {
		Document document = new Document();
		try {
			
			File URL = new File(javax.swing.filechooser.FileSystemView.getFileSystemView().getDefaultDirectory() + "/Relatorios_DentArt");
			if (!URL.exists()) {
				URL.mkdirs();
			}
			String path = URL.toString();
			String arq = "/Relatorio__gastos_"+(new SimpleDateFormat("dd-MM-yyyy").format(new Date()))+"_"+(new SimpleDateFormat("HH-mm-ss").format(new Date()))+".pdf";
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path+arq));
			document.open();
			document.setPageSize(PageSize.A4);
			Image img = Image.getInstance(getClass().getResource("/res/logo.png"));
			img.setAlignment(Element.ALIGN_LEFT);
			img.scaleAbsolute(200, 150);
			document.add(img);
			writer.setPageEvent(new EndPage());
			Font f = new Font(FontFamily.HELVETICA, 15, Font.BOLD);
			if (fixas!=null) {
				document.add(new Paragraph("Despesas fixas", f));
				document.add(new Paragraph("\n"));
				PdfPTable tbFixas = this.criarCabecalhoFixas();
				this.preencherDadosFixas(document, tbFixas, fixas);
				document.add(new Paragraph("\n"));	
			}
			if (despesas!=null) {
				document.add(new Paragraph("Despesas", f));
				document.add(new Paragraph("\n"));
				PdfPTable tbDespesas = this.criarCabecalhoDespesas();
				this.preencherDadosDespesas(document, tbDespesas, despesas);
				document.add(new Paragraph("\n"));	
			}
			if (compras != null) {
				document.add(new Paragraph("Compras", f));
				document.add(new Paragraph("\n"));
				PdfPTable tbCompras = this.criarCabecalhoCompras();
				this.preencherDadosCompras(document, tbCompras, compras);
				document.add(new Paragraph("\n"));	
			}
			if (array != null) {
				document.add(new Paragraph("Funcionários", f));
				document.add(new Paragraph("\n"));
				PdfPTable tbFunc = this.criarCabecalhoFunc();
				this.preencherDadosFunc(document, tbFunc, array);
				document.add(new Paragraph("\n"));	
			}
			PdfPTable table = new PdfPTable(new float[] { 15f, 15f });
			Font f1 = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD);
			Font f2 = FontFactory.getFont(FontFactory.COURIER, 7);
			PdfPCell celulaTotal = new PdfPCell(new Phrase("Total:", f1));
			celulaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
			DecimalFormat df = new DecimalFormat("0.##");
			PdfPCell celulaValor = new PdfPCell(new Phrase("R$ "+df.format(totaldespesas+totalfixas+totalcompras+totalfunc), f2));
			celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celulaTotal);
			table.addCell(celulaValor);
			document.add(table);
			document.close();
			int botao = JOptionPane.YES_NO_OPTION;
	        if (JOptionPane.showConfirmDialog(null,"Deseja abrir o documento?", "Relatório criado com sucesso", botao, JOptionPane.PLAIN_MESSAGE)==JOptionPane.OK_OPTION){
	        	java.awt.Desktop desktop = java.awt.Desktop.getDesktop(); 
				desktop.open(new File(path+arq));
	        }
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}

	}
	
	private void preencherDadosFunc(Document document, PdfPTable table, ArrayList<FuncRelatorio> func)
			throws DocumentException {
		if (document.isOpen()) {
			DecimalFormat df = new DecimalFormat("0.##");
			Font f = FontFactory.getFont(FontFactory.COURIER, 7);
			for (FuncRelatorio funcionario : func) {
				PdfPCell celula1 = new PdfPCell(new Phrase(funcionario.getF().getNome(), f));
				PdfPCell celula2 = new PdfPCell(new Phrase(funcionario.getF().getCpf(), f));
				PdfPCell celula3 = new PdfPCell(new Phrase(String.valueOf(funcionario.getF().getComissao())+"%", f));
				PdfPCell celula4 = new PdfPCell(new Phrase(String.valueOf(funcionario.getQtdServicos()), f));
				PdfPCell celulas = new PdfPCell(new Phrase("R$ "+df.format(funcionario.getF().getSalario()), f));
				PdfPCell celula5 = new PdfPCell(new Phrase("R$ "+df.format(funcionario.getComissao()+funcionario.getF().getSalario()), f));
				table.addCell(celula1);
				table.addCell(celula2);
				table.addCell(celula3);
				table.addCell(celula4);
				table.addCell(celulas);
				table.addCell(celula5);
				totalfunc = totalfunc + funcionario.getComissao() + funcionario.getF().getSalario();
			}
			Font f2 = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD);
			PdfPTable tb = new PdfPTable(new float[] { 15f, 15f });
			tb.addCell( new PdfPCell(new Phrase("Total funcionários:", f2)));
			tb.addCell( new PdfPCell(new Phrase("R$ "+df.format(totalfunc), f)));

			document.add(table);
			document.add(tb);
		}
	}

	private PdfPTable criarCabecalhoFunc() {
		PdfPTable table = new PdfPTable(new float[] { 40f, 15f, 15f, 15f, 15f, 15f});
		Font f = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD);
		PdfPCell celulaFuncionario = new PdfPCell(new Phrase("Funcionário", f));
		celulaFuncionario.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell celulaCPF = new PdfPCell(new Phrase("CPF", f));
		celulaCPF.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell celulaComissao = new PdfPCell(new Phrase("Comissão", f));
		celulaComissao.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell celulaServico = new PdfPCell(new Phrase("Qtd. serviços", f));
		celulaServico.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell celulaSalario = new PdfPCell(new Phrase("Salário", f));
		celulaSalario.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell celulaTotal = new PdfPCell(new Phrase("Valor total recebido", f));
		celulaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celulaFuncionario);
		table.addCell(celulaCPF);
		table.addCell(celulaComissao);
		table.addCell(celulaServico);
		table.addCell(celulaSalario);
		table.addCell(celulaTotal);
		return table;
	}

	private PdfPTable criarCabecalhoDespesas() throws DocumentException {
		PdfPTable table = new PdfPTable(new float[] { 15f, 15f, 15f });
		Font f = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD);
		PdfPCell celulaDescricao = new PdfPCell(new Phrase("Descrição", f));
		celulaDescricao.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell celulaValor = new PdfPCell(new Phrase("Valor", f));
		celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell celulaData = new PdfPCell(new Phrase("Data", f));
		celulaData.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celulaDescricao);
		table.addCell(celulaValor);
		table.addCell(celulaData);
		return table;
	}
	

	private void preencherDadosDespesas(Document document, PdfPTable table, ArrayList<Despesa> despesas)
			throws DocumentException {
		if (document.isOpen()) {
			DecimalFormat df = new DecimalFormat("0.##");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Font f = FontFactory.getFont(FontFactory.COURIER, 7);
			for (Despesa despesa : despesas) {
				
				PdfPCell celula1 = new PdfPCell(new Phrase(despesa.getNome(), f));
				PdfPCell celula2 = new PdfPCell(new Phrase("R$ "+df.format(despesa.getValor()), f));
				PdfPCell celula3 = new PdfPCell(new Phrase(sdf.format(despesa.getData()), f));
				table.addCell(celula1);
				table.addCell(celula2);
				table.addCell(celula3);
				totaldespesas = totaldespesas + despesa.getValor();
			}
			Font f2 = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD);
			PdfPTable tb = new PdfPTable(new float[] { 15f, 15f });
			tb.addCell( new PdfPCell(new Phrase("Total despesas:", f2)));
			tb.addCell( new PdfPCell(new Phrase("R$ "+df.format(totaldespesas), f)));
			document.add(table);
			document.add(tb);
		}
	}
	
	private PdfPTable criarCabecalhoFixas() throws DocumentException {
		PdfPTable table = new PdfPTable(new float[] { 15f, 15f, });
		Font f = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD);
		PdfPCell celulaDescricao = new PdfPCell(new Phrase("Descrição", f));
		celulaDescricao.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell celulaValor = new PdfPCell(new Phrase("Valor", f));
		celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celulaDescricao);
		table.addCell(celulaValor);
		return table;
	}
	
	private void preencherDadosFixas(Document document, PdfPTable table, ArrayList<Despesa> fixas)
			throws DocumentException {
		if (document.isOpen()) {
			DecimalFormat df = new DecimalFormat("0.##");
			Font f = FontFactory.getFont(FontFactory.COURIER, 7);
			for (Despesa fixa : fixas) {
				PdfPCell celula1 = new PdfPCell(new Phrase(fixa.getNome(), f));
				PdfPCell celula2 = new PdfPCell(new Phrase("R$ "+String.valueOf(fixa.getValor()), f));
				table.addCell(celula1);
				table.addCell(celula2);
				totalfixas=totalfixas + fixa.getValor();
			}
			Font f2 = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD);
			table.addCell( new PdfPCell(new Phrase("Total despesas fixas:", f2)));
			table.addCell( new PdfPCell(new Phrase("R$ "+df.format(totalfixas), f)));
			document.add(table);
		}
	}
	
	private PdfPTable criarCabecalhoCompras() throws DocumentException {
		PdfPTable table = new PdfPTable(new float[] { 20f, 15f, 15f, 15f });
		Font f = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD);
		PdfPCell celulaDescricao = new PdfPCell(new Phrase("Descrição", f));
		celulaDescricao.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell celulaValor = new PdfPCell(new Phrase("Valor", f));
		celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell celulaData = new PdfPCell(new Phrase("Data", f));
		celulaData.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell celulaFornecedor = new PdfPCell(new Phrase("Fornecedor", f));
		celulaFornecedor.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celulaDescricao);
		table.addCell(celulaValor);
		table.addCell(celulaData);
		table.addCell(celulaFornecedor);
		return table;
	}

	private void preencherDadosCompras(Document document, PdfPTable table, ArrayList<Compra> compras)
			throws DocumentException {
		if (document.isOpen()) {
			DecimalFormat df = new DecimalFormat("0.##");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Font f = FontFactory.getFont(FontFactory.COURIER, 7);
			for (Compra compra : compras) {
				
				PdfPCell celula1 = new PdfPCell(new Phrase(compra.getDescricao(), f));
				PdfPCell celula2 = new PdfPCell(new Phrase("R$ "+String.valueOf(compra.getValor()), f));
				PdfPCell celula3 = new PdfPCell(new Phrase(sdf.format(compra.getData()), f));
				PdfPCell celula4 = new PdfPCell(new Phrase(compra.getFornecedor().getNome(), f));
				table.addCell(celula1);
				table.addCell(celula2);
				table.addCell(celula3);
				table.addCell(celula4);
				totalcompras = totalcompras + compra.getValor();
			}
			Font f2 = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD);
			PdfPTable tb = new PdfPTable(new float[] { 15f, 15f });
			tb.addCell( new PdfPCell(new Phrase("Total compras:", f2)));
			tb.addCell( new PdfPCell(new Phrase("R$ "+df.format(totalcompras), f)));
			document.add(table);
			document.add(tb);
		}
	}
}

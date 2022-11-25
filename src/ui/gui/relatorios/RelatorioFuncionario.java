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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class RelatorioFuncionario {
	public void relatorio(ArrayList<FuncRelatorio> func) {
		Document document = new Document();
		try {
			File URL = new File(javax.swing.filechooser.FileSystemView.getFileSystemView().getDefaultDirectory() + "/Relatorios_DentArt");
			if (!URL.exists()) {
				URL.mkdirs();
			}
			String path = URL.toString();
			String arq = "/Relatorio__Funcionarios_"+(new SimpleDateFormat("dd-MM-yyyy").format(new Date()))+"_"+(new SimpleDateFormat("HH-mm-ss").format(new Date()))+".pdf";
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path+arq));
			document.open();
			document.setPageSize(PageSize.A4);
			Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
			Image img = Image.getInstance(getClass().getResource("/res/logo.png"));
			img.setAlignment(Element.ALIGN_LEFT);
			img.scaleAbsolute(200, 150);
			document.add(img);
			writer.setPageEvent(new EndPage());
			PdfPTable table = this.criarCabecalho();
			this.preencherDados(document, table, func);
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
	
	private PdfPTable criarCabecalho() throws DocumentException {
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

	private void preencherDados(Document document, PdfPTable table, ArrayList<FuncRelatorio> func)
			throws DocumentException {
		if (document.isOpen()) {
			for (FuncRelatorio funcionario : func) {
				DecimalFormat df = new DecimalFormat("0.##");
				Font f = FontFactory.getFont(FontFactory.COURIER, 7);
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
			}
			document.add(table);
		}
	}
}

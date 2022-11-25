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

import sistema.classes.Paciente;
import sistema.classes.Servicos;

public class RelatorioExtrato {
	private double total = 0;
	public void relatorio(ArrayList<Servicos> servicos, int modo) {
		Document document = new Document();
		try {
			File URL = new File(javax.swing.filechooser.FileSystemView.getFileSystemView().getDefaultDirectory() + "/Relatorios_DentArt");
			if (!URL.exists()) {
				URL.mkdirs();
			}
			String path = URL.toString();
			String arq = "/Extrato__Interno_"+(new SimpleDateFormat("dd-MM-yyyy").format(new Date()))+"_"+(new SimpleDateFormat("HH-mm-ss").format(new Date()))+".pdf";
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path+arq));
			document.open();
			document.setPageSize(PageSize.A4);
			Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
			Image img = Image.getInstance(getClass().getResource("/res/logo.png"));
			img.setAlignment(Element.ALIGN_LEFT);
			img.scaleAbsolute(200, 150);
			document.add(img);
			writer.setPageEvent(new EndPage());
			PdfPTable table = this.criarCabecalho(modo);
			this.preencherDados(document, table, servicos, modo);
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
	
	private PdfPTable criarCabecalho(int modo) throws DocumentException {
		PdfPTable table = new PdfPTable(new float[] { 15f, 15f, 15f, 15f, 10f, 10f});
		Font f = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD);
		PdfPCell celulaClinica = new PdfPCell(new Phrase("Cliente", f));
		celulaClinica.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell celulaPaciente = new PdfPCell(new Phrase("Paciente", f));
		celulaPaciente.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell celulaServico = new PdfPCell(new Phrase("Serviço", f));
		celulaServico.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell celulaData;
		if (modo == 2) {
			 celulaData = new PdfPCell(new Phrase("Data de entrada", f));
		} else {
			 celulaData = new PdfPCell(new Phrase("Data de saída", f));
		}
		celulaData.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell celulaTotal = new PdfPCell(new Phrase("Valor", f));
		celulaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell celulaSituacao = new PdfPCell(new Phrase("Situação", f));
		celulaServico.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celulaClinica);
		table.addCell(celulaPaciente);
		table.addCell(celulaServico);
		table.addCell(celulaData);
		table.addCell(celulaTotal);
		table.addCell(celulaSituacao);
		return table;
	}

	private void preencherDados(Document document, PdfPTable table, ArrayList<Servicos> servicos, int modo)
			throws DocumentException {
		if (document.isOpen()) {
			DecimalFormat df = new DecimalFormat("0.##");
			for (Servicos servico : servicos) {
				Font f = FontFactory.getFont(FontFactory.COURIER, 7);
				PdfPCell celula1 = new PdfPCell(new Phrase(servico.getCliente().getNome(), f));
				Paciente p = servico.getPaciente();
				PdfPCell celula2;
				if (p != null)
					celula2 = new PdfPCell(new Phrase(servico.getPaciente().getNome(), f));
				else
					celula2 = new PdfPCell(new Phrase("Sem paciente informado", f));
				PdfPCell celula3;
				if (servico.getDescricao()!=null && servico.getDescricao().length()>0)
					celula3 = new PdfPCell(new Phrase(servico.getTipo().getNome()+", "+servico.getDescricao(), f));
				else 
					celula3 = new PdfPCell(new Phrase(servico.getTipo().getNome(), f));
				PdfPCell celuladata;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				if (modo == 2) {
					celuladata = new PdfPCell(new Phrase(sdf.format(servico.getEntrada()), f));
				} else {
					celuladata = new PdfPCell(new Phrase(sdf.format(servico.getSaida()), f));
				}
				PdfPCell celula5 = new PdfPCell(new Phrase("R$ "+df.format(servico.getValorFinal()), f));
				PdfPCell celula6 = new PdfPCell(new Phrase(servico.getSituacao(), f));
				table.addCell(celula1);
				table.addCell(celula2);
				table.addCell(celula3);
				table.addCell(celuladata);
				table.addCell(celula5);
				table.addCell(celula6);
			}
			document.add(table);
		}
	}
}

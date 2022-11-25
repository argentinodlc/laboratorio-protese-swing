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

import sistema.classes.Dentista;
import sistema.classes.Servicos;

public class RelatorioServico {
	public void relatorio(Servicos servicos) {
		Document document = new Document(PageSize.A4.rotate());
		try {
			File URL = new File(javax.swing.filechooser.FileSystemView.getFileSystemView().getDefaultDirectory() + "/Relatorios_DentArt");
			if (!URL.exists()) {
				URL.mkdirs();
			}
			String path = URL.toString();
			String arq = "/Extrato_"+(new SimpleDateFormat("dd-MM-yyyy").format(new Date()))+"_"+(new SimpleDateFormat("HH-mm-ss").format(new Date()))+".pdf";
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path+arq));
			document.open();
			Font f = new Font(FontFamily.HELVETICA, 20, Font.BOLD);
			Image img = Image.getInstance(getClass().getResource("/res/logo.png"));
			img.setAlignment(Element.ALIGN_LEFT);
			img.scaleAbsolute(200, 150);
			document.add(img);
			Paragraph p = new Paragraph(new Phrase("Extrato", f));
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			writer.setPageEvent(new EndPage());
			this.criarTabela(document, servicos);
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
	
	
	private void criarTabela(Document document, Servicos s) throws DocumentException{
		if (document.isOpen()) {
			DecimalFormat df = new DecimalFormat("0.##");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			PdfPTable table = new PdfPTable(new float[] { 25f, 25f });
			PdfPTable tb = new PdfPTable(new float[] {25f, 25f} );
			Font f = FontFactory.getFont(FontFactory.HELVETICA, 15, Font.BOLD);
			Font f2 = FontFactory.getFont(FontFactory.COURIER, 15);
			PdfPCell celulaServico = new PdfPCell(new Phrase("Serviço", f));
			celulaServico.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaValor = new PdfPCell(new Phrase("Valor", f));
			celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celula1 = new PdfPCell(new Phrase(s.getTipo().getNome(), f2));
			celula1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celula2 = new PdfPCell(new Phrase("R$ "+df.format(s.getValorFinal()), f2));
			celula2.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaEntrada = new PdfPCell(new Phrase("Data de entrada", f));
			celulaEntrada.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaSaida = new PdfPCell(new Phrase("Data de saída", f));
			celulaSaida.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celula3 = new PdfPCell(new Phrase(sdf.format(s.getEntrada()), f2));
			celula3.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celula4;
			if (s.getSaida()==null) {
				celula4 = new PdfPCell(new Phrase("Sem previsão", f2));
				celula4.setHorizontalAlignment(Element.ALIGN_CENTER);
			} else {
				celula4 = new PdfPCell(new Phrase(sdf.format(s.getSaida()), f2));
				celula4.setHorizontalAlignment(Element.ALIGN_CENTER);
			}
			PdfPCell celulaCliente;
			if (s.getCliente() instanceof Dentista) {
				celulaCliente = new PdfPCell(new Phrase("Dentista", f));
				celulaCliente.setHorizontalAlignment(Element.ALIGN_CENTER);
			} else {
				celulaCliente = new PdfPCell(new Phrase("Clínica", f));
				celulaCliente.setHorizontalAlignment(Element.ALIGN_CENTER);
			}
			PdfPCell celula5 = new PdfPCell(new Phrase(s.getCliente().getNome(), f2));
			celula5.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPaciente = new PdfPCell(new Phrase("Paciente", f));
			celulaPaciente.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celula6; 
			if (s.getPaciente()==null) {
				celula6 = new PdfPCell(new Phrase("Sem paciente informado", f2));
				celula6.setHorizontalAlignment(Element.ALIGN_CENTER);
			} else {
				celula6 = new PdfPCell(new Phrase(s.getPaciente().getNome(), f2));
				celula6.setHorizontalAlignment(Element.ALIGN_CENTER);
			}
			table.addCell(celulaCliente);
			table.addCell(celulaPaciente);
			table.addCell(celula5);
			table.addCell(celula6);
			tb.addCell(celulaServico);
			tb.addCell(celulaValor);
			tb.addCell(celula1);
			tb.addCell(celula2);
			tb.addCell(celulaEntrada);
			tb.addCell(celulaSaida);
			tb.addCell(celula3);
			tb.addCell(celula4);
			document.add(new Paragraph("\n"));
			document.add(table);
			document.add(new Paragraph("\n"));
			document.add(tb);
		}
	}
}

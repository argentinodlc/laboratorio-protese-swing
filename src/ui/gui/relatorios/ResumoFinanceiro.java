package ui.gui.relatorios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class ResumoFinanceiro {

	public void relatorio(JLabel lblResumo, String pPeriodo, String pServicos, String pDespesa, String pFixo,
			String pCompra, String pLucro) {
		// criação do documento
		Document document = new Document();
		try {
			File URL = new File(javax.swing.filechooser.FileSystemView.getFileSystemView().getDefaultDirectory() + "/Relatorios_DentArt");
			if (!URL.exists()) {
				URL.mkdirs();
			}
			String path = URL.toString();
			String arq = "/Resumo_Financeiro_"+(new SimpleDateFormat("dd-MM-yyyy").format(new Date()))+"_"+(new SimpleDateFormat("HH-mm-ss").format(new Date()))+".pdf";
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path+arq));
			document.open();
			document.setPageSize(PageSize.A4);
			Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
			Image img = Image.getInstance(getClass().getResource("/res/logo.png"));
			img.setAlignment(Element.ALIGN_LEFT);
			img.scaleAbsolute(200, 150);
			document.add(img);
			if (pPeriodo != null) {
				Paragraph p = new Paragraph("Resumo financeiro\n" + pPeriodo, FontFactory.getFont(FontFactory.HELVETICA, 17f));
				p.setAlignment(Element.ALIGN_CENTER);
				document.add(p);
				document.add(new Paragraph("\n"));
			}
			if (pServicos != null) {
				document.add(new Paragraph(pServicos));
				document.add(new Paragraph("\n"));
			}
			if (pDespesa != null) {
				document.add(new Paragraph(pDespesa));
				document.add(new Paragraph("\n"));
			}
			if (pFixo != null) {
				document.add(new Paragraph(pFixo));
				document.add(new Paragraph("\n"));
			}
			if (pCompra != null) {
				document.add(new Paragraph(pCompra));
				document.add(new Paragraph("\n"));
			}
			if (pLucro != null)
				document.add(new Paragraph(pLucro));
			writer.setPageEvent(new EndPage());
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

}

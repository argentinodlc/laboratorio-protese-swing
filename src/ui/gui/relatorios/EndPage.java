package ui.gui.relatorios;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class EndPage extends PdfPageEventHelper {

	public void onEndPage(PdfWriter writer, Document document) {

		try {

			/*
			 * Image jpg = Image.getInstance("logo.jpg"); jpg.setAlignment(Image.RIGHT);
			 * jpg.scaleAbsolute(100, 30);
			 */
			Rectangle page = document.getPageSize();
			PdfPTable head = new PdfPTable(1);

			PdfPCell cell1 = new PdfPCell(new Phrase("Emitido em "+new SimpleDateFormat("dd/MM/yy").format(new Date()) +" às " + (new SimpleDateFormat("HH:mm:ss").format(new Date())),
					FontFactory.getFont(FontFactory.HELVETICA, 10f)));
			cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell1.setBorder(0);

			for (int k = 1; k <= 1; ++k)
				head.addCell(cell1);
			// head.addCell(new Phrase("Pueblo Nativo S.A. de C.V",
			// FontFactory.getFont(FontFactory.HELVETICA, 16f)));

			head.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
			head.writeSelectedRows(0, -1, document.leftMargin(),
					page.getHeight() - document.topMargin() + head.getTotalHeight(), writer.getDirectContent());


		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}

}

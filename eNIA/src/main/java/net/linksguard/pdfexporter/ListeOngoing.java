package net.linksguard.pdfexporter;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import net.linksguard.entities.Fiche;

public class ListeOngoing {
   private List<Fiche> listFiches;

public ListeOngoing(List<Fiche> listFiches) {
	super();
	this.listFiches = listFiches;
}
   
   private void writeTableHeader(PdfPTable table) {
	   PdfPCell cell = new PdfPCell();
	   
	   cell.setBackgroundColor(Color.GREEN);
	   cell.setPadding(5);
	   Font font = FontFactory.getFont(FontFactory.HELVETICA);
	   font.setColor(Color.WHITE);
	   cell.setPhrase(new Phrase("Fiche ID",font));
	   
	   table.addCell(cell);
	   
cell.setPhrase(new Phrase("Titre",font));
	   
	   table.addCell(cell);
	   
cell.setPhrase(new Phrase("Date",font));
	   
	   table.addCell(cell);
   }
   
   private void writeTableData(PdfPTable table) {
	 for (Fiche fiche : listFiches) {
		 table.addCell(String.valueOf(fiche.getId()));
		 table.addCell(fiche.getTitre());
		 table.addCell(String.valueOf(fiche.getCreationDate()));
	}  
   }
   
   public void export(HttpServletResponse response) throws DocumentException, IOException {
	   Document document = new Document(PageSize.A4);
	   PdfWriter.getInstance(document, response.getOutputStream());
	   
	   document.open();
	   
	   Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	   font.setColor(Color.BLACK);
	   font.setSize(18);
	   Paragraph title = new Paragraph("Liste des incidents en cours ...",font);
	   title.setAlignment(Paragraph.ALIGN_CENTER);
	   
	   document.add(new Paragraph(title));
	   
	   PdfPTable table = new PdfPTable(3);
	   table.setWidthPercentage(100);
	   table.setSpacingBefore(15);
	   table.setWidths(new float[] {1.5f, 3.5f, 3.0f});
	   
	   
	   writeTableHeader(table);
	   writeTableData(table);
	   document.add(table);
	   document.close();
   }
   
   
}

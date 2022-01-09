package net.linksguard.excelexporter;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.linksguard.entities.Fiche;

public class FicheExporter {
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	private List<Fiche> listFiches;
	
	
 

public FicheExporter(List<Fiche> listFiches) {
		super();
		this.listFiches = listFiches;
		
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Fiches");
	}

private void writeHeaderRow() {
	
	Row row = sheet.createRow(0);
	
	CellStyle style = workbook.createCellStyle();
	XSSFFont font = workbook.createFont();
	font.setBold(true);
	font.setFontHeight(16);
	style.setFont(font);
	Cell cell = row.createCell(0);
	cell.setCellValue("fiche ID");
	cell.setCellStyle(style);
	
	  cell = row.createCell(1);
	cell.setCellValue("Titre");
	cell.setCellStyle(style);
	  cell = row.createCell(2);
		cell.setCellValue("Date");
		cell.setCellStyle(style);
}


private void writeDataRows() {
	
	int  rowCount = 1;
	CellStyle style = workbook.createCellStyle();
	XSSFFont font = workbook.createFont();
 
	font.setFontHeight(14);
	style.setFont(font);
	
	for (Fiche fiche : listFiches) {
		
		Row row = sheet.createRow(rowCount++);
		Cell cell = row.createCell(0);
		cell.setCellValue(fiche.getId());
		sheet.autoSizeColumn(0);
		cell.setCellStyle(style);
		
		cell = row.createCell(1);
		cell.setCellValue(fiche.getTitre());
		sheet.autoSizeColumn(1);
		cell.setCellStyle(style);
		
		cell = row.createCell(2);
		cell.setCellValue(fiche.getCreationDate().toString());
		sheet.autoSizeColumn(2);
		cell.setCellStyle(style);
		
	}
	
}


public void export(HttpServletResponse response) throws IOException {
	writeHeaderRow() ;
	writeDataRows();
	
	ServletOutputStream outPutStream = response.getOutputStream();
	workbook.write(outPutStream);
	workbook.close();
	outPutStream.close();
}


}

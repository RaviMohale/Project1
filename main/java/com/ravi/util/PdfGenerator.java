package com.ravi.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.ravi.entity.CitizenPlan;
import com.ravi.repo.CitizenPlanRepo;

@Component
public class PdfGenerator {
	
	@Autowired
	private CitizenPlanRepo planRepo;
	
	public void generate(HttpServletResponse response,List<CitizenPlan> plans,File f)throws Exception {
		
		Document document  = new Document(PageSize.A4);
		 PdfWriter.getInstance(document, response.getOutputStream());
		 PdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();
		
		// Creating font
				// Setting font style and size
				Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
				fontTiltle.setSize(20);
		Paragraph p = new Paragraph("Citizen plan info",fontTiltle);
		
		// Aligning the paragraph in document
		p.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(p);
		//document.close();
		
		PdfPTable table = new PdfPTable(6);
		
		// Setting width of table, its columns and spacing
				table.setWidthPercentage(100f);
				//table.setWidths(new int[] { 6, 6, 6 });
				table.setSpacingBefore(5);
		

		
		table.addCell("Id");
		table.addCell("Citizen Name");
		table.addCell("Plan Name");
		table.addCell("Plane Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		
		
		for(CitizenPlan plan :plans) {
			
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			table.addCell(plan.getPlanStartDate()+"");
			table.addCell(plan.getPlanEndDate()+"");
			
			
			
		}
		
		document.add(table);
		document.close();
		
	}

}

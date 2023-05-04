package com.ravi.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ravi.entity.CitizenPlan;
import com.ravi.repo.CitizenPlanRepo;
@Component
public class ExcelGenerator {
	
	@Autowired
	private CitizenPlanRepo planRepo;
	
	public void generate(HttpServletResponse response,List<CitizenPlan> records,File file)throws Exception {
		
		//Workbook workbook = new XSSFWorkbook(); then extension is .xlsx
				Workbook workbook = new HSSFWorkbook();
					Sheet sheet = workbook.createSheet("Insurance Plan");
					Row headerRow = sheet.createRow(0);
					headerRow.createCell(0).setCellValue("Id");
					headerRow.createCell(1).setCellValue("Citizen Name");
					headerRow.createCell(2).setCellValue("Plan Name");
					headerRow.createCell(3).setCellValue("Plan Status");
					headerRow.createCell(4).setCellValue("Plan Start Date");
					headerRow.createCell(5).setCellValue("Plan End Date");
					headerRow.createCell(6).setCellValue("Benefit Amt");
					
				
					
					 int dataRowIndex =1;
					for(CitizenPlan plan : records) {
						 Row datarow = sheet.createRow(dataRowIndex);
						 datarow.createCell(0).setCellValue(plan.getCitizenId());
						 datarow.createCell(1).setCellValue(plan.getCitizenName());
						 datarow.createCell(2).setCellValue(plan.getPlanName());
						 datarow.createCell(3).setCellValue(plan.getPlanStatus());
						 if(null != plan.getPlanStartDate() ) {
							 datarow.createCell(4).setCellValue(plan.getPlanStartDate()+"");	 
						 }else {
							 datarow.createCell(4).setCellValue("N/A");
						 }
						 if(null != plan.getPlanEndDate() ) {
							 datarow.createCell(5).setCellValue(plan.getPlanEndDate()+"");
						 }else {
							 datarow.createCell(5).setCellValue("N/A");
						 }
						 
						 
						 if(null != plan.getBenefitAmt()) {
							 datarow.createCell(6).setCellValue(plan.getBenefitAmt());
						 }else {
							 datarow.createCell(6).setCellValue("N/A");
						 }
						 
						 dataRowIndex++;
					}
					
					
					 // if you want to store file on server folder the FileOutputStream is required
					  FileOutputStream fos = new FileOutputStream(file);
					  workbook.write(fos);
					  fos.close();
					  //workbook.close();
					 
				
					   // for sending response to browser the OutputStream is required...
					ServletOutputStream outputStream = response.getOutputStream();
						workbook.write(outputStream);
						workbook.close();
						
		
	}

}
